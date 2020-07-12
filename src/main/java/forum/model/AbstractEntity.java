package forum.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

/**
 * AbstractEntity.
 *
 * @param <ID> type
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/28/2020
 */
public abstract class AbstractEntity<ID> implements Persistable<ID> {
    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
