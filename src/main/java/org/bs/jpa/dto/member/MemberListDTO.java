package org.bs.jpa.dto.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<String> roles = new ArrayList<>();


}
