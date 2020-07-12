package forum.model;

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
public class Post extends AbstractEntity<Integer> {
    /**
     * field a id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Integer id;
    /**
     * field a name.
     */
    private String name;
    /**
     * field a description.
     */
    private String description;

    /**
     * field a time to create.
     */
    private LocalDateTime created;

    /**
     * field a author.
     */
    private String author;
    /**
     * field a messages.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "post")
    private Set<Message> messages = new HashSet<>();

    /**
     * Constructor.
     */
    public Post() {
    }

    /**
     * Constructor.
     *
     * @param aId      id
     * @param aName    name
     * @param aDesc    description
     * @param aCreated create date and time
     */
    public Post(final Integer aId, final String aName, final String aDesc,
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

    /**
     * Method to get.
     *
     * @return author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Method to set.
     *
     * @param aAuthor a author
     **/
    public void setAuthor(final String aAuthor) {
        this.author = aAuthor;
    }

    /**
     * Method to get.
     *
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Method to set.
     *
     * @param aId id
     */
    public void setId(final Integer aId) {
        this.id = aId;
    }

    /**
     * Method to set.
     *
     * @param aId id
     **/
    public void setId(final int aId) {
        this.id = aId;
    }

    /**
     * Method to get.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set.
     *
     * @param aName name of post
     **/
    public void setName(final String aName) {
        this.name = aName;
    }

    /**
     * Method to get.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to set.
     *
     * @param aDesc description
     **/
    public void setDescription(final String aDesc) {
        this.description = aDesc;
    }

    /**
     * Method to get.
     *
     * @return a time of create
     */
    public LocalDateTime getCreated() {
        return this.created;
    }

    /**
     * Method to set.
     *
     * @param aCreated created
     **/
    public void setCreated(final LocalDateTime aCreated) {
        this.created = aCreated;
    }

    /**
     * Method to check post.
     *
     * @return is new post or not
     */
    public boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        final Post post = (Post) o;
        return Objects.equals(getId(), post.getId())
                && Objects.equals(getName(), post.getName())
                && Objects.equals(getDescription(), post.getDescription())
                && Objects.equals(getCreated(), post.getCreated())
                && Objects.equals(getAuthor(), post.getAuthor());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(),
                getCreated(), getAuthor());
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
                .add("messages=" + this.messages)
                .toString();
    }
}
