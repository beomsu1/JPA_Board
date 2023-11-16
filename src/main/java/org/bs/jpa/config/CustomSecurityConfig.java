package org.bs.jpa.config;

import javax.sql.DataSource;

import org.bs.jpa.security.handler.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

    // 데이터베이스와의 연결을 관리하는 인터페이스
    private final DataSource dataSource;

    // rememberME
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        // 로그인 정보를 db에 저장, 관리에 사용
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();

        // db 연결
        repo.setDataSource(dataSource);
        return repo;
    }

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

        // rememberME
        http.rememberMe(
                config -> config.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60 * 60 * 24));

        return http.build();

    }

}
