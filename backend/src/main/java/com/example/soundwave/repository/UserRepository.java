package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import com.example.soundwave.entity.User;


public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsername(String username);
}
