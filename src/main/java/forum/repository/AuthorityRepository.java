package forum.repository;

import forum.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthorityRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/1/2020
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthority(String authority);
}
