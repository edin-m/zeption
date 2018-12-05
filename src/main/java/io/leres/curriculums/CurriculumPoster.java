package io.leres.curriculums;

import io.leres.entities.Teacher;
import io.leres.entities.UniClass;

public interface CurriculumPoster {

    void addTextMessageToCurriculum(Teacher teacher, UniClass uniClass, int weekOfCurriculum, String description);

}
