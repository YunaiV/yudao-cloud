package cn.iocoder.yudao.gateway.jackson;

import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.json.databind.NumberSerializer;
import cn.iocoder.yudao.framework.common.util.json.databind.TimestampLocalDateTimeDeserializer;
import cn.iocoder.yudao.framework.common.util.json.databind.TimestampLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@Slf4j
public class GatewayJacksonAutoConfiguration {

    /**
     * 从 Builder 源头定制（关键：使用 *ByType，避免 handledType 要求）
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer ldtEpochMillisCustomizer() {
        return builder -> builder
                // Long -> Number
                .serializerByType(Long.class, NumberSerializer.INSTANCE)
                .serializerByType(Long.TYPE, NumberSerializer.INSTANCE)
                // LocalDate / LocalTime
                .serializerByType(LocalDate.class, LocalDateSerializer.INSTANCE)
                .deserializerByType(LocalDate.class, LocalDateDeserializer.INSTANCE)
                .serializerByType(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .deserializerByType(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                // LocalDateTime < - > EpochMillis
                .serializerByType(LocalDateTime.class, TimestampLocalDateTimeSerializer.INSTANCE)
                .deserializerByType(LocalDateTime.class, TimestampLocalDateTimeDeserializer.INSTANCE);
    }

    /**
     * 以 Bean 形式暴露 Module（Boot 会自动注册到所有 ObjectMapper）
     */
    @Bean
    public Module timestampSupportModuleBean() {
        SimpleModule m = new SimpleModule("TimestampSupportModule");
        // Long -> Number
        m.addSerializer(Long.class, NumberSerializer.INSTANCE);
        m.addSerializer(Long.TYPE, NumberSerializer.INSTANCE);
        // LocalDate / LocalTime
        m.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
        m.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
        m.addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE);
        m.addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE);
        // LocalDateTime < - > EpochMillis
        m.addSerializer(LocalDateTime.class, TimestampLocalDateTimeSerializer.INSTANCE);
        m.addDeserializer(LocalDateTime.class, TimestampLocalDateTimeDeserializer.INSTANCE);
        return m;
    }

    /**
     * 初始化全局 JsonUtils，直接使用主 ObjectMapper
     */
    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public JsonUtils jsonUtils(ObjectMapper objectMapper) {
        JsonUtils.init(objectMapper);
        log.debug("[init][初始化 JsonUtils 成功]");
        return new JsonUtils();
    }

    /**
     * WebFlux 场景：强制默认编解码器使用同一个 ObjectMapper
     */
    @Bean
    public CodecCustomizer unifyJackson(ObjectMapper om) {
        return configurer -> {
            Jackson2JsonDecoder decoder = new Jackson2JsonDecoder(om);
            Jackson2JsonEncoder encoder = new Jackson2JsonEncoder(om);
            configurer.defaultCodecs().jackson2JsonDecoder(decoder);
            configurer.defaultCodecs().jackson2JsonEncoder(encoder);
        };
    }
}
