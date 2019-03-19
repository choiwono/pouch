package my.examples.pouch.repository;

import my.examples.pouch.domain.AccountCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountCategoryRepository extends JpaRepository<AccountCategory,Long>{
    @Query("SELECT c FROM AccountCategory c WHERE c.id=:id")
    AccountCategory findMyCategory(@Param("id") Long id);
}
