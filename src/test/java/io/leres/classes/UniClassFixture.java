package io.leres.classes;

import io.leres.entities.UniClass;

public abstract class UniClassFixture {

    public static UniClass getDefaultClass() {
        return new UniClass("CS-101", "Intro");
    }
}
