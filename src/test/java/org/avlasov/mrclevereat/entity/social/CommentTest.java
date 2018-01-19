package org.avlasov.mrclevereat.entity.social;

import org.avlasov.mrclevereat.entity.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class CommentTest {

    @Test
    void getComment() {
        assertEquals("Test", getCommentData().getComment());
    }

    @Test
    void getOwner() {
        assertNotNull(getCommentData().getOwner());
    }

    @Test
    void equals() {
        assertTrue(getCommentData().equals(getCommentData()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getCommentData().hashCode(), getCommentData().hashCode());
    }

    private Comment getCommentData() {
        return new Comment("Test", User.builder("test", "test".getBytes()).build());
    }

}