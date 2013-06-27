package cs235.avl;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.Collection;

public class Test extends SetImpl {

	/**
	 * Run tests on AVL trees.
	 */

	private static String[] listA = { "fungible", "tautology", "epiphany",
			"fisticuffs", "enigma", "alabaster", "debacle", "bogus" };
	private static String[] listB = { "heresy", "bombastic", "sentient",
			"blitzkrieg", "infernal", "euphoric", "plethora", "azure" };
	private static String[] listC = { "cacophony", "lollygag", "luscious",
			"yurt", "macabre", "soporific", "ergo", "pedantic" };

	public static void main(String[] args) {

		testIterator();
		testContainsAndSize();
		testRemove();
		testExceptions();

	}

	private static void testIterator() {

		SetImpl s = (SetImpl) SetFactory.createSet();
		for (int i = 0; i < listA.length; i++)
			s.add(listA[i]);
		
		
		Iterator it = s.iterator();
		while (it.hasNext())
			System.out.println(it.next());
		

	}

	private static void testContainsAndSize() {
		SetImpl s = (SetImpl) SetFactory.createSet();
		System.out.println("Size: " + s.size());

		String[] listA = { "fungible", "tautology", "epiphany", "fisticuffs",
				"enigma", "alabaster", "debacle", "bogus" };

		printListContains(s, listA);

		for (int i = 0; i < listA.length; i++)
			s.add(listA[i]);

		System.out.println("Size: " + s.size());
		printListContains(s, listA);
		s.clear();
		printListContains(s, listA);
	}

	public static void printListContains(SetImpl s, String[] l) {
		for (int i = 0; i < l.length; i++) {
			if (s.contains(l[i]))
				System.out.println("Found");
			else
				System.out.println("Not Found");
		}
		System.out.println();
	}

	private static void testRemove() {
		SetImpl s = (SetImpl) SetFactory.createSet();

		for (int i = 0; i < listC.length; i++)
			s.add(listC[i]);
		for (int i = 0; i < listA.length; i++)
			s.add(listA[i]);

		for (int i = 0; i < 1; i++)
			s.remove(listC[i]);
		// for (int i = 0; i < listB.length; i++)
		// s.remove(listB[i]);
		System.out.println("Size after remove: " + s.size());
		printListContains(s, listC);
		printListContains(s, listA);

		// for (int i = 0; i < listA.length; i++)
		// s.remove(listA[i]);
		printListContains(s, listA);
		System.out.println("Size after 2nd remove: " + s.size());
	}

	private static void testExceptions() {
		SetImpl s = (SetImpl) SetFactory.createSet();
		Collection c = new ArrayList<String>();

		try {
			s.addAll(c);
		} catch (UnsupportedOperationException e) {
			System.out.println("\"addAll\" method not supported.");
		}
		try {
			s.containsAll(c);
		} catch (UnsupportedOperationException e) {
			System.out.println("\"containsAll\" method not supported.");
		}
		try {
			s.isEmpty();
		} catch (UnsupportedOperationException e) {
			System.out.println("\"isEmpty\" method not supported.");
		}
		try {
			s.removeAll(c);
		} catch (UnsupportedOperationException e) {
			System.out.println("\"removeAll\" method not supported.");
		}
		try {
			s.retainAll(c);
		} catch (UnsupportedOperationException e) {
			System.out.println("\"retainAll\" method not supported.");
		}
		try {
			s.toArray();
		} catch (UnsupportedOperationException e) {
			System.out.println("\"toArray\" method not supported.");
		}
		try {
			s.toArray(listB);
		} catch (UnsupportedOperationException e) {
			System.out.println("\"toArray(Object)\" method not supported.");
		}
	}

}
