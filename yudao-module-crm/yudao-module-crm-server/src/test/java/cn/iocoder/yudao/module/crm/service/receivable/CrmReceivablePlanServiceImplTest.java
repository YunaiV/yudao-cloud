package cn.iocoder.yudao.module.crm.service.receivable;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanSaveReqVO;
import cn.iocoder.yudao.module.crm.dal.dataobject.contract.CrmContractDO;
import cn.iocoder.yudao.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;
import cn.iocoder.yudao.module.crm.dal.mysql.receivable.CrmReceivablePlanMapper;
import cn.iocoder.yudao.module.crm.service.contract.CrmContractService;
import cn.iocoder.yudao.module.crm.service.permission.CrmPermissionService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.module.crm.enums.ErrorCodeConstants.CONTRACT_NOT_EXISTS;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class CrmReceivablePlanServiceImplTest extends BaseMockitoUnitTest {
    @InjectMocks
    private CrmReceivablePlanServiceImpl service;
    @Mock
    private CrmReceivablePlanMapper receivablePlanMapper;
    @Mock
    private CrmContractService contractService;
    @Mock
    private CrmPermissionService permissionService;
    @Mock
    private AdminUserApi adminUserApi;

    @Test
    void missingContractReturnsBusinessErrorBeforeInsert() {
        when(contractService.validateContract(9L)).thenThrow(exception(CONTRACT_NOT_EXISTS));
        assertServiceException(() -> service.createReceivablePlan(
                new CrmReceivablePlanSaveReqVO().setContractId(9L)), CONTRACT_NOT_EXISTS);
        verifyNoInteractions(receivablePlanMapper, permissionService);
    }

    @Test
    void validContractDeterminesCustomer() {
        when(contractService.validateContract(9L)).thenReturn(new CrmContractDO().setId(9L).setCustomerId(20L));
        service.createReceivablePlan(new CrmReceivablePlanSaveReqVO().setContractId(9L).setCustomerId(999L));
        verify(receivablePlanMapper).insert(argThat((CrmReceivablePlanDO row) ->
                Objects.equals(row.getCustomerId(), 20L) && Objects.equals(row.getPeriod(), 1)));
    }
}
