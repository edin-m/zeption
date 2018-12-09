package io.leres.classes;

import io.leres.entities.UniClass;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;

public interface UniClassCuder {

    UniClass createUniClass(UniClass example) throws ResourceAlreadyExists;

    UniClass update(long uniClassId, UniClass example) throws ResourceNotFound;

    void remove(long uniClassId) throws ResourceNotFound;

//    UniClass createUniClass(String name, String description);

//    void saveExisting(UniClass uniClass);
}
