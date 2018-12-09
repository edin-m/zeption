package io.leres.example.posts.services;

import io.leres.example.posts.CommentCuder;
import io.leres.example.posts.CommentFinder;

interface CommentService extends CommentFinder, CommentCuder {
}
