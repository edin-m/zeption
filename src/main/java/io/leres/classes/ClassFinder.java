package io.leres.classes;

import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.entities.UniClass;

import java.time.Instant;

public interface ClassFinder {

    UniClass getUniClassForId(long uniClassId) throws UniClassNotFound;

    int findWeekOfClass(UniClass uniClass, Instant forTime);

}
