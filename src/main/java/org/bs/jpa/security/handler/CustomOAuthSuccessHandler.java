package org.bs.jpa.security.handler;

import java.io.IOException;
import java.util.stream.Collectors;

import org.bs.jpa.dto.member.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
// AuthenticationSuccessHandler -> 사용자 인증 성공 시 수행할 작업을 정의하는 인터페이스
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        MemberDTO dto = (MemberDTO) authentication.getPrincipal();

        log.info("MemberDTO: " + dto);
        log.info("ROLE: " + dto.getRoles().stream().map(role -> role).collect(Collectors.toList()));

        // 비밀번호가 null, 빈 공백일 시 수정페이지로 이동
        if (dto.getPassword() == null || dto.getPassword().equals("")) {
            response.sendRedirect("/member/update");
        }

    }

}
