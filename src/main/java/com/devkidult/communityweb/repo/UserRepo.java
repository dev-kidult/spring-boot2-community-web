package com.devkidult.communityweb.repo;

import com.devkidult.communityweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
