package com.subhan.springbootrestapi.repository;

import com.subhan.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
