package com.userservice.repository;

import com.userservice.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
