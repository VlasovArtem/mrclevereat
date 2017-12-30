package org.avlasov.mrclevereat.entity.social;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * Created By artemvlasov on 26/12/2017
 **/
@Entity
public class Comment extends Base {

    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    public Comment(String comment, User owner) {
        this.comment = comment;
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(comment, comment1.comment) &&
                Objects.equals(owner, comment1.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, owner);
    }
}
