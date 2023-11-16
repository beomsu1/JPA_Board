package org.bs.jpa.config;

import org.bs.jpa.security.handler.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        // loginPage
        http.formLogin(config -> config.loginPage("/member/login")

                // 로그인 성공 시 mypage로 이동
                .defaultSuccessUrl("/member/mypage", true));

        // csrf 비활성화
        http.csrf(config -> config.disable());

        // AccessDenied
        http.exceptionHandling(config -> config.accessDeniedHandler(new CustomAccessDeniedHandler("/member/login")));

        return http.build();

    }

}
