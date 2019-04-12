package my.examples.pouch.repository;

import my.examples.pouch.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long>{
    @Query("SELECT A FROM Account A WHERE A.email=:email")
    Account findMyAccount(@Param("email") String email);

    @Query("SELECT A FROM Account A inner join fetch A.accountCategories " +
           "WHERE A.email=:email")
    Account findMyCategoryList(@Param("email") String email);
}
