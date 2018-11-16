package com.devkidult.communityweb;

import com.devkidult.communityweb.domain.Board;
import com.devkidult.communityweb.domain.User;
import com.devkidult.communityweb.domain.enums.BoardType;
import com.devkidult.communityweb.repo.BoardRepo;
import com.devkidult.communityweb.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepo userRepo;

    @Autowired
    BoardRepo boardRepo;

    @Before
    public void init() {
        User user = userRepo.save(
                User.builder()
                        .name("devkidult")
                        .password("test")
                        .email(email)
                        .createdDate(LocalDateTime.now())
                        .build()
        );

        boardRepo.save(
                Board.builder()
                        .title(boardTestTitle)
                        .subtitle("서브 타이틀")
                        .content("콘텐츠")
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user)
                        .build()
        );
    }

    @Test
    public void Test() {
        User user = userRepo.findByEmail(email);
        assertThat(user.getName(), is("devkidult"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepo.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubtitle(), is("서브 타이틀"));
        assertThat(board.getContent(), is("콘텐츠"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }
}
