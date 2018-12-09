package io.leres.example.controllers;

import io.leres.example.posts.Tag;
import io.leres.example.posts.TagCuder;
import io.leres.example.posts.TagFinder;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private TagFinder tagFinder;
    private TagCuder tagCuder;

    public TagController(TagFinder tagFinder, TagCuder tagCuder) {
        this.tagFinder = tagFinder;
        this.tagCuder = tagCuder;
    }

    @PostMapping("/tags/{tagName}")
    public Tag createTag(@PathVariable("tagName") String tagName) {
        return tagCuder.createTag(tagName);
    }

    @PutMapping("/tags/{oldTagName}/{newTagName}")
    public Tag updateTag(@PathVariable("oldTagName") String oldTagName,
                         @PathVariable("newTagName") String newTagName) throws ResourceNotFound {
        return tagCuder.updateTag(oldTagName, newTagName);
    }

    @DeleteMapping("/tags/{tagName}")
    public ResponseEntity<?> deleteTag(@PathVariable("tagName") String tagName) throws ResourceNotFound {
        tagCuder.deleteTag(tagName);
        return ResponseEntity.ok().build();
    }
}
