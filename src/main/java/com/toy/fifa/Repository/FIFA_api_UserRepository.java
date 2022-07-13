package com.toy.fifa.Repository;

import com.toy.fifa.Entity.FIFA_user_info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FIFA_api_UserRepository extends JpaRepository<FIFA_user_info,String> {
}
