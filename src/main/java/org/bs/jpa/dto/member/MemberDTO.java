package org.bs.jpa.dto.member;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String regDate;

    @Builder.Default
    private List<String> roles = new ArrayList<>();
    
}
