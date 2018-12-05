package io.leres.classes;

import io.leres.entities.UniClass;

public interface ClassManager {

    UniClass createUniClass(String name, String description);

    void saveExisting(UniClass uniClass);
}
