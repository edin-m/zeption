package io.leres.example.posts.services;

import io.leres.example.posts.PostCuder;
import io.leres.example.posts.PostFinder;

interface PostService extends PostFinder, PostCuder {
}
