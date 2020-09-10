package forum.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    private String username;
    private String password;
    private boolean enable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id",
            foreignKey = @ForeignKey(name = "authority_id_fk"),
            nullable = false)
    private Authority authority;

    public User() {
    }

    public User(final Long aId, final String aUsername,
                final String aPassword, final Authority aAuthority) {
        this.id = aId;
        this.username = aUsername;
        this.password = aPassword;
        this.authority = aAuthority;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(final boolean aEnable) {
        this.enable = aEnable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String aUsername) {
        this.username = aUsername;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String aPassword) {
        this.password = aPassword;
    }

    public Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(final Authority aAuthority) {
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
        User that = (User) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    /**
     * Method to check new user or not.
     *
     * @return result
     */
    public final boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public final int hashCode() {
        return (int) (this.getId() == null ? 0 : this.getId());
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                User.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("username='" + this.username + "'")
                .add("password='" + this.password + "'")
                .add("authority=" + this.authority)
                .toString();
    }
}
