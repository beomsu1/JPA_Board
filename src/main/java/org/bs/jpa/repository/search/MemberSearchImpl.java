package org.bs.jpa.repository.search;


import org.bs.jpa.domain.Member;
import org.bs.jpa.domain.QMember;
import org.bs.jpa.domain.QMemberRole;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch{

    public MemberSearchImpl(){
        super(Member.class);
    }


    @Override
    public PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequestDTO) {

        QMember member = QMember.member;
        QMemberRole role = QMemberRole.memberRole;

        JPQLQuery<Member> query = from(member);
        
        return null;
    }

    
    
}
