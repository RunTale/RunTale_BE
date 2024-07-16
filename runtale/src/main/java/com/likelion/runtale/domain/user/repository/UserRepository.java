package com.likelion.runtale.domain.user.repository;

import com.likelion.runtale.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLoginId(String loginId);
}
