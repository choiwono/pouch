package my.examples.pouch.repository;

import my.examples.pouch.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT B FROM Link B WHERE B.email=:email AND B.repository=0")
    List<Link> boardByEmail(@Param("email") String email);

    @Query("SELECT B FROM Link B WHERE B.email=:email AND B.repository=1")
    List<Link> repositoryByEmail(@Param("email") String email);

    /*@Query("SELECT B.tags FROM Link B inner join fetch B.tags where B.id=:id")
    List<Tag> getTagsByLinkId(@Param("id") Long id);*/

    @Query("SELECT DISTINCT L FROM Link L inner join fetch L.category" +
           " left join fetch L.tags"+
           " WHERE L.category.id=:categoryId and L.email=:email")
    List<Link> getMyPouchByCategory(@Param("categoryId") Long categoryId,@Param("email") String email);

    /*@Query(value = "SELECT L FROM Link L " +
                   "INNER join fetch L.tags tags " +
                   "WHERE L.category.id=:categoryId and tags.tagName like CONCAT('%',:tagName,'%')")*/
    @Query(value = "SELECT l.* FROM Link l " +
                   "INNER JOIN tag_mapping tp ON L.id=tp.board_id " +
                   "INNER JOIN tag t ON tp.tag_id=t.id " +
                   "WHERE l.category_id=:categoryId AND t.tag_name LIKE CONCAT('%',:tagName,'%')",
                   nativeQuery = true)
    List<Link> getLinkByTagName(@Param("categoryId") Long categoryId,@Param("tagName") String tagName);

    @Modifying
    @Transactional
    @Query(value = "DELETE tp FROM tag_mapping AS tp WHERE tp.board_id=:linkId", nativeQuery = true)
    void deleteTagMappingByLinkId(@Param("linkId") Long linkId);

    @Query("SELECT DISTINCT L FROM Link L WHERE L.category.id=:categoryId")
    List<Link> getSearchPouchByCategory(@Param("categoryId") Long categoryId);
}
