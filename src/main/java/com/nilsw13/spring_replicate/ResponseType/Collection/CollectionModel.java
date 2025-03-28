package com.nilsw13.spring_replicate.ResponseType.Collection;

/**
 * Represents a curated collection of models in the Replicate API.
 *
 * Collections in Replicate group related models together, providing an organized
 * way to discover models that serve similar purposes or belong to the same category.
 * Each collection has a descriptive name, a unique slug identifier, and a detailed
 * description of its purpose or contents.
 *
 * Collections are useful for browsing related models and discovering new capabilities
 * within specific domains like image generation, text processing, or audio manipulation.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class CollectionModel {
    private String name;
    private String slug;
    private String description;

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CollectionModel{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
