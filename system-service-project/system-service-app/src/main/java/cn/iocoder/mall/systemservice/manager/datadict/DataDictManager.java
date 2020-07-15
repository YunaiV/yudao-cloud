package cn.iocoder.mall.systemservice.manager.datadict;

import cn.iocoder.mall.systemservice.convert.datadict.DataDictConvert;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;
import cn.iocoder.mall.systemservice.service.datadict.DataDictService;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 数据字典 Manager
*/
@Service
public class DataDictManager {

    @Autowired
    private DataDictService dataDictService;

    /**
    * 创建数据字典
    *
    * @param createDTO 创建数据字典 DTO
    * @return 数据字典
    */
    public Integer createDataDict(DataDictCreateDTO createDTO) {
        DataDictBO dataDictBO = dataDictService.createDataDict(DataDictConvert.INSTANCE.convert(createDTO));
        return dataDictBO.getId();
    }

    /**
    * 更新数据字典
    *
    * @param updateDTO 更新数据字典 DTO
    */
    public void updateDataDict(DataDictUpdateDTO updateDTO) {
        dataDictService.updateDataDict(DataDictConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除数据字典
    *
    * @param dataDictId 数据字典编号
    */
    public void deleteDataDict(Integer dataDictId) {
        dataDictService.deleteDataDict(dataDictId);
    }

    /**
    * 获得数据字典
    *
    * @param dataDictId 数据字典编号
    * @return 数据字典
    */
    public DataDictVO getDataDict(Integer dataDictId) {
        DataDictBO dataDictBO = dataDictService.getDataDict(dataDictId);
        return DataDictConvert.INSTANCE.convert(dataDictBO);
    }

    /**
     * 获得全部数据字典
     *
     * @return 数据字典列表
     */
    public List<DataDictVO> listDataDicts() {
        List<DataDictBO> dataDictBOs = dataDictService.listDataDicts();
        return DataDictConvert.INSTANCE.convertList02(dataDictBOs);
    }

    /**
    * 获得数据字典列表
    *
    * @param dataDictIds 数据字典编号列表
    * @return 数据字典列表
    */
    public List<DataDictVO> listDataDicts(List<Integer> dataDictIds) {
        List<DataDictBO> dataDictBOs = dataDictService.listDataDicts(dataDictIds);
        return DataDictConvert.INSTANCE.convertList02(dataDictBOs);
    }

}
