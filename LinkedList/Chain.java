import java.util.*;

// LinkedList practice
// 


class Chain <T> {
	class ChainNode <U> {
		private U data;	// storage for data
		private ChainNode<U> link;	// link to the next node

		// constructors come here
		ChainNode() {
			this.link = null;
			// the link field is null 
		}
		ChainNode(U data) {
			// set the data field only 
			this.data = data;
		}
		ChainNode(U data, ChainNode<U> link) {
			// set the data field and link field
			this.data = data;
			this.link = link;
		}
	};

	private ChainNode<T> first; // reference to the fist node

	Chain() { 
		first = null;
	}

	boolean IsEmpty() {return first == null;}

	/**
	 * Show all the elements in the Chain in sequence
	 */
	public String toString() { 
		ChainNode<T> p = first;

		String str = new String();
		str = String.format("List (%d) : ", Size());

		// show all the nodes
		while (p != null) {
			str += p.data + " ";
			p = p.link;
		}
		return str;
	}



	/**
	 * delete an element from the first 
	 */
	T DeleteFront() {
		if(IsEmpty()) { return null;}
		ChainNode <T> p = first;
		
		 T tempdata= p.data;
		 
		 first = p.link;
		
		return tempdata;
	}

	/**
	 * return the number of elements in Chain
	 */
	final int  Size() {
		ChainNode<T> p = first;
		int cnt = 0;
		if(IsEmpty())
		{
			return 0;
		}
		do
		{
			cnt++;
			p = p.link;
		}while(p != null);
	
		return cnt;
	}
	
	/**
	 * insert theElement in theIndex
	 */

	boolean Insert(int theIndex, T theElement) {

		ChainNode<T> p = first;
		if(theIndex > this.Size()) return false;
		else {
		if(theIndex == 0)
		{
			first = new ChainNode<T>(theElement , first);
		}
		else {
		
				for(int i =0 ; i < theIndex-1 ; i++)
					{
						p = p.link;
					}
				p.link = new ChainNode<T> (theElement, p.link);
			}
			
		}
		
		return true;
		}
	
}
	


