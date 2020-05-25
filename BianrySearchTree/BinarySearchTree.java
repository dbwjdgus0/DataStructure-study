import java.util.*;

// Name : À¯Á¤Çö
// Student ID : 20151142

@SuppressWarnings("unchecked")
class BST <T extends KeyValue> {
	
	

	class TreeNode <U extends KeyValue> {
		U data;	// storage for data : in HW 3, T will be Item
		TreeNode<U> leftChild;	// link to the left Child
		TreeNode<U> rightChild;	// link to the right Child

		// constructors come here
		TreeNode() {
			leftChild = rightChild = null;
		}
		TreeNode(U d) {
			// data is given
			data = d;
			// the leftChild and rightChild field are null
			leftChild = rightChild = null;
		}
	};

	TreeNode <T> root;// the reference to the root node

	BST() { 
		// BST constructor. 
		root = null;
		
	}

    void Show() {

		System.out.print( "Pre  Order : ");
		PreOrder(root);
		System.out.println("");
		System.out.print("In   Order : ");
		InOrder(root);
		System.out.println("");
		System.out.print("Post Order : ");
		PostOrder(root);
		System.out.println("");
		System.out.print("Count      : ");
		System.out.print( Count(root));
		System.out.println("");
		System.out.print("Height      : ");
		System.out.println( Height(root));
		System.out.println("");
		
	}


	// IMPLEMENT THE FOLLOWING FUNCTIONS

	boolean  Insert(T item)  {
		// first search the key
		if(root == null) {
		
			root = new TreeNode<>(item);
			return true;
		}
		else
		{
			TreeNode<T> ptr = root;
			TreeNode<T> parent;
			
			while(true)
			{
				if(ptr.data.GetKey() == item.GetKey())
				{
					ptr.data = item;
					return false;
				}
				else if(ptr.data.GetKey() > item.GetKey())
				{
					if(ptr.leftChild == null)
					{
						ptr.leftChild = new TreeNode<>(item);
						break;
					}
					else 
					{
						ptr = ptr.leftChild;
					}
					
				}
				else
				{
					if(ptr.rightChild == null)
					{
						ptr.rightChild = new TreeNode<>(item);
						break;
					}
					else 
					{
						ptr = ptr.rightChild;
					}
				}
				
			}
			
			return true;
			

		}

		



		
	}

	T Get(T item)  {
		// use the key field of item and find the node
		// do not use val field of item
		TreeNode<T> ptr;
		ptr = root;
		
		int key = item.GetKey();
		
		while(true)
		{	
			if(ptr == null)
			{
				return null;
			}
			else if(ptr.data.GetKey() == key)
			{
				break;
			}
			else if(ptr.data.GetKey() < key)
			{
				ptr = ptr.rightChild;
			}
			else
			{
				ptr = ptr.leftChild;
			}
		}

	

		return ptr.data;
	} 


	boolean Delete(T item)  {
		
		if(root == null)
			return false;	// non existing key
		
		TreeNode<T> ptr = root;
		TreeNode<T> parent = null;
		
		
		
		while(true)
		{
			if(ptr == null)
			{
				return false;
			}
			else if(ptr.data.GetKey() == item.GetKey())
			{
				break;
			}
			
			else if(ptr.data.GetKey() > item.GetKey())
			{
				parent = ptr;
				ptr = ptr.leftChild;
			}
			else if(ptr.data.GetKey() < item.GetKey())
			{
				parent = ptr;
				ptr = ptr.rightChild;
			}
		}
		
		if(ptr == root)
		{
			int degree = 0;
			if(ptr.leftChild != null) degree++;
			if(ptr.rightChild != null) degree++;
			
			if(degree == 2)
			{
				T newdata = RightMinNode(ptr);
				Delete(newdata);
				TreeNode<T> newnode = new TreeNode<>(newdata);
				root = newnode;
				newnode.leftChild = ptr.leftChild;
				newnode.rightChild = ptr.rightChild;
	
			}
			else if(degree == 1)
			{
				if(ptr.leftChild != null) root = ptr.leftChild;
				
				else root = ptr.rightChild;	
			}
			else
			{
				root = null;
			}
			
			
		}
		else
		{
			int degree = 0;
			if(ptr.leftChild != null) degree++;
			if(ptr.rightChild != null) degree++;
			
			if(degree == 2)
			{
				T newdata = RightMinNode(ptr);
				Delete(newdata);
				TreeNode<T> newnode = new TreeNode<>(newdata);
				if(isLeft(parent, ptr))
				{
					parent.leftChild = newnode;
					newnode.leftChild = ptr.leftChild;
					newnode.rightChild = ptr.rightChild;
					
				}
				else
				{
					parent.rightChild = newnode;
					newnode.leftChild = ptr.leftChild;
					newnode.rightChild = ptr.rightChild;
				
				}
				
			}
			else if(degree == 1)
			{
				if(ptr.leftChild != null)
				{
					if(isLeft(parent, ptr))
					{
						parent.leftChild = ptr.leftChild;
					}
					else
					{
						parent.rightChild = ptr.leftChild;
					}
					
				}
				else
				{
					if(isLeft(parent, ptr))
					{
						parent.leftChild = ptr.rightChild;
					}
					else
					{
						parent.rightChild = ptr.rightChild;
					}
				}
			}
			else
			{
				if(isLeft(parent, ptr)) parent.leftChild = null;
				else parent.rightChild = null;
			}
			
			
		}
		
		

		return true;
	}
	
	boolean isLeft(TreeNode<T> parent , TreeNode<T> child)
	{
		if(parent.data.GetKey() > child.data.GetKey()) return true;
		else return false;
	}
	
	T RightMinNode(TreeNode<T> t)
	{
		t = t.rightChild;
		
		while(t.leftChild != null)
		{
			t = t.leftChild;
		}
		
		return t.data;
	}
	
	

	void  PreOrder(TreeNode<T> t)  {

		TreeNode<T> ptr = t;
		if(ptr != null)
		{
			System.out.print(ptr.data.GetKey() + "(" + 
								ptr.data.GetValue() + ")" + " " );
			PreOrder(ptr.leftChild);
			PreOrder(ptr.rightChild);
		}
		

	}

	void  InOrder(TreeNode<T> t)  {

		TreeNode<T> ptr = t;
		if(ptr != null)
		{
			
			InOrder(ptr.leftChild);
			System.out.print(ptr.data.GetKey() + "(" + 
					ptr.data.GetValue() + ")" + " " );
			InOrder(ptr.rightChild);
		}	

	}

	void  PostOrder(TreeNode<T> t)  {

		TreeNode<T> ptr = t;
		if(ptr != null)
		{
			
			PostOrder(ptr.leftChild);
			PostOrder(ptr.rightChild);
			System.out.print(ptr.data.GetKey() + "(" + 
					ptr.data.GetValue() + ")" + " " );
		}	

	}

	int  Count(TreeNode<T> t)  {

		int cnt = 0;
		
		TreeNode<T> ptr = t;
		if(ptr != null)
		{
			cnt += 1 + Count(ptr.leftChild) + Count(ptr.rightChild);
		}
		
		return cnt;
	}

	int  Height(TreeNode<T> t)  {

		int hgt = 0;
		
		TreeNode<T> ptr = t;
		if(ptr != null)
		{
			int l = Height(ptr.leftChild);
			int r = Height(ptr.rightChild);
			if(l > r)
			{
				hgt += 1 + l;
			}
			else
			{
				hgt += 1 + r;
			}
		}
		
		
		return hgt;
	}
}


