package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Account;
import my.examples.shoppingmall.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.name=:name")
    Role getRoleByName(@Param("name") String name);
}