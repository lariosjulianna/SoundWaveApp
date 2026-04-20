package com.example.soundwave.repository;

import com.example.soundwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsername(String username);

    List<User> findTop50ByOrderByUsernameAsc();

    List<User> findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String namePart, String usernamePart);
}
