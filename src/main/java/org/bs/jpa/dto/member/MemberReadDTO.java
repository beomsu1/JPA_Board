package org.bs.jpa.dto.member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bs.jpa.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadDTO {

    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String regDate;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void entityToDTO(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.regDate = member.getRegDate();
        this.roles = member.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
    }
    
}
