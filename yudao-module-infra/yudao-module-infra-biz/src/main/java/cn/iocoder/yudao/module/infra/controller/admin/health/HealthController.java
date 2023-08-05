package cn.iocoder.yudao.module.infra.controller.admin.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * @description: 健康检查
 * @author: jhaol
 */
@Tag(name = "infra 模块 - 健康检查")
@RestController
@RequestMapping("/infra/health")
@Validated
public class HealthController {
    @GetMapping()
    @Operation(summary = "健康检查")
    @PermitAll
    public Integer health() {
        return 0;
    }
}
