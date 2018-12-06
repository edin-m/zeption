package io.leres.class_room;

import io.leres.class_room.exceptions.ClassRoomNotFound;
import io.leres.entities.ClassRoom;

public interface ClassRoomFinder {

    ClassRoom getClassRoomById(long classRoomId) throws ClassRoomNotFound;
}
