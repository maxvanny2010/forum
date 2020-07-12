package forum.repository;

import forum.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * AuthorityRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/1/2020
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Query("SELECT a FROM Authority a WHERE a.authority=?1")
    Authority findByAuthority(String authority);
}
