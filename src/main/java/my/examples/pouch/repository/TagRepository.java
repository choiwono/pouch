package my.examples.pouch.repository;

import my.examples.pouch.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    @Query("SELECT t FROM Tag t inner join fetch t.links" +
           " WHERE t.email=:email")
    List<Tag> findMyTagListByAccountId(@Param("email") String email);
}
