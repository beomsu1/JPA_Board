package org.bs.jpa.repository;

import org.bs.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
