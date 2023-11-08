package org.bs.jpa.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "bs_member")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String regDate;
    private boolean deleteFlag;

    // queryDsl 조인하기 위해 양방향매핑
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberRole> roles = new ArrayList<>();

    // 생성 시간 포맷 변경
    @PrePersist
    public void onPrePersist() {

        // 현재 날짜와 시간 가져오기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 사용할 형식 지정 (날짜, 오후/오전, 시간)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd a h:mm");

        // 날짜와 시간을 문자열로 형식화
        String formattedDateTime = currentDateTime.format(formatter);

        // 날짜와 시간을 저장
        this.regDate = formattedDateTime;
    }

    // member update
    public void memberUpdate(String password, String nickname, String phoneNumber) {

        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;

    }

    // member delete
    public void memberDelete(){

        this.deleteFlag = true;
    }

}
