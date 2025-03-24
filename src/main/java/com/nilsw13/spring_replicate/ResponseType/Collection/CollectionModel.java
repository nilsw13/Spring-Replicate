package com.nilsw13.spring_replicate.ResponseType.Collection;

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
