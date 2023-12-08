package cn.iocoder.yudao.module.system.controller.admin.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;

/**
 * @description: 健康检查
 * @author: jhaol
 */
@Tag(name = "system 模块 - 健康检查")
@RestController
@RequestMapping("/system/health")
@Validated
public class HealthController {
    @GetMapping()
    @Operation(summary = "健康检查")
    @PermitAll
    public Integer health() {
        return 0;
    }
}
