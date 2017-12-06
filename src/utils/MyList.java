package utils;

import java.util.List;
/**
 * 
 * @author Etienne Onasch
 *
 * @param <E>
 */
public interface MyList<E> extends List<E> {
	/**
	 * Removes the first element and returns it if
	 * it is equal to e.
	 * If the list is empty or the first element 
	 * is not equal to e returns null.
	 * @param e
	 * @return
	 */
	E removeAndReturnFirstIfEquals(E e);
	
	/**
	 * Removes the first element and returns true if
	 * it is equal to e.
	 * If the list is empty or the first element 
	 * is not equal to e returns false.
	 * @param e
	 * @return
	 */
	boolean removeFirstIfEquals(E e);
	
	
}
