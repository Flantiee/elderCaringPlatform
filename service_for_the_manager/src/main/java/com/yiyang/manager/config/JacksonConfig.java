package com.yiyang.manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigInteger;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 全局配置序列化
        SimpleModule simpleModule = new SimpleModule();
        // 使用String来序列化Long包装类
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        // 使用String来序列化long基本类型
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        // 使用String来序列化BigInteger包装类类型。（有人用BigInteger表示大的整数）
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}