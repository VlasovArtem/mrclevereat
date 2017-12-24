package org.avlasov.mrclevereat.entity;

import org.avlasov.mrclevereat.entity.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Base {

    @Id
    private ObjectId id;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @CreatedBy
    private User createdBy;
    @LastModifiedBy
    private User modifiedBy;
    private boolean isDeleted;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base)) return false;
        Base base = (Base) o;
        return isDeleted == base.isDeleted &&
                Objects.equals(id, base.id) &&
                Objects.equals(createdDate, base.createdDate) &&
                Objects.equals(modifiedDate, base.modifiedDate) &&
                Objects.equals(createdBy, base.createdBy) &&
                Objects.equals(modifiedBy, base.modifiedBy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdDate, modifiedDate, createdBy, modifiedBy, isDeleted);
    }
}
