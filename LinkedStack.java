import java.util.Iterator;

//i use this class for pairSet

public class LinkedStack<Item> implements Iterable<Item>{
	
	private Node first = null;
	private int count=0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void push(Item item) {
		if(item==null)throw new NullPointerException("You can not pop from void massive");
		Node oldFirst = first;
		first = new Node();
		first.item=item;
		first.next=oldFirst;
		count++;
	}

	public Item pop() {
		if(first==null)throw new ArrayIndexOutOfBoundsException("You can not pop from void");
		Item item = first.item;
		first = first.next;
		count--;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();		
		}
		
	}

}