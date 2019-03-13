package my.examples.pouch.repository;

import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Board;
import my.examples.pouch.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT B FROM Board B WHERE B.email=:email AND B.repository=0")
    List<Board> boardByEmail(@Param("email") String email);

    @Query("SELECT B FROM Board B WHERE B.email=:email AND B.repository=1")
    List<Board> repositoryByEmail(@Param("email") String email);

    @Query("SELECT B FROM Board B WHERE B.email=:email AND B.favorites=1")
    List<Board> favoriteByEmail(@Param("email") String s);

    @Query("SELECT B.tags FROM Board B where B.id=:id")
    List<Tag> getTagByBoardId(@Param("id") Long id);


}
