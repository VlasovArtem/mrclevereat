package org.avlasov.mrclevereat.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @CreatedBy
    private long createdBy;
    @LastModifiedBy
    private long modifiedBy;
    private boolean isDeleted;

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base)) return false;
        Base base = (Base) o;
        return id == base.id &&
                createdBy == base.createdBy &&
                modifiedBy == base.modifiedBy &&
                isDeleted == base.isDeleted &&
                Objects.equals(createdDate, base.createdDate) &&
                Objects.equals(modifiedDate, base.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, modifiedDate, createdBy, modifiedBy, isDeleted);
    }
}
