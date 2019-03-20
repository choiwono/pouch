package my.examples.pouch.repository;

import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT T.boards FROM Tag T WHERE T.id=:id")
    List<Link> getBoardByTagId(@Param("id") Long id);

//    @Query("SELECT B FROM Link B WHERE B.email=:email AND B.repository=0")
//    List<Link> boardByEmail(@Param("email") String email);

//@Query("SELECT t FROM Tag t inner join fetch t.boards where t.boards.id=:id")
//    List<Tag> getTagByBoard(@Param("id") Long id);
}


//SELECT b.tag_name FROM tag_mapping a
  //      INNER JOIN tag b ON a.tag_id=b.id
    //    WHERE a.board_id=1