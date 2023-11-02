package org.bs.jpa.repository;

import org.bs.jpa.domain.Board;
import org.bs.jpa.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch{

    
    
}
