package andy.example.commons.utils;

import java.util.Collection;

/**
 * Collection Utility.
 *
 * @author Anand Shinde
 */

public class CollectionUtil {
  private CollectionUtil() {
  }

  /**
   * Checks if collection is empty.
   *
   * @param collection - instance of collection
   * @return true if collection is null or contains no item otherwise returns false
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Checks if collection is not empty.
   *
   * @param collection - instance of collection
   * @return true if collection contains items > 0 otherwise returns false
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}