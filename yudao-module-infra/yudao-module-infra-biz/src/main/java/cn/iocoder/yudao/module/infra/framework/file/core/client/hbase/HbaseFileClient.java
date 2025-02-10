package cn.iocoder.yudao.module.infra.framework.file.core.client.hbase;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.infra.framework.file.core.client.AbstractFileClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hbase 文件客户端
 * 调优策略 - 依据使用场景进行调整 针对 预分区数量
 * 注意：RowKey 设计为 MD5(filename) : filename
 *
 * @author sur1-123
 */
@Slf4j
public class HbaseFileClient extends AbstractFileClient<HbaseFileClientConfig> {

    // zookeeper (集群)连接地址
    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_ROOTDIR = "hbase.rootdir";
    // 客户端连接端口
    private static final String HBASE_ZOOKEPER_CLIENTPORT = "hbase.zookeeper.property.clientPort";
    // master的kerberos认证的主体名称
    private static final String HBASE_MASTER_PRINCIPAL = "hbase.master.kerberos.principal";
    // regionserver的kerberos认证的主体名称
    private static final String HBASE_REGIONSERVER_PRINCIPAL = "hbase.regionserver.kerberos.principal";
    // hbase 集群安全认证机制，目前的版本只支持kerberos安全认证
    private static final String HBASE_AUTHENTICATION = "hbase.security.authentication";
    // hadoop 集群安全认证机制，目前的版本只支持kerberos安全认证
    private static final String HADOOP_AUTHENTICATION = "hadoop.security.authentication";

    /**
     * 管理员可以做表以及数据的增删改查功能
     */
    private Admin admin;
    private Connection connection;
    private Integer columnTimeToLive;

    public HbaseFileClient(Long id, HbaseFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();

        configuration.set(HBASE_QUORUM, config.getQuorum());

        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
            columnTimeToLive = config.getColumnTimeToLive();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("获取HBase连接成功!,HBASE_QUORUM: [{}], columnTimeToLive：[{}]", config.getQuorum(), columnTimeToLive);
    }

    // 插入时检查path是否携带日期  不携带报错！！！
    @Override
    public String upload(byte[] content, String path, String type) {
        // path 为 RowKey (如果重名 新数据会覆盖旧数据)
        // 执行写入
        String fileTable = getFileTable(path);
        // 初始化 path 如果没有后缀 为其补充后缀
        String fileRowKey = getFileRowKey(path);
        // 后续可以考虑增加限制 如果多次出现 RowKey 长度异常时 不新增此数据
        // 塞入 图片数据
        putData(fileTable, fileRowKey, config.getFamily(), config.getColumn(), content);
        return super.formatFileUrl(config.getDomain(), path);
    }

