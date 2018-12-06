package io.leres.forum;

import io.leres.entities.forum.ForumEntry;
import io.leres.entities.forum.ForumThread;
import io.leres.entities.forum.ForumUser;

public interface ThreadReplier {

    ForumEntry reply(ForumThread thread, ForumUser user);

}
