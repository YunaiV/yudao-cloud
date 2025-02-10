package cn.iocoder.yudao.module.infra.framework.file.core.hbase;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.ftp.FtpMode;
import cn.iocoder.yudao.module.infra.framework.file.core.client.ftp.FtpFileClient;
import cn.iocoder.yudao.module.infra.framework.file.core.client.ftp.FtpFileClientConfig;
import cn.iocoder.yudao.module.infra.framework.file.core.client.hbase.HbaseFileClient;
import cn.iocoder.yudao.module.infra.framework.file.core.client.hbase.HbaseFileClientConfig;
import com.alibaba.nacos.common.utils.MD5Utils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static cn.hutool.core.util.CharsetUtil.UTF_8;

public class HbaseFileClientTest {

    @Test
    @Disabled
    public void test() {
        // 创建客户端
        HbaseFileClientConfig config = new HbaseFileClientConfig();
        config.setDomain("http://127.0.0.1:48080");
        config.setQuorum("10.0.31.207:2181,10.0.31.208:2181,10.0.31.209:2181");
        config.setTable("img");
        config.setFamily("data");
        config.setColumn("content");
        config.setColumnTimeToLive(null);
        HbaseFileClient client = new HbaseFileClient(0L, config);
        client.init();
        // 上传文件
        String path = IdUtil.fastSimpleUUID() + ".jpg";
        byte[] content = ResourceUtil.readBytes("file/erweima.jpg");
        String fullPath = client.upload(content, path, "image/jpeg");
        System.out.println("访问地址：" + fullPath);
        if (true) {
            byte[] bytes = client.getContent(path);
            System.out.println("文件内容：" + bytes);
        }
        if (true) {
            client.delete(path);
        }
    }

}
