package io.leres.homework;

import io.leres.students.Student;
import io.leres.entities.homework.HomeworkAssignment;

public interface HomeworkSubmitter {

    void submitHomework(HomeworkAssignment assignment, Student student, String data);

}
