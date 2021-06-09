package com.notifyforall.api.repository;

import com.notifyforall.api.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByEmail(String email);

    Optional<UserApp> findByUsername(String username);
}
