package org.bs.jpa.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bs.jpa.dto.comment.CommentDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "bs_comment")
@Getter
@Builder
@ToString(exclude = "board") // board는 제외
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 생명주기
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Long cno; // 댓글 번호
    private String comments; // 댓글 내용
    private String commenter; // 댓글 작성자
    private boolean deleteFlag; // 삭제 플래그
    private boolean updateFlag; // 수정 플래그

    @CreatedDate // 생성시간 기준
    private String regDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    public Board board; // FK(Board)가 관계의 주인 -> ManyToOne

    // 댓글 수정
    public void updateCommnent(CommentUpdateDTO commentUpdateDTO){

        this.comments = commentUpdateDTO.getComments();
        this.commenter = commentUpdateDTO.getCommeters();
        this.updateFlag = commentUpdateDTO.isUpdateFlag();
    }

    // 댓글 삭제
    public void deleteComment(){
        this.comments = "DELETE";
        this.commenter = "DELETE";
        this.deleteFlag = true;
    }

    // DTO -> Entity
    public Comment dtoTOEntity(CommentDTO commentDTO){

        Comment comment = Comment.builder()
        .cno(commentDTO.getCno())
        .comments(commentDTO.getCommnets())
        .commenter(commentDTO.getCommenter())
        .regDate(commentDTO.getRegDate())
        .deleteFlag(commentDTO.isDeleteFlag())
        .updateFlag(commentDTO.isUpdateFlag())
        .build(); 

        return comment;

    }

}
