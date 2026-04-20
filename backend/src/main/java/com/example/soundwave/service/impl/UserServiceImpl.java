package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.UserDto;
import com.example.soundwave.entity.User;
import com.example.soundwave.exception.ResourceNotFoundException;
import com.example.soundwave.repository.UserRepository;
import com.example.soundwave.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getRandomUsers() {
        return userRepository.findTop50ByOrderByUsernameAsc().stream()
                .map(UserServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> searchUsers(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        String needle = query.trim();
        return userRepository
                .findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(needle, needle)
                .stream()
                .map(UserServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        UUID id = parseUserId(userId);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        return toDto(user);
    }

    private static UUID parseUserId(String userId) {
        try {
            return UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
    }

    private static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
