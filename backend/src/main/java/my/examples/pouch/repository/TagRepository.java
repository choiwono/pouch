package my.examples.pouch.repository;

import my.examples.pouch.domain.Tag;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    /*@Query(value = "SELECT distinct t FROM Tag t, Link l " +
                   "inner join fetch t.links " +
                    "where l.email = :email and l.category.id = :id")*/

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
}
