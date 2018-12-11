package io.leres.forum;

import io.leres.students.Student;
import io.leres.teachers.Teacher;
import io.leres.entities.forum.ForumUser;

public interface ForumUserCreator {

    ForumUser createForumUser(Student student);

    ForumUser createForumUser(Teacher teacher);

}
