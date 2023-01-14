package com.fbk.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author 房博坤
 * @Date 2023/1/14 21:58
 * @Version 1.0.1
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter getCorsFilter() {
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.addAllowedOrigin("*");//允许哪些方法
        configuration.addAllowedOrigin("*");//允许哪些表示设置ip
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
