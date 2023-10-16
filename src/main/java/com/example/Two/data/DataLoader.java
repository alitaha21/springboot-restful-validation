package com.example.Two.data;

import com.example.Two.model.Post;
import com.example.Two.model.User;
import com.example.Two.repository.PostRepository;
import com.example.Two.repository.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;

    public DataLoader(UserRepository userRepository, PostRepository postRepository, PasswordEncoder passwordEncoder, Faker faker) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Add a valid user to login, and posts...");
        User user = new User("user", passwordEncoder.encode("password"), "admin,user");
        List<Post> postList = IntStream.range(1, 100)
                        .mapToObj(i -> new Post(
                                faker.book().title(),
                                faker.howIMetYourMother().catchPhrase(),
                                faker.book().author()
                        )).toList();
        postRepository.saveAll(postList);
        userRepository.save(user);
    }
}
