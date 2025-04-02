package com.nilsw13.spring_boot.replicate.ResponseType.Collection;

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

    /**
     * Default constructor for CollectionModel class.
     *
     * This empty constructor exists for the following reasons:
     * 1. Required by JSON/Jackson deserialization process when mapping API responses
     * 2. Enables library users to instantiate response objects when needed
     * 3. Supports serialization/deserialization in various client implementations
     *
     * Although empty, this constructor is essential for the proper functioning
     * of the API client library and should not be removed.
     */
    public CollectionModel() {
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
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
