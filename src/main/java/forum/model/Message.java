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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Message.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long id;
    private String description;
    private LocalDateTime created;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",
            foreignKey = @ForeignKey(name = "post_id_fk"),
            nullable = false)
    private Post post;

    public Message() {
    }

    public Message(final Long aId, final String aDesc,
                   final LocalDateTime aDateTime,
                   final String aAuthor) {
        this.id = aId;
        this.description = aDesc;
        this.created = aDateTime;
        this.author = aAuthor;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(final Post post) {
        this.post = post;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String aDesc) {
        this.description = aDesc;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(final LocalDateTime aDateTime) {
        this.created = aDateTime;
    }

    public boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                "\n" + Message.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("desc='" + this.description + "'")
                .add("dateTime=" + this.created)
                .add("author=" + this.author)
                .toString();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        final Message that = (Message) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return (int) (this.getId() == null ? 0 : this.getId());
    }
}
