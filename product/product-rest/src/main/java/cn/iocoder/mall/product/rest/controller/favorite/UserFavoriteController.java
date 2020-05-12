package cn.iocoder.mall.product.rest.controller.favorite;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户收藏
 * @author xiaofeng
 * @date 2019/07/07 11:06
 * @version 1.0
 */
@RestController
@RequestMapping("users/favorite")
@Api("用户收藏")
// TODO FROM 芋艿 to ilnhj：controller 分包的话，还是按照模块。然后通过 Admins 和 Users 前缀，区分不同的 Controlller
public class UserFavoriteController {

}
