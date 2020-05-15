import java.util.*;
import java.lang.reflect.*;

/**
 * Generic version of the MinHeap class.
 * @param <T> the type of the value being added
 */

class MinHeap <T extends Comparable> {
	private T[] heapArray;
	private int heapSize;	// number of heap elements

	/**
	 * Create an empty MinHeap of size capacity
	 */
	MinHeap(Class<T> elemType, int capacity) {
		heapArray = (T []) Array.newInstance(elemType, capacity);
		heapSize = 0;
	}


	/**
	 * Insert item into the MinHeap
	 */
	void Insert (T item) { 
		
		if(heapSize == 0) 
		{
			heapSize++;
			heapArray[1] = item;
		}
		else
		{
			int temp = ++heapSize;
			heapArray[heapSize] = item;
			while(true )
			{
				if(temp != 1 && heapArray[temp].compareTo(heapArray[temp/2]) < 0)
				{
					heapArray[temp] = heapArray[temp/2];
					heapArray[temp/2] = item;
					temp /= 2;
				}
				else
				{
					break;
				}
			}
			
		}
		


	}

	void PostOrder (final int idx) { 

			int me = idx;
			int ch1 = idx * 2;
			int ch2 = idx * 2 + 1;

			if(ch1 <= heapSize)
			{
				PostOrder(ch1);
			}
			if(ch2 <= heapSize)
			{
				PostOrder(ch2);
			}
			
			System.out.print(heapArray[me] + " ");
			

	}


	public String toString() {
		String a = new String();
        a = "Min Heap : - ";
        for(int i = 1; i <= heapSize; i++) {
            a += heapArray[i] + "  ";
        }
        return a;
    }
}; 