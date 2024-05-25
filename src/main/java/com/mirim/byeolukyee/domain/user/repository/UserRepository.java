package com.mirim.byeolukyee.domain.user.repository;

import com.mirim.byeolukyee.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsUserByEmail(String email);
}
