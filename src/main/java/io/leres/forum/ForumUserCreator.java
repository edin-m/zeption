package io.leres.forum;

import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.forum.ForumUser;

public interface ForumUserCreator {

    ForumUser createForumUser(Student student);

    ForumUser createForumUser(Teacher teacher);

}
