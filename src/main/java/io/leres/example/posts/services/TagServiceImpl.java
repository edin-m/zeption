package io.leres.example.posts.services;

import io.leres.example.posts.Tag;
import io.leres.example.posts.repo.TagRepository;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(String tagName) {
        return getOrCreateTag(tagName);
    }

    @Override
    public Tag updateTag(String oldTagName, String newTagName) throws ResourceNotFound {
        Tag tag = getByTagName(oldTagName);
        tag.setName(newTagName);
        return tag;
    }

    @Override
    public void deleteTag(String tagName) throws ResourceNotFound {
        Tag tag = getByTagName(tagName);
        tagRepository.delete(tag);
    }

    @Override
    public Set<Tag> getOrCreateTags(Set<String> tags) {
        Set<Tag> allTags = new HashSet<>();

        for (String tagName : tags) {
            allTags.add(createTag(tagName));
        }

        return allTags;
    }

    private Tag getOrCreateTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName);

        if (tag == null) {
            tag = new Tag(tagName);
            tag = tagRepository.save(tag);
        }

        return tag;
    }

    @Override
    public Tag getByTagName(String tagName) throws ResourceNotFound {
        Tag tag = tagRepository.findByName(tagName);

        if (tag == null) {
            throw new ResourceNotFound(Tag.class, tagName);
        }

        return tag;
    }
}
