package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.reljicd.model.Follow;

import java.util.Optional;
import java.util.Set;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUsername(@Param("username") String username);
    Set<Follow> findAllByUsername(@Param("username") String username);
    Optional<Follow> findByUsernameAndFollowusername(@Param("username") String username, @Param("follow_username") String followusername);
}
