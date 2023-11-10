package org.bs.jpa.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberListDTO {

    private String email;
    private String nickname;
    private String phoneNumber;
    private String regDate;
    private String role;

}
