package com.devkidult.communityweb;

import com.devkidult.communityweb.domain.Board;
import com.devkidult.communityweb.domain.User;
import com.devkidult.communityweb.domain.enums.BoardType;
import com.devkidult.communityweb.repo.BoardRepo;
import com.devkidult.communityweb.repo.UserRepo;
import com.devkidult.communityweb.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class CommunityWebApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(CommunityWebApplication.class, args);
    }

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    @Bean
    public CommandLineRunner runner(UserRepo userRepo, BoardRepo boardRepo) throws Exception {
        return (args -> {
            User user = userRepo.save(
                    User.builder()
                            .name("devkidult")
                            .password("test")
                            .email("devkidult@gmail.com")
                            .createdDate(LocalDateTime.now())
                            .build()
            );

            IntStream.rangeClosed(1, 200).forEach(index ->
                    boardRepo.save(
                            Board.builder()
                                    .title("게시글" + index)
                                    .subtitle("순서" + index)
                                    .content("콘텐츠")
                                    .boardType(BoardType.free)
                                    .createdDate(LocalDateTime.now())
                                    .updatedDate(LocalDateTime.now())
                                    .user(user)
                                    .build()
                    )
            );
        });
    }
}
