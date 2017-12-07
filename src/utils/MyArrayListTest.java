package utils;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MyArrayListTest {

	@Test
	public void testRemoveAndReturnFirstIfEquals() {
		MyArrayList<Integer> l = new MyArrayList<>(Arrays.asList(1,2,3,4,5));
		
		int first = l.removeAndReturnFirst(1);
		
		assertEquals(first, 1);
		assertTrue(l.size() == 4);
		assertTrue(l.get(0) == 2);
	}

	@Test
	public void testRemoveFirstIfEquals() {
		MyArrayList<Integer> l = new MyArrayList<>(Arrays.asList(1,2,3,4,5));
		
		assertTrue(l.removeFirst(1));
		assertTrue(l.removeFirst(2));
		assertTrue(l.removeFirst(3));
		assertTrue(l.removeFirst(4));
		assertTrue(l.removeFirst(5));
	}

}
