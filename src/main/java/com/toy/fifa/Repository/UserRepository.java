package com.toy.fifa.Repository;

import com.toy.fifa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
}
