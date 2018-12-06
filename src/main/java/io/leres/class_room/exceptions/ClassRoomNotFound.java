package io.leres.class_room.exceptions;

public class ClassRoomNotFound extends Exception {
    public ClassRoomNotFound(long classRoomId) {
        super(String.format("Class Room %s not found", classRoomId));
    }
}
