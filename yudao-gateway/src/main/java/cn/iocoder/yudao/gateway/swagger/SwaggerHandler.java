//package cn.iocoder.yudao.gateway.swagger;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//import springfox.documentation.swagger.web.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Optional;
//
///**
// * Swagger Controller
// *
// * @author zxliu
// * @date 2022-10-25 11:24
// */
//@RestController
//@RequestMapping("/swagger-resources")
//public class SwaggerHandler {
//
//    @Resource
//    private SwaggerResourcesProvider swaggerResources;
//
//    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection") // 只有 @Autowired 可以实现可选注入
//    @Autowired(required = false)
//    private SecurityConfiguration securityConfiguration;
//    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection") // 只有 @Autowired 可以实现可选注入
//    @Autowired(required = false)
//    private UiConfiguration uiConfiguration;
//
//    @GetMapping("")
//    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources() {
//        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
//    }
//
//    @GetMapping("/configuration/security")
//    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
//        return Mono.just(new ResponseEntity<>(Optional.ofNullable(securityConfiguration)
//                .orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//    @GetMapping("/configuration/ui")
//    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
//        return Mono.just(new ResponseEntity<>(Optional.ofNullable(uiConfiguration)
//                .orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//}
