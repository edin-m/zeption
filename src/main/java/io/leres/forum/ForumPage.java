package io.leres.forum;

import io.leres.entities.forum.ForumEntry;
import lombok.Data;

import java.util.List;

@Data
public class ForumPage {

    private int currentPage;

    private int maxPage;

    private List<ForumEntry> entries;

}
