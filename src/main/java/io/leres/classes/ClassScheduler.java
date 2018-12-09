package io.leres.classes;

import io.leres.entities.UniClass;

import java.time.Instant;

public interface ClassScheduler {

    UniClass scheduleExam(UniClass uniClass, Instant dateStart, Instant dateEnd);

}
