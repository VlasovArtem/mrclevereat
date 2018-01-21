package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.image.Image;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeComplexity;
import org.avlasov.mrclevereat.entity.social.Comment;
import org.avlasov.mrclevereat.entity.user.User;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created By artemvlasov on 21/01/2018
 **/
class SharedRecipeTest {

    @Test
    void defaultConstructor() {
        new SharedRecipe();
    }

    @Test
    void getLikes() {
        assertEquals(10, getSharedRecipeBuilder().build().getLikes());
    }

    @Test
    void getRecipe() {
        assertNotNull(getSharedRecipeBuilder().build().getRecipe());
    }

    @Test
    void getComplexity() {
        assertEquals(RecipeComplexity.HIGH, getSharedRecipeBuilder().build().getComplexity());
    }

    @Test
    void getImages() {
        assertThat(getSharedRecipeBuilder().build().getImages(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void getComments() {
        assertThat(getSharedRecipeBuilder().build().getComments(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void getBuilder() {
        assertNotNull(getSharedRecipeBuilder());
    }

    @Test
    void getBuilder_WithSharedRecipe() {
        SharedRecipe build = SharedRecipe.getBuilder(getSharedRecipeBuilder().build())
                .likes(300)
                .build();
        assertEquals(300, build.getLikes());

    }

    @Test
    void getBuilder_WithRecipe() {
        SharedRecipe build = SharedRecipe.getBuilder(Recipe.builder().name("hello").build())
                .comments(Collections.singletonList(new Comment("test", User.builder("test", "test".getBytes()).build())))
                .images(Collections.singletonList(new Image()))
                .build();
        assertEquals("hello", build.getRecipe().getName());
    }

    private SharedRecipe.SharedRecipeBuilder getSharedRecipeBuilder() {
        return SharedRecipe.getBuilder()
                .recipe(Recipe.builder().name("name").build())
                .complexity(RecipeComplexity.HIGH)
                .addComment(new Comment("test", User.builder("test", "test".getBytes()).build()))
                .addImage(new Image())
                .likes(10);
    }

}