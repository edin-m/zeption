package io.leres.classes;

import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.entities.UniClass;

public interface ClassFinder {

    UniClass getUniClassForId(long uniClassId) throws UniClassNotFound;

}
