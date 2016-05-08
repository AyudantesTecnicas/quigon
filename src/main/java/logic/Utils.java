package logic;

import java.util.List;

public class Utils {
    public static <T> void addToCollection(T item, List<T> collection) {
        if (item != null) {
            if (!collection.contains(item)){
                collection.add(item);
            }
        }
    }
}
