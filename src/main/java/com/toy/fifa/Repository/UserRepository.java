package com.toy.fifa.Repository;

import com.toy.fifa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String name);
}
