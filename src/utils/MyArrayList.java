package utils;

import java.util.ArrayList;
import java.util.Collection;

public class MyArrayList<E> extends ArrayList<E> implements MyList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MyArrayList(){
		super();
	}
	
	public MyArrayList(Collection<E> c) {
		super(c);
	}
	
	@Override
	public E removeAndReturnFirst(E e) {
		E elem = null;
		
		if(!isEmpty()) {
			if(get(0).equals(e)) {
				elem = remove(0);
			}
		}
		return elem;
	}

	@Override
	public boolean removeFirst(E e) {
		if(isEmpty() || !get(0).equals(e)) {
			return false;
		}
		else {
			remove(0);
			return true;
		}
	}

}
