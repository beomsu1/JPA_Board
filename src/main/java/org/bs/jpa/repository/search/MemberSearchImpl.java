package org.bs.jpa.repository.search;

import java.util.List;

import org.bs.jpa.domain.Member;
import org.bs.jpa.domain.QMember;
import org.bs.jpa.domain.QMemberRole;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch {

    public MemberSearchImpl() {
        super(Member.class);
    }

    // 검색 , Querydsl
    @Override
    public PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequestDTO) {

        QMember member = QMember.member;
        QMemberRole role = QMemberRole.memberRole;

        JPQLQuery<Member> query = from(member);

        query.leftJoin(member).on(member.email.eq(role.member.email));

        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        if (type != null && keyword != null) {

            String arr[] = type.split("");

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String arrType : arr) {

                switch (arrType) {

                    case "e" -> booleanBuilder.or(member.email.contains(keyword));
                    case "n" -> booleanBuilder.or(member.nickname.contains(keyword));
                    case "p" -> booleanBuilder.or(member.phoneNumber.contains(keyword));
                    case "r" -> booleanBuilder.or(role.role.contains(keyword));
                }
            }

            query.where(booleanBuilder);

        }

        Pageable pageable = makePageable(pageRequestDTO);

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<MemberListDTO> listQuery = query.select(Projections.bean(
            MemberListDTO.class,
            member.email,
            member.nickname,
            member.phoneNumber,
            member.regDate,
            member.roles));

        List<MemberListDTO> dtoList = listQuery.fetch();

        Long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

}
