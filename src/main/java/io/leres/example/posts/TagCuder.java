package io.leres.example.posts;

import io.leres.exceptions.ResourceNotFound;

import java.util.Set;

public interface TagCuder {

    Tag createTag(String tagName);

    Tag updateTag(String oldTagName, String newTagName) throws ResourceNotFound;

    void deleteTag(String tagName) throws ResourceNotFound;

    Set<Tag> getOrCreateTags(Set<String> tags);
}
