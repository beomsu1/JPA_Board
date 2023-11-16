package org.bs.jpa.dto.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberDTO extends User implements OAuth2User {

    private String email;
    private String password;
    private String nickname;
    private List<String> roles = new ArrayList<>();

    public MemberDTO(String email, String password, String nickname, List<String> roles) {
        super(email, password,
                roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    // OAuth2 제공자로부터 가져온 사용자에 관한 속성 정보를 포함한 맵을 반환하는 역할
    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    // 이메일 반환
    @Override
    public String getName() {

        return email;
    }
}
