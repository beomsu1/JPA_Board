package org.bs.jpa.dto.member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class MemberDTO extends User {

    private String email;
    private String password;
    private String nickname;
    private List<String> roles = new ArrayList<>();

    public MemberDTO(String email, String password, String nickname, List<String> roles) {

        // SimpleGrantedAuthority -> 권한을 나타내는데 사용, 권한 부여
        super(email, password,
                roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));

        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
