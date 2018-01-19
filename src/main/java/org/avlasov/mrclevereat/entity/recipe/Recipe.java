package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.image.Image;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.RecipeProduct;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeComplexity;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;
import org.avlasov.mrclevereat.entity.social.Comment;
import org.avlasov.mrclevereat.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Entity
@Table(indexes = {
        @Index(name = "RCP_NAME_IDX", columnList = "name")
})
public class Recipe extends Base {

    private boolean isShared;
    private int likes;
    private double volume;
    @Column(unique = true)
    private String name;
    private String description;
    @Embedded
    private NutritionalValue nutritionalValue;
    @Enumerated(EnumType.STRING)
    private RecipeVisibility recipeVisibility;
    private RecipeComplexity complexity;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private List<Image> images;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private List<RecipeProduct> recipeProducts;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private List<Comment> comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    Recipe() {
    }

    Recipe(boolean isShared, int likes, double volume, String name, String description, NutritionalValue nutritionalValue, RecipeVisibility recipeVisibility, RecipeComplexity complexity, List<Image> images, List<RecipeProduct> recipeProducts, List<Comment> comments) {
        this.isShared = isShared;
        this.likes = likes;
        this.volume = volume;
        this.name = name;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.recipeVisibility = recipeVisibility;
        this.complexity = complexity;
        this.images = images;
        this.recipeProducts = recipeProducts;
        this.comments = comments;
    }

    public boolean isShared() {
        return isShared;
    }

    public int getLikes() {
        return likes;
    }

    public double getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public RecipeVisibility getRecipeVisibility() {
        return recipeVisibility;
    }

    public RecipeComplexity getComplexity() {
        return complexity;
    }

    public List<Image> getImages() {
        return new ArrayList<>(images);
    }

    public List<RecipeProduct> getRecipeProducts() {
        return new ArrayList<>(recipeProducts);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static RecipeBuilder builder(Recipe recipe) {
        return new RecipeBuilder(recipe);
    }

    public static class RecipeBuilder {

        private boolean isShared;
        private int likes;
        private double volume;
        private String name;
        private String description;
        private NutritionalValue nutritionalValue;
        private RecipeVisibility recipeVisibility;
        private RecipeComplexity complexity;
        private List<Image> images;
        private List<RecipeProduct> recipeProducts;
        private List<Comment> comments;

        RecipeBuilder(Recipe recipe) {
            isShared = recipe.isShared;
            likes = recipe.likes;
            volume = recipe.volume;
            name = recipe.name;
            description = recipe.description;
            nutritionalValue = recipe.nutritionalValue;
            recipeVisibility = recipe.recipeVisibility;
            complexity = recipe.complexity;
            images = recipe.images;
            recipeProducts = recipe.recipeProducts;
            comments = recipe.comments;
        }

        RecipeBuilder() {}

        public RecipeBuilder isShared(boolean isShared) {
            this.isShared = isShared;
            return this;
        }

        public RecipeBuilder likes(int likes) {
            this.likes = likes;
            return this;
        }

        public RecipeBuilder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public RecipeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder nutritionalValue(NutritionalValue nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public RecipeBuilder recipeVisibility(RecipeVisibility recipeVisibility) {
            this.recipeVisibility = recipeVisibility;
            return this;
        }

        public RecipeBuilder complexity(RecipeComplexity complexity) {
            this.complexity = complexity;
            return this;
        }

        public RecipeBuilder images(List<Image> images) {
            this.images = images;
            return this;
        }

        public RecipeBuilder mealProducts(List<RecipeProduct> mealProducts) {
            this.recipeProducts = mealProducts;
            return this;
        }

        public RecipeBuilder addMealProduct(RecipeProduct recipeProduct) {
            if (Objects.isNull(recipeProducts))
                recipeProducts = new ArrayList<>();
            recipeProducts.add(recipeProduct);
            return this;
        }

        public RecipeBuilder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Recipe build() {
            return new Recipe(isShared, likes, volume, name, description, nutritionalValue, recipeVisibility, complexity, images, recipeProducts, comments);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return isShared == recipe.isShared &&
                likes == recipe.likes &&
                Double.compare(recipe.volume, volume) == 0 &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(nutritionalValue, recipe.nutritionalValue) &&
                recipeVisibility == recipe.recipeVisibility &&
                complexity == recipe.complexity &&
                Objects.equals(images, recipe.images) &&
                Objects.equals(recipeProducts, recipe.recipeProducts) &&
                Objects.equals(comments, recipe.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isShared, likes, volume, name, description, nutritionalValue, recipeVisibility, complexity, images, recipeProducts, comments);
    }
}
