package org.bs.jpa.security;

import java.util.List;
import java.util.Map;

import org.bs.jpa.dto.member.MemberDTO;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

// OAuth2 제공자는 OAuth2User 객체 형식으로 사용자 정보를 반환
// DefaultOAuth2UserService는 이 사용자 정보를 가져오는 역할
@Service
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("-----------------");
        log.info("userRequest: " + userRequest);

        // 현재 사용자의 클라이언트 등록 정보 가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        // 클라이언트 이름 가져오기
        String clientName = clientRegistration.getClientName();
        log.info("clientName: " + clientName);
        log.info("-----------------");

        // 부모 클래스에서 상속된 메서드를 호출하여, userRequest를 사용해 OAuth2.0 기반의 사용자 정보를 가져옴
        // userRequest는 OAuth2.0 인증 요청에 대한 정보를 담고있음
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 속성 Map 형식으로 담아주기
        Map<String, Object> attribute = oAuth2User.getAttributes();

        log.info("attribute: " + attribute);
        log.info("-----------------");

        String email = null;

        // 이메일 뽑아내기
        switch (clientName) {
            case "kakao":
                email = kakaoEmail(attribute);
                break;
        }

        log.info(email);

        MemberDTO memberDTO = new MemberDTO(email, "", "kakao", List.of("ROLE_USER"));

        return memberDTO;
    }

    private String kakaoEmail(Map<String,Object> attribute){

        // JSON, MAP 인지 몰라서 Object 타입으로 받기
        Object value = attribute.get("kakao_account");

        // JSON -> MAP으로 파싱
        Map account = (Map) value;

        // email 받기
        String email = (String) account.get("email");

        return email;
    }

    
}
