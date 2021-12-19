package com.example.jwt;

import com.example.jwt.domain.Role;
import com.example.jwt.domain.User;
import com.example.jwt.service.UserService;
//import com.mysql.cj.jdbc.CommentClientInfoProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args->{
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null,"Elon Musk","musk","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Jim Carry","jim","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Tod Govards","tod","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Bruce Wayne","bruce","1234",new ArrayList<>()));

            userService.addRole2User("musk","ROLE_USER");
            userService.addRole2User("musk","ROLE_MANAGER");
            userService.addRole2User("bruce","ROLE_SUPER_ADMIN");
            userService.addRole2User("jim","ROLE_MANAGER");
            userService.addRole2User("tod","ROLE_ADMIN");
            userService.addRole2User("tod","ROLE_USER");

        };
    }
}
