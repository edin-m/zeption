package io.leres.classes;

import io.leres.entities.UniClass;

public interface ClassCruder {

    UniClass createUniClass(String name, String description);

    void saveExisting(UniClass uniClass);
}
