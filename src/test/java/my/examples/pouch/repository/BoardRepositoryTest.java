package my.examples.pouch.repository;

import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Board;
import my.examples.pouch.domain.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    TagRepository tagRepository;

    @Test
    public void init() {
    }

    @Test
    public void findAll() throws Exception {
        List<Board> all = boardRepository.findAll();
        for (Board list : all) {
            System.out.println(list.getTitle() + " " + list.getUrl());
        }
    }

    //email 주소에 해당하는 board 목록을 가져온다
    @Test
    public void boardByEmail() throws Exception {
        List<Board> all = boardRepository.boardByEmail("jaehee@gmail.com");
        for (Board list : all) {
            System.out.println(list.getTitle() + " " + list.getUrl());
        }
    }

    // email 주소에 해당하는 repo 목록을 가져온다
    @Test
    public void repositoryByEmail() throws Exception {
        List<Board> all = boardRepository.repositoryByEmail("jaehee@gmail.com");
        for (Board list : all) {
            System.out.println(list.getTitle() + " " + list.getUrl());
        }
    }

    // email 주소에 해당하는 즐겨찾기 목록을 가져온다
    @Test
    public void favoriteByEmail() throws Exception {
        List<Board> all = boardRepository.favoriteByEmail("jaehee@gmail.com");
        for (Board list : all) {
            System.out.println(list.getTitle() + " " + list.getUrl());
        }
    }

    // board id에 해당하는 태그 목록을 가져온다
    @Test
    public void getTagByBoardId() throws Exception {
        List<Tag> all = boardRepository.getTagByBoardId(4L);
        for (Tag list : all) {
            System.out.println(list.getTagName());
        }
    }

}
