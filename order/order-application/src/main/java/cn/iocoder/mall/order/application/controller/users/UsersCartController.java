package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.api.dto.CalcOrderPriceDTO;
import cn.iocoder.mall.order.application.convert.CartConvert;
import cn.iocoder.mall.order.application.vo.UsersCalcSkuPriceVO;
import cn.iocoder.mall.order.application.vo.UsersCartDetailVO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("users/cart")
public class UsersCartController {

    @Reference(validation = "true")
    private CartService cartService;
    @Reference(validation = "true")
    private OrderService orderService;

    @PostMapping("add")
    public CommonResult<Integer> add(@RequestParam("skuId") Integer skuId,
                                     @RequestParam("quantity") Integer quantity) {
        // 添加到购物车
        CommonResult<Boolean> addResult = cartService.add(UserSecurityContextHolder.getContext().getUserId(),
                skuId, quantity);
        // 添加失败，则直接返回错误
        if (addResult.isError()) {
            return CommonResult.error(addResult);
        }
        // 获得目前购物车商品总数量
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @PostMapping("update_quantity")
    public CommonResult<UsersCartDetailVO> updateQuantity(@RequestParam("skuId") Integer skuId, // TODO 芋艿，先暂用这个 VO 。等促销活动出来后，做调整
                                                          @RequestParam("quantity") Integer quantity) {
        // 添加到购物车
        CommonResult<Boolean> updateQuantityResult = cartService.updateQuantity(UserSecurityContextHolder.getContext().getUserId(),
                skuId, quantity);
        // 添加失败，则直接返回错误
        if (updateQuantityResult.isError()) {
            return CommonResult.error(updateQuantityResult);
        }
        // 获得目前购物车明细
        return getCartDetail();
    }

    @PostMapping("update_selected")
    public CommonResult<UsersCartDetailVO> updateSelected(@RequestParam("skuIds") Set<Integer> skuIds, // TODO 芋艿，先暂用这个 VO 。等促销活动出来后，做调整
                                                          @RequestParam("selected") Boolean selected) {
        // 添加到购物车
        CommonResult<Boolean> updateSelectedResult = cartService.updateSelected(UserSecurityContextHolder.getContext().getUserId(),
                skuIds, selected);
        // 添加失败，则直接返回错误
        if (updateSelectedResult.isError()) {
            return CommonResult.error(updateSelectedResult);
        }
        // 获得目前购物车明细
        return getCartDetail();
    }

    @GetMapping("count")
    public CommonResult<Integer> count() {
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @GetMapping("/list")
    public CommonResult<UsersCartDetailVO> list() { // TODO 芋艿，先暂用这个 VO 。等促销活动出来后，做调整
        return getCartDetail();
    }

    private CommonResult<UsersCartDetailVO> getCartDetail() {
        // 获得购物车中选中的
        List<CartItemBO> cartItems = cartService.list(UserSecurityContextHolder.getContext().getUserId(), null).getData();
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersCartDetailVO result = new UsersCartDetailVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersCartDetailVO.Fee(0, 0, 0, 0));
            return CommonResult.success(result);
        }
        // 计算商品价格
        CommonResult<CalcOrderPriceBO> calcOrderPriceResult = list0(cartItems);
        if (calcOrderPriceResult.isError()) {
            return CommonResult.error(calcOrderPriceResult);
        }
        // 执行数据拼装
        return CommonResult.success(CartConvert.INSTANCE.convert2(calcOrderPriceResult.getData()));
    }

    @GetMapping("/confirm_create_order")
    public CommonResult<UsersOrderConfirmCreateVO> getConfirmCreateOrder() {
        // 获得购物车中选中的
        List<CartItemBO> cartItems = cartService.list(UserSecurityContextHolder.getContext().getUserId(), true).getData();
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersOrderConfirmCreateVO result = new UsersOrderConfirmCreateVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersOrderConfirmCreateVO.Fee(0, 0, 0, 0));
            return CommonResult.success(result);
        }
        // 计算商品价格
        CommonResult<CalcOrderPriceBO> calcOrderPriceResult = list0(cartItems);
        if (calcOrderPriceResult.isError()) {
            return CommonResult.error(calcOrderPriceResult);
        }
        // 执行数据拼装
        return CommonResult.success(CartConvert.INSTANCE.convert(calcOrderPriceResult.getData()));
    }

    private CommonResult<CalcOrderPriceBO> list0(List<CartItemBO> cartItems) {
        // 创建计算的 DTO
        CalcOrderPriceDTO calcOrderPriceDTO = new CalcOrderPriceDTO()
                .setItems(new ArrayList<>(cartItems.size()));
        for (CartItemBO item : cartItems) {
            calcOrderPriceDTO.getItems().add(new CalcOrderPriceDTO.Item(item.getSkuId(), item.getQuantity(), item.getSelected()));
        }
        // 执行计算
        return cartService.calcOrderPrice(calcOrderPriceDTO);
    }

    @GetMapping("/calc_sku_price")
    public CommonResult<UsersCalcSkuPriceVO> calcSkuPrice(@RequestParam("skuId") Integer skuId) {
        // 计算 sku 的价格
        CommonResult<CalcSkuPriceBO> calcSkuPriceResult = cartService.calcSkuPrice(skuId);
        // 返回结果
        if (calcSkuPriceResult.isError()) {
            return CommonResult.error(calcSkuPriceResult);
        }
        return CommonResult.success(CartConvert.INSTANCE.convert2(calcSkuPriceResult.getData()));
    }

    public CommonResult<Object> confirmOrder() {
        // 查询购物车列表（选中的）
//        cartService.list(userId, true);
        // 查询确认订单信息的明细

        return null;
    }

}
