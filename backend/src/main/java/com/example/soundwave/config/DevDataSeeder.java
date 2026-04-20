package com.example.soundwave.config;

import com.example.soundwave.entity.Post;
import com.example.soundwave.entity.User;
import com.example.soundwave.repository.PostRepository;
import com.example.soundwave.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

// @Component = Spring automatically creates this class when the app starts
@Component
// CommandLineRunner = Special interface that runs code ONCE when app starts up
public class DevDataSeeder implements CommandLineRunner { 
   
    // Need access to database to create users and posts
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    // Spring automatically gives us these repositories when creating this class
    public DevDataSeeder(UserRepository userRepository, PostRepository postRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // This method runs automatically when the app starts
    @Override 
    @Transactional  // All database operations happen together (all or nothing)
    public void run(String... args) {
        // Check: Are there already users in the database?
        if (userRepository.count() > 0) {
            return; // If there are users, skip creating new ones
        }

        // If we get here, database is empty, so create sample data...
        // Create some sample users for testing
        // Store ONLY hashed passwords in the database.
        // NOTE: this is just dev seed data; the raw password is never persisted.
        User tiff = new User("Tiff", "tiff", "tiff@example.com", passwordEncoder.encode(""));
        User leaf = new User("Leaf", "leaf", "leaf@example.com", passwordEncoder.encode(""));
        
        // Save the users to the database
        userRepository.save(tiff);
        userRepository.save(leaf);

        // Create a post by Tiff about a SONG (with a fake MusicBrainz ID)
        Post songPost = new Post(
                tiff, // author
                UUID.fromString("22222222-2222-2222-2222-222222222222"), // songID (fake)
                Post.TopicType.SONG, // topicType
                "Fancy That", // topicName (song name)
                "This song is insane 🔥" // content
        );
        // Create a post by Leaf about an ALBUM (with a fake MusicBrainz ID)
        Post albumPost = new Post(
                leaf, // author
                UUID.fromString("aaaaaaaa-bbbb-aaaa-aaaa-aaaaaaaaaaaa"), // albumID (fake)
                Post.TopicType.ALBUM, // topicType
                "After Hours", // topicName (album name)
                "This album is insane 🔥" // content
        );

        // Save the posts to the database
        postRepository.save(songPost);
        postRepository.save(albumPost);

        // Now when you visit /posts or /users, you'll see this sample data!
    }
}
