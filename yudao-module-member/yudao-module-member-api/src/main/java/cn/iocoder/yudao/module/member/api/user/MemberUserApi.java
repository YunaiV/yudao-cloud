package cn.iocoder.yudao.module.member.api.user;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.yudao.module.member.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 会员用户")
public interface MemberUserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获得会员用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    CommonResult<MemberUserRespDTO> getUser(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获得会员用户信息们")
    @Parameter(name = "ids", description = "用户编号的数组", example = "1,2", required = true)
    CommonResult<List<MemberUserRespDTO>> getUserList(@RequestParam("ids") Collection<Long> ids);

    /**
     * 获得会员用户 Map
     *
     * @param ids 用户编号的数组
     * @return 会员用户 Map
     */
    default Map<Long, MemberUserRespDTO> getUserMap(Collection<Long> ids) {
        List<MemberUserRespDTO> list = getUserList(ids).getCheckedData();
        return convertMap(list, MemberUserRespDTO::getId);
    }

    @GetMapping(PREFIX + "/list-by-nickname")
    @Operation(summary = "基于用户昵称，模糊匹配用户列表")
    @Parameter(name = "nickname", description = "用户昵称，模糊匹配", required = true, example = "土豆")
    CommonResult<List<MemberUserRespDTO>> getUserListByNickname(@RequestParam("nickname") String nickname);

    @GetMapping(PREFIX + "/get-by-mobile")
    @Operation(summary = "基于手机号，精准匹配用户")
    @Parameter(name = "mobile", description = "基于手机号，精准匹配用户", required = true, example = "1560")
    CommonResult<MemberUserRespDTO> getUserByMobile(@RequestParam("mobile") String mobile);

    @GetMapping(PREFIX + "/valid")
    @Operation(summary = "校验用户是否存在")
    @Parameter(name = "id", description = "用户编号", required = true, example = "1")
    CommonResult<Boolean> validateUser(@RequestParam("id") Long id);

}
