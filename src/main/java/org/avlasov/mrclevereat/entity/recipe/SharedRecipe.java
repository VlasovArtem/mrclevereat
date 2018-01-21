package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.image.Image;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeComplexity;
import org.avlasov.mrclevereat.entity.social.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created By artemvlasov on 21/01/2018
 **/
@Entity
public class SharedRecipe extends Base {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shared_recipe_id")
    private Recipe recipe;
    private int likes;
    private RecipeComplexity complexity;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_recipe_id")
    private List<Image> images;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_recipe_id")
    private List<Comment> comments;

    SharedRecipe() {}

    SharedRecipe(Recipe recipe, int likes, RecipeComplexity complexity, List<Image> images, List<Comment> comments) {
        this.recipe = recipe;
        this.likes = likes;
        this.complexity = complexity;
        this.images = images;
        this.comments = comments;
    }

    public Recipe getRecipe() {
        return Recipe.builder(recipe).build();
    }

    public int getLikes() {
        return likes;
    }

    public RecipeComplexity getComplexity() {
        return complexity;
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(Optional.ofNullable(images).orElseGet(Collections::emptyList));
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(Optional.ofNullable(comments).orElseGet(Collections::emptyList));
    }

    public static SharedRecipeBuilder getBuilder() {
        return new SharedRecipeBuilder();
    }

    public static SharedRecipeBuilder getBuilder(SharedRecipe sharedRecipe) {
        return new SharedRecipeBuilder(sharedRecipe);
    }

    public static SharedRecipeBuilder getBuilder(Recipe recipe) {
        return new SharedRecipeBuilder(recipe);
    }

    public static class SharedRecipeBuilder {

        private Recipe recipe;
        private int likes;
        private List<Image> images;
        private List<Comment> comments;
        private RecipeComplexity complexity;

        SharedRecipeBuilder() {}

        SharedRecipeBuilder(SharedRecipe sharedRecipe) {
            this.recipe = sharedRecipe.recipe;
            this.likes = sharedRecipe.likes;
            this.images = sharedRecipe.images;
            this.comments = sharedRecipe.comments;
            this.complexity = sharedRecipe.complexity;
        }

        SharedRecipeBuilder(Recipe recipe) {
            this.recipe = recipe;
        }

        public SharedRecipeBuilder recipe(Recipe recipe) {
            this.recipe = recipe;
            return this;
        }

        public SharedRecipeBuilder likes(int likes) {
            this.likes = likes;
            return this;
        }

        public SharedRecipeBuilder complexity(RecipeComplexity complexity) {
            this.complexity = complexity;
            return this;
        }

        public SharedRecipeBuilder images(List<Image> images) {
            this.images = images;
            return this;
        }

        public SharedRecipeBuilder addImage(Image image) {
            this.images = Optional.ofNullable(this.images).orElseGet(ArrayList::new);
            images.add(image);
            return this;
        }

        public SharedRecipeBuilder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public SharedRecipeBuilder addComment(Comment comment) {
            this.comments = Optional.ofNullable(this.comments).orElseGet(ArrayList::new);
            comments.add(comment);
            return this;
        }

        public SharedRecipe build() {
            return new SharedRecipe(recipe, likes, complexity, images, comments);
        }

    }

}
