package com.yiyang.manager.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        //config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，自定义可以添加多个，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        //用上面那个要G
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");// #允许访问的头信息,*表示全部，可以添加多个
        config.setMaxAge(1800L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，一般OPTIONS,GET,POST三个够了
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);//对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 优先级最高
        return bean;
    }
}