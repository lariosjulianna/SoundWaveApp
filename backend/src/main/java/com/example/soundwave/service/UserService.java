package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.UserDto;

import java.util.List;


/**
 * Service interface for user-related business logic
 *
 * This layer abstracts where user data comes from
 * (db, cache...)
 */

//! interface v class

public interface UserService {

    /**
     * Returns a list of random users
     *
     * @return list of UserDto
     */

    List<UserDto> getRandomUsers();


    /**
     * Search for users by name
     *
     * @param query search keyword
     * @return list of matching users
     */

    List<UserDto> searchUsers(String query);


    /**
     * Fetch a single user by Id
     *
     * @param userId userId
     * @return UserDto
     */

    UserDto getUserById(String userId);

}
