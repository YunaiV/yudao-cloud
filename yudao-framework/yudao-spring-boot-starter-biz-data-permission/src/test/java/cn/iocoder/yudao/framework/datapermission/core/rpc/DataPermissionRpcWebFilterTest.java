package cn.iocoder.yudao.framework.datapermission.core.rpc;

import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.framework.datapermission.core.aop.DataPermissionContextHolder;
import javax.servlet.FilterChain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@link DataPermissionRpcWebFilter} 的单元测试
 *
 * @author 芋道源码
 */
public class DataPermissionRpcWebFilterTest {

    private final DataPermissionRpcWebFilter filter = new DataPermissionRpcWebFilter();

    @BeforeEach
    public void setUp() {
        // 清理其它单测（同线程）残留的上下文
        DataPermissionContextHolder.clear();
    }

    @AfterEach
    public void tearDown() {
        DataPermissionContextHolder.clear();
    }

    @Test
    public void testDoFilter_adminApiIgnoreHeader() throws Exception {
        // 准备参数
        MockHttpServletRequest request = buildRequest("", "/admin-api/crm/customer/page", "false");

        // 调用，并断言
        assertNull(doFilterAndGetDataPermission(request));
        assertNull(DataPermissionContextHolder.get());
    }

    @Test
    public void testDoFilter_appApiIgnoreHeader() throws Exception {
        // 准备参数
        MockHttpServletRequest request = buildRequest("", "/app-api/trade/order/page", "false");

        // 调用，并断言
        assertNull(doFilterAndGetDataPermission(request));
        assertNull(DataPermissionContextHolder.get());
    }

    @Test
    public void testDoFilter_rpcApiPrefixLikeIgnoreHeader() throws Exception {
        // 准备参数：仅前缀相似，并不是 /rpc-api/ 下的路径
        MockHttpServletRequest request = buildRequest("", "/rpc-api-fake/system/user/get", "false");

        // 调用，并断言
        assertNull(doFilterAndGetDataPermission(request));
    }

    @Test
    public void testDoFilter_contextPathAdminApiIgnoreHeader() throws Exception {
        // 准备参数：contextPath 下的 /admin-api
        MockHttpServletRequest request = buildRequest("/yudao", "/yudao/admin-api/system/user/page", "false");

        // 调用，并断言
        assertNull(doFilterAndGetDataPermission(request));
    }

    @Test
    public void testDoFilter_rpcApiDisable() throws Exception {
        // 准备参数
        MockHttpServletRequest request = buildRequest("", "/rpc-api/system/user/list", "false");

        // 调用
        DataPermission dataPermission = doFilterAndGetDataPermission(request);
        // 断言：请求过程中禁用数据权限，请求结束后恢复上下文
        assertNotNull(dataPermission);
        assertFalse(dataPermission.enable());
        assertNull(DataPermissionContextHolder.get());
    }

    @Test
    public void testDoFilter_contextPathRpcApiDisable() throws Exception {
        // 准备参数：contextPath 下的 /rpc-api
        MockHttpServletRequest request = buildRequest("/yudao", "/yudao/rpc-api/system/user/list", "false");

        // 调用
        DataPermission dataPermission = doFilterAndGetDataPermission(request);
        // 断言
        assertNotNull(dataPermission);
        assertFalse(dataPermission.enable());
        assertNull(DataPermissionContextHolder.get());
    }

    @Test
    public void testDoFilter_rpcApiDefault() throws Exception {
        // 准备参数：无 Header、Header 非 false 时，保持默认开启
        MockHttpServletRequest request = buildRequest("", "/rpc-api/system/user/list", null);
        MockHttpServletRequest request02 = buildRequest("", "/rpc-api/system/user/list", "true");

        // 调用，并断言
        assertNull(doFilterAndGetDataPermission(request));
        assertNull(doFilterAndGetDataPermission(request02));
    }

    @Test
    public void testDoFilter_rpcApiDisableWithException() {
        // 准备参数
        MockHttpServletRequest request = buildRequest("", "/rpc-api/system/user/list", "false");
        AtomicBoolean disabled = new AtomicBoolean(false);
        FilterChain chain = (req, resp) -> {
            DataPermission dataPermission = DataPermissionContextHolder.get();
            disabled.set(dataPermission != null && !dataPermission.enable());
            throw new IllegalStateException("模拟业务异常");
        };

        // 调用，并断言：异常后恢复上下文
        assertThrows(IllegalStateException.class, () -> filter.doFilter(request, new MockHttpServletResponse(), chain));
        assertTrue(disabled.get());
        assertNull(DataPermissionContextHolder.get());
    }

    @Test
    public void testDoFilter_rpcApiDisableWithOuterDataPermission() throws Exception {
        // 准备参数：已存在外层开启的 DataPermission
        DataPermission outer = mockEnableDataPermission();
        DataPermissionContextHolder.add(outer);
        MockHttpServletRequest request = buildRequest("", "/rpc-api/system/user/list", "false");

        // 调用
        DataPermission dataPermission = doFilterAndGetDataPermission(request);
        // 断言：请求过程中禁用数据权限，请求结束后恢复外层
        assertNotNull(dataPermission);
        assertNotSame(outer, dataPermission);
        assertFalse(dataPermission.enable());
        assertSame(outer, DataPermissionContextHolder.get());
        assertEquals(1, DataPermissionContextHolder.getAll().size());
    }

    @Test
    public void testDoFilter_rpcApiDisableWithOuterDataPermissionAndException() {
        // 准备参数：已存在外层开启的 DataPermission
        DataPermission outer = mockEnableDataPermission();
        DataPermissionContextHolder.add(outer);
        MockHttpServletRequest request = buildRequest("", "/rpc-api/system/user/list", "false");
        AtomicBoolean disabled = new AtomicBoolean(false);
        FilterChain chain = (req, resp) -> {
            DataPermission dataPermission = DataPermissionContextHolder.get();
            disabled.set(dataPermission != null && dataPermission != outer && !dataPermission.enable());
            throw new IllegalStateException("模拟业务异常");
        };

        // 调用，并断言：异常后恢复外层
        assertThrows(IllegalStateException.class, () -> filter.doFilter(request, new MockHttpServletResponse(), chain));
        assertTrue(disabled.get());
        assertSame(outer, DataPermissionContextHolder.get());
        assertEquals(1, DataPermissionContextHolder.getAll().size());
    }

    private static DataPermission mockEnableDataPermission() {
        DataPermission dataPermission = mock(DataPermission.class);
        when(dataPermission.enable()).thenReturn(true);
        return dataPermission;
    }

    private DataPermission doFilterAndGetDataPermission(MockHttpServletRequest request) throws Exception {
        AtomicReference<DataPermission> dataPermissionRef = new AtomicReference<>();
        AtomicBoolean invoked = new AtomicBoolean(false);
        FilterChain chain = (req, resp) -> {
            invoked.set(true);
            dataPermissionRef.set(DataPermissionContextHolder.get());
        };
        filter.doFilter(request, new MockHttpServletResponse(), chain);
        assertTrue(invoked.get());
        return dataPermissionRef.get();
    }

    private static MockHttpServletRequest buildRequest(String contextPath, String requestURI, String enable) {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", requestURI);
        request.setContextPath(contextPath);
        if (enable != null) {
            request.addHeader(DataPermissionRequestInterceptor.ENABLE_HEADER_NAME, enable);
        }
        return request;
    }

}
