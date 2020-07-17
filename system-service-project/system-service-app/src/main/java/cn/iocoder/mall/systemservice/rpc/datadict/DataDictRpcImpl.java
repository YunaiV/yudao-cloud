package cn.iocoder.mall.systemservice.rpc.datadict;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.datadict.DataDictManager;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 数据字典 Rpc 实现类
*/
@Service(version = "${dubbo.provider.DataDictRpc.version}")
public class DataDictRpcImpl implements DataDictRpc {

    @Autowired
    private DataDictManager dataDictManager;

    @Override
    public CommonResult<Integer> createDataDict(DataDictCreateDTO createDTO) {
        return success(dataDictManager.createDataDict(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateDataDict(DataDictUpdateDTO updateDTO) {
        dataDictManager.updateDataDict(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteDataDict(Integer dataDictId) {
        dataDictManager.deleteDataDict(dataDictId);
        return success(true);
    }

    @Override
    public CommonResult<DataDictVO> getDataDict(Integer dataDictId) {
        return success(dataDictManager.getDataDict(dataDictId));
    }

    @Override
    public CommonResult<List<DataDictVO>> listDataDicts() {
        return success(dataDictManager.listDataDicts());
    }

    @Override
    public CommonResult<List<DataDictVO>> listDataDicts(List<Integer> dataDictIds) {
        return success(dataDictManager.listDataDicts(dataDictIds));
    }

}
