package io.leres.forum;

import io.leres.entities.UniClass;
import io.leres.entities.forum.ForumThread;
import io.leres.entities.forum.ForumUser;

public interface ThreadCreator {

    ForumThread createThread(UniClass uniClass, ForumUser forumUser);

}
