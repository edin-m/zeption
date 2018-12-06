package io.leres.forum.exceptions;

public class ThreadNotFound extends Exception {
    public ThreadNotFound(long threadId) {
        super(String.format("Thread %s not found", threadId));
    }
}
