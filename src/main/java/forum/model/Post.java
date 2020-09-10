package forum.model;

import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Post.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created;
    private String author;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "post")
    private Set<Message> messages = new HashSet<>();

    public Post() {
    }

    public Post(final Long aId, final String aName, final String aDesc,
                final LocalDateTime aCreated, final String aAuthor) {
        this.id = aId;
        this.name = aName;
        this.description = aDesc;
        this.created = aCreated;
        this.author = aAuthor;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(final Set<Message> messages) {
        this.messages = messages;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String aAuthor) {
        this.author = aAuthor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String aDesc) {
        this.description = aDesc;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(final LocalDateTime aCreated) {
        this.created = aCreated;
    }

    public final boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        final Post that = (Post) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return (int) (this.getId() == null ? 0 : this.getId());
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                "\n" + Post.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name='" + this.name + "'")
                .add("description='" + this.description + "'")
                .add("created=" + this.created)
                .add("author=" + this.author)
                //.add("messages=" + this.messages)
                .toString();
    }
}
