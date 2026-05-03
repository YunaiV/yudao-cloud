package cn.iocoder.yudao.module.iot.service.device.property;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.iot.core.enums.IotDeviceMessageMethodEnum;
import cn.iocoder.yudao.module.iot.core.mq.message.IotDeviceMessage;
import cn.iocoder.yudao.module.iot.dal.dataobject.device.IotDeviceDO;
import cn.iocoder.yudao.module.iot.dal.dataobject.device.IotDevicePropertyDO;
import cn.iocoder.yudao.module.iot.dal.dataobject.thingmodel.IotThingModelDO;
import cn.iocoder.yudao.module.iot.dal.dataobject.thingmodel.model.ThingModelProperty;
import cn.iocoder.yudao.module.iot.dal.redis.device.DevicePropertyRedisDAO;
import cn.iocoder.yudao.module.iot.dal.tdengine.IotDevicePropertyMapper;
import cn.iocoder.yudao.module.iot.enums.thingmodel.IotDataSpecsDataTypeEnum;
import cn.iocoder.yudao.module.iot.service.thingmodel.IotThingModelService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@link IotDevicePropertyServiceImpl} 的单元测试
 *
 * @author 芋道源码
 */
public class IotDevicePropertyServiceImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private IotDevicePropertyServiceImpl service;

    @Mock
    private IotThingModelService thingModelService;
    @Mock
    private IotDevicePropertyMapper devicePropertyMapper;
    @Mock
    private DevicePropertyRedisDAO deviceDataRedisDAO;

    @Test
    public void testSaveDeviceProperty_identifierCaseInsensitive() {
        // 准备参数：物模型 identifier 是 "LightStatus"，设备上报的 key 是 "LIGHTSTATUS"（全大写）
        IotDeviceDO device = buildDevice();
        IotThingModelDO thingModel = buildThingModel("LightStatus", IotDataSpecsDataTypeEnum.INT.getDataType());
        Map<String, Object> params = new HashMap<>();
        params.put("LIGHTSTATUS", 100);
        IotDeviceMessage message = buildMessage(params);

        // mock 行为
        when(thingModelService.getThingModelListByProductIdFromCache(device.getProductId()))
                .thenReturn(singletonList(thingModel));

        // 调用
        service.saveDeviceProperty(device, message);

        // 断言：properties 落库 / 入缓存时 key 应为物模型 identifier "LightStatus"，而不是上报的 "LIGHTSTATUS"
        Map<String, Object> dbProperties = captureMapperInsertProperties();
        assertTrue(dbProperties.containsKey("LightStatus"));
        assertFalse(dbProperties.containsKey("LIGHTSTATUS"));
        assertEquals(100, dbProperties.get("LightStatus"));

        Map<String, IotDevicePropertyDO> redisProperties = captureRedisPutAllProperties(device.getId());
        assertTrue(redisProperties.containsKey("LightStatus"));
        assertFalse(redisProperties.containsKey("LIGHTSTATUS"));
    }

    @Test
    public void testSaveDeviceProperty_identifierNotInThingModel() {
        // 准备参数：上报的 key 在物模型里完全不存在（连忽略大小写都匹配不到）
        IotDeviceDO device = buildDevice();
        IotThingModelDO thingModel = buildThingModel("LightStatus", IotDataSpecsDataTypeEnum.INT.getDataType());
        Map<String, Object> params = new HashMap<>();
        params.put("UnknownProperty", 1);
        IotDeviceMessage message = buildMessage(params);

        // mock 行为
        when(thingModelService.getThingModelListByProductIdFromCache(device.getProductId()))
                .thenReturn(singletonList(thingModel));

        // 调用
        service.saveDeviceProperty(device, message);

        // 断言：没有合法属性，不会写入 TDengine 与 Redis
        verify(devicePropertyMapper, never()).insert(any(), any(), anyLong(), anyLong());
        verify(deviceDataRedisDAO, never()).putAll(anyLong(), any());
    }

    @Test
    public void testSaveDeviceProperty_boolFromBooleanTrue() {
        // 准备参数：物模型为 BOOL，设备上报原生 boolean true
        assertBoolValueConvertedToByte(true, (byte) 1);
    }

    @Test
    public void testSaveDeviceProperty_boolFromBooleanFalse() {
        // 准备参数：物模型为 BOOL，设备上报原生 boolean false
        assertBoolValueConvertedToByte(false, (byte) 0);
    }

    @Test
    public void testSaveDeviceProperty_boolFromStringTrue() {
        // 准备参数：物模型为 BOOL，设备上报字符串 "true"
        assertBoolValueConvertedToByte("true", (byte) 1);
    }

    @Test
    public void testSaveDeviceProperty_boolFromStringFalse() {
        // 准备参数：物模型为 BOOL，设备上报字符串 "false"
        assertBoolValueConvertedToByte("false", (byte) 0);
    }

    @Test
    public void testSaveDeviceProperty_boolFromNumberOne() {
        // 准备参数：物模型为 BOOL，设备上报数字 1
        assertBoolValueConvertedToByte(1, (byte) 1);
    }

    @Test
    public void testSaveDeviceProperty_boolFromNumberZero() {
        // 准备参数：物模型为 BOOL，设备上报数字 0
        assertBoolValueConvertedToByte(0, (byte) 0);
    }

    /**
     * 校验 BOOL 类型属性上报后，最终落到 properties Map 的值类型与数值
     */
    private void assertBoolValueConvertedToByte(Object reportedValue, byte expected) {
        // 准备参数
        IotDeviceDO device = buildDevice();
        IotThingModelDO thingModel = buildThingModel("PowerSwitch", IotDataSpecsDataTypeEnum.BOOL.getDataType());
        Map<String, Object> params = new HashMap<>();
        params.put("PowerSwitch", reportedValue);
        IotDeviceMessage message = buildMessage(params);

        // mock 行为
        when(thingModelService.getThingModelListByProductIdFromCache(device.getProductId()))
                .thenReturn(singletonList(thingModel));

        // 调用：不能抛异常
        assertDoesNotThrow(() -> service.saveDeviceProperty(device, message));

        // 断言：写入的 value 是 byte 类型，且值匹配
        Map<String, Object> dbProperties = captureMapperInsertProperties();
        Object actual = dbProperties.get("PowerSwitch");
        assertTrue(actual instanceof Byte, "BOOL 属性应被转为 Byte 类型，实际为 " + (actual == null ? "null" : actual.getClass()));
        assertEquals(expected, actual);
    }

    // ========== 辅助方法 ==========

    /**
     * 构造一个最简 IotDeviceDO，只设置测试需要的 id 与 productId
     */
    private IotDeviceDO buildDevice() {
        return IotDeviceDO.builder().id(1L).productId(2L).build();
    }

    /**
     * 构造物模型；只填 saveDeviceProperty 链路用到的 identifier + property.dataType
     */
    private IotThingModelDO buildThingModel(String identifier, String dataType) {
        ThingModelProperty property = new ThingModelProperty();
        property.setIdentifier(identifier);
        property.setDataType(dataType);
        return IotThingModelDO.builder().identifier(identifier).property(property).build();
    }

    /**
     * 构造一条属性上报消息
     */
    private IotDeviceMessage buildMessage(Map<String, Object> params) {
        IotDeviceMessage message = new IotDeviceMessage();
        message.setMethod(IotDeviceMessageMethodEnum.PROPERTY_POST.getMethod());
        message.setParams(params);
        message.setReportTime(LocalDateTime.now());
        return message;
    }

    /**
     * 抓取 mapper.insert 的 properties 入参
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> captureMapperInsertProperties() {
        ArgumentCaptor<Map<String, Object>> captor = ArgumentCaptor.forClass(Map.class);
        verify(devicePropertyMapper).insert(any(IotDeviceDO.class), captor.capture(), anyLong(), anyLong());
        return captor.getValue();
    }

    /**
     * 抓取 redisDAO.putAll 的 properties 入参
     */
    @SuppressWarnings("unchecked")
    private Map<String, IotDevicePropertyDO> captureRedisPutAllProperties(Long deviceId) {
        ArgumentCaptor<Map<String, IotDevicePropertyDO>> captor = ArgumentCaptor.forClass(Map.class);
        verify(deviceDataRedisDAO).putAll(eq(deviceId), captor.capture());
        return captor.getValue();
    }

}
