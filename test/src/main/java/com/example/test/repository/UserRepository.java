package com.example.test.repository;

import com.example.test.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT s FROM User s WHERE (:nickname is null or s.nickname = :nickname) " +
            "and (:nif is null or s.nif = :nif) " +
            "and (:status is null or s.status = :status) ")
    Page<User> findAllByFields(@Param("nickname") String nickname,
                               @Param("nif") String nif,
                               @Param("status") UserStatus status,
                               Pageable pageable);
}
