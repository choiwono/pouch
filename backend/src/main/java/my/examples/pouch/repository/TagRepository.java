package my.examples.pouch.repository;

import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.custom.CustomTagDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    @Query(value = "SELECT t.id AS id,t.tag_name AS tagName,COUNT(*) AS cnt FROM tag t " +
                   "inner join tag_mapping tp ON t.id=tp.tag_id " +
                   "inner join link li ON tp.board_id=li.id " +
                   "WHERE t.email=:email AND li.category_id=:id "+
                   "GROUP BY t.tag_name",nativeQuery = true)
    List<CustomTagDto> findMyTagListByAccountId(@Param("email") String email, @Param("id") Long id);

    @Query(value = "SELECT t.id AS id,t.tag_name AS tagName,COUNT(*) AS cnt FROM tag t " +
            "inner join tag_mapping tp ON t.id=tp.tag_id " +
            "inner join link li ON tp.board_id=li.id " +
            "WHERE li.category_id=:id "+
            "GROUP BY t.tag_name",nativeQuery = true)
    List<CustomTagDto> findTagListByCategoryId(@Param("id")Long categoryId);

    @Query(value = "SELECT T FROM Tag T INNER JOIN FETCH T.links l" +
            " WHERE l.id=:linkId")
    List<Tag> findTagListByLinksId(@Param("linkId") Long linkId);

    @Query(value="SELECT T FROM Tag T WHERE T.id=:id")
    Tag findTagById(@Param("id") Long id);
}
