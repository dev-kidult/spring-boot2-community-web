package com.devkidult.communityweb.repo;

import com.devkidult.communityweb.domain.Board;
import com.devkidult.communityweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board,Long> {
    Board findByUser(User user);
}
