package org.bs.jpa.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDTO {
    
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

}
