package io.leres.example.posts;

import io.leres.exceptions.ResourceNotFound;

public interface TagFinder {

    Tag getByTagName(String tagName) throws ResourceNotFound;

}
