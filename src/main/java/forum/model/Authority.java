package forum.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Authority.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authority")
    private Long id;

    private String authority;

    public Authority() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(final String aAuthority) {
        this.authority = aAuthority;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        final Authority that = (Authority) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return (int) (this.getId() == null ? 0 : this.getId());
    }
}
