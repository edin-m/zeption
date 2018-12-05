package io.leres.util;

import java.util.ArrayList;
import java.util.List;

public abstract class ListUtils {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

}
