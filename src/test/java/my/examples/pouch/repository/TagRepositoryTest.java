package my.examples.pouch.repository;

import my.examples.pouch.domain.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    public void init() {
    }

    // tag id에 해당하는 board 목록을 가져온다
    @Test
    public void getBoardByTagId() throws Exception{
        List<Link> all = tagRepository.getBoardByTagId(1L);
        for (Link list : all) {
            System.out.println(list.getTitle());
        }
    }
}