    @Override
    public void delete(String path) {
        String tableName = getFileTable(path);
        String fileRowKey = getFileRowKey(path);
        try {
            deleteData(tableName, fileRowKey, config.getFamily(), config.getColumn());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getContent(String path) {
        String tableName = getFileTable(path);
        String fileRowKey = getFileRowKey(path);
        try {
            return getData(tableName, fileRowKey, config.getFamily(), config.getColumn());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileRowKey(String path) {
        String filename = FileUtil.getName(path);
        String md5 = MD5.create().digestHex16(filename);
        return md5.concat(":").concat(filename);
    }

    private String getFileTable(String path) {
        return config.getTable();
    }

    /**
     * 创建表 create <table>, {NAME => <column family>, VERSIONS => <VERSIONS>}
     */
    public synchronized boolean creatTable(String tableName, List<String> columnFamily) {
        try {
            // 列族column family
            List<ColumnFamilyDescriptor> cfDesc = new ArrayList<>(columnFamily.size());
            // 给每个列族设置存活时间
            columnFamily.forEach(cf -> {
                ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
                if(ObjUtil.isNotEmpty(config.getColumnTimeToLive()) && config.getColumnTimeToLive() > 0){
                    columnFamilyDescriptorBuilder.setTimeToLive(columnTimeToLive);
                }
                cfDesc.add(columnFamilyDescriptorBuilder.build());
            });
            // 表 table
            TableDescriptor tableDesc = TableDescriptorBuilder
                    .newBuilder(TableName.valueOf(tableName))
                    .setColumnFamilies(cfDesc).build();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                log.debug("table Exists!");
            } else {
                admin.createTable(tableDesc, generateRowKeyPartition());
                log.debug("create table Success!");
            }
        } catch (IOException e) {
            log.error("创建表[{}]失败,错误信息为：[{}]", tableName, e);
            return false;
        } finally {
            close(admin, null, null);
        }
        return true;
    }

    /**
     * TODO 根据需求调整此处的 预分区长度 一般取前两位 作为分区数 数量就够了
     *  如果取前三位 则会有 4096个 分区
     * 生成预分区键（取MD5前2位，范围：00到FF）
     * 生成预分区键（取MD5前3位，范围：000到FFF）
     */
    private byte[][] generateRowKeyPartition() {
        List<byte[]> splitKeys = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            // 将整数转换为2位十六进制字符串
            String hex = String.format("%02X", i);
            // 将十六进制字符串转换为字节数组
            splitKeys.add(Bytes.toBytes(hex));
        }
        return splitKeys.toArray(new byte[0][]);
    }

    /**
     * 查询所有表的表名
     */
    public List<String> getAllTableNames() {
        List<String> result = new ArrayList<>();
        try {
            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                result.add(tableName.getNameAsString());
            }
        } catch (IOException e) {
            log.error("获取所有表的表名失败,错误信息：[{}]", e.getMessage());
        } finally {
            close(admin, null, null);
        }
        return result;
    }

    /**
     * 遍历查询指定表中的所有数据
     */
    public Map<String, Map<String, String>> getResultScanner(String tableName) {
        Scan scan = new Scan();
        return this.queryData(tableName, scan);
    }

    /**
     * 通过表名及过滤条件查询数据
     */
    private Map<String, Map<String, String>> queryData(String tableName, Scan scan) {
        // <rowKey,对应的行数据>
        Map<String, Map<String, String>> result = new HashMap<>();
        ResultScanner rs = null;
        // 获取表
        Table table = null;
        try {
            table = getTable(tableName);
            rs = table.getScanner(scan);
            for (Result r : rs) {
                // 每一行数据
                Map<String, String> columnMap = new HashMap<>();
                String rowKey = null;
                // 行键，列族和列限定符一起确定一个单元（Cell）
                for (Cell cell : r.listCells()) {
                    if (rowKey == null) {
                        rowKey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(),
                                cell.getRowLength());
                    }

                    columnMap.put(
                            // 列族:列限定符
                            Bytes.toString(cell.getQualifierArray(), cell.getFamilyOffset(),
                                    cell.getFamilyLength() + cell.getQualifierLength()),
                            // value
                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                    cell.getValueLength()));
                }
                if (rowKey != null) {
                    result.put(rowKey, columnMap);
                }
            }
        } catch (IOException e) {
            log.error("遍历查询指定表中的所有数据失败,tableName:{},错误信息为：[{}]", tableName, e);
        } finally {
            close(admin, rs, table);
        }
        return result;
    }

    public static byte[] subByte(byte[] b, int off, int length) {

        byte[] b1 = new byte[length];
        System.arraycopy(b, off, b1, 0, length);
        return b1;
    }

    /**
     * 为表添加或者更新数据
     */
    public void putData(String tableName, String rowKey, String familyName,
                        String column,
                        byte[] value) {
        Table table = null;
        try {

            // 如果表不存在 则创建表 tableExists-->如果表存在则返回true 所以如果返回true则不执行if内代码
            if (!admin.tableExists(TableName.valueOf(tableName))) {
                log.info("表不存在，创建表！:[{}]", tableName);
                creatTable(tableName, Arrays.asList(familyName));
            }
            table = getTable(tableName);
            // 如果列族不存在则创建列族
            TableDescriptor tableDescriptor = table.getDescriptor();
            ColumnFamilyDescriptor descriptor = tableDescriptor.getColumnFamily(
                    Bytes.toBytes(familyName));
            if (descriptor == null) {
                // 给每个列族设置存活时间
                // 列族column family
                ColumnFamilyDescriptor newCf = ColumnFamilyDescriptorBuilder.newBuilder(
                        Bytes.toBytes(familyName)).setTimeToLive(604800).build();
                admin.addColumnFamily(table.getName(), newCf);
            }

            // 设置rowkey
            Put put = new Put(Bytes.toBytes(rowKey));
            if (column != null && value != null) {
                put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column),
                        value);
            } else {
                throw new NullPointerException(MessageFormat.format(
                        "列名和列数据都不能为空,column:{0},value:{1}", column, value));
            }
            long startT = System.currentTimeMillis();
            putData(table, put);
            long endT = System.currentTimeMillis();
            log.info(
                    "table:[{}] putData:[Success],lost:[{}]ms,alertType:[{}],family:[{}],rowKey:[{}]",
                    tableName,
                    (endT - startT), column, familyName, rowKey);
            table.close();
        } catch (Exception e) {
            log.error(
                    "为表添加 or 更新数据失败,tableName:[{}],rowKey:[{}],familyName:[{}],errorInfo:[{}]",
                    tableName, rowKey, familyName, e.getMessage());
        }
    }

    private void putData(Table table, Put put) throws IOException {
        table.put(put);
    }

    /**
     * 根据表名获取table
     */
    private Table getTable(String tableName) throws IOException {
        return connection.getTable(TableName.valueOf(tableName));
    }

    /**
     * 关闭流
     */
    private void close(Admin admin, ResultScanner rs, Table table) {
        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                log.error("关闭Admin失败", e);
            }
        }
        if (rs != null) {
            rs.close();
        }
        if (table != null) {
            try {
                table.close();
            } catch (IOException e) {
                log.error("关闭Table失败", e);
            }
        }
    }

    /**
     * 删除表
     */
    public boolean deleteTable(String tableName, List<String> columnFamily) {
        try {
            // 列族column family
            List<ColumnFamilyDescriptor> cfDesc = new ArrayList<>(columnFamily.size());
            columnFamily.forEach(cf -> {
                cfDesc.add(ColumnFamilyDescriptorBuilder.newBuilder(
                        Bytes.toBytes(cf)).build());
            });
            // 表 table
            TableDescriptor tableDesc = TableDescriptorBuilder
                    .newBuilder(TableName.valueOf(tableName))
                    .setColumnFamilies(cfDesc).build();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                log.debug("table Exists!");

                admin.disableTable(TableName.valueOf(tableName));
                admin.deleteTable(TableName.valueOf(tableName));
            }
        } catch (IOException e) {
            log.error(MessageFormat.format("删除表{0}失败", tableName), e);
        } finally {
            close(admin, null, null);
        }
        return true;
    }

    /**
     * 删除指定数据
     */
    public void deleteData(String tableName, String rowKey, String cf, String cn)
            throws IOException {
        // 1.获取表对象
        Table table = getTable(tableName);
        // 2.构建删除对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));

        // 2.2 删除指定的列族
        if (cf != null && !cf.equals("")) {
            delete.addFamily(Bytes.toBytes(cf));
        }
        // 3.执行删除操作
        table.delete(delete);

        close(admin, null, table);
    }

    /**
     * 获取数据 (get)
     *
     * @param cf 列族
     * @param cn 列名
     */
    public byte[] getData(String tableName, String rowKey, String cf, String cn)
            throws IOException {
        // 1.获取表对象
        Table table = getTable(tableName);
        // 2.创建get对象
        Get get = new Get(Bytes.toBytes(rowKey));
        // 2.1指定获取的列族
        // ArrayList<Map<String,String>> results = new ArrayList<>();
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
        // 3.获取数据
        Result result = table.get(get);
        // 4.解析result并打印
        if (!result.isEmpty()) {
            for (Cell cell : result.listCells()) {
                // cn
                CellUtil.cloneQualifier(cell);
                // value
                CellUtil.cloneValue(cell);
                // 去除制表符 换行符 等特殊字符
                table.close();
                return CellUtil.cloneValue(cell);
            }
        } else {
            log.warn("该次{}报警并未上报{}图片", cf, cn);
            // 6.关闭表连接
            table.close();
        }
        table.close();
        return new byte[0];
    }

    public Map<String, String> getDataByList(String tableName, List<String> rowKeys, String cf,
                                             String cn) throws IOException {
        HashMap<String, String> resultMap = new HashMap<>();
        // 1.获取表对象
        Table table = getTable(tableName);
        // 2.创建get对象
        List<Get> listGets = new ArrayList<Get>();
        for (String rowKey : rowKeys) {
            Get get = new Get(Bytes.toBytes(rowKey));
            // 2.1指定获取的列族
            // ArrayList<Map<String,String>> results = new ArrayList<>();
            get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
            listGets.add(get);
        }
        // 3.获取数据
        Result[] results = table.get(listGets);
        for (Result result : results) {
            // 拿到rowk
            String rowk = Bytes.toString(result.getRow());
            String resultBase64 = "";
            // 4.解析result并打印
            if (!result.isEmpty()) {
                for (Cell cell : result.listCells()) {
                    // cn
                    String k = Bytes.toString(CellUtil.cloneQualifier(cell));
                    // value
                    String v = Bytes.toString(CellUtil.cloneValue(cell));
                    // 去除制表符 换行符 等特殊字符
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(v);
                    v = m.replaceAll("");
                    resultBase64 = v;
                }
            } else {
                log.warn("该次{}报警并未上报{}图片", rowk, cn);
                resultBase64 = "该次" + rowk + "报警并未上报" + cn + "图片";
            }
            resultMap.put(rowk, resultBase64);
        }
        // 6.关闭表连接
        table.close();
        return resultMap;
    }

    public Map<String, String> getDataByTimeRange(String tableName, Date startTime, Date endTime,
                                                  String familyName,
                                                  String column) {
        HashMap<String, String> resultMap = new HashMap<>();
        String s = DateUtil.format(startTime, "yyyy_MM_dd");
        String startName = tableName + "-" + s;
        String e = DateUtil.format(endTime, "yyyy_MM_dd");
        String endName = tableName + "-" + e;
        if (startTime.after(endTime)) {
            throw new ServiceException(new ErrorCode(0, "开始时间不能大于结束时间"));
        }
        if (startName.equals(endName)) {
            try {
                Table table = getTable(startName);
                Scan scan = new Scan();
                scan.setTimeRange(startTime.getTime(), endTime.getTime())
                        .addColumn(Bytes.toBytes(familyName),
                                Bytes.toBytes(column));
                ResultScanner scanner = table.getScanner(scan);
                Iterator<Result> iterator = scanner.iterator();
                while (iterator.hasNext()) {
                    Result next = iterator.next();
                    // 拿到rowk
                    String rowk = Bytes.toString(next.getRow());
                    String resultBase64 = "";
                    for (Cell cell : next.listCells()) {
                        // cn
                        String k = Bytes.toString(CellUtil.cloneQualifier(cell));
                        // value
                        String v = Bytes.toString(CellUtil.cloneValue(cell));
                        // 去除制表符 换行符 等特殊字符
                        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                        Matcher m = p.matcher(v);
                        v = m.replaceAll("");
                        resultBase64 = v;
                    }
                    resultMap.put(rowk, resultBase64);
                }
            } catch (IOException ex) {
                log.error(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } else {
            throw new ServiceException(0, "时间不可以跨天");
        }
        return resultMap;
    }

    public boolean verifyHbaseClient() {
        return connection != null || admin != null;
    }

}