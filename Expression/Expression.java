import java.util.*;

/*
 * from Infix expression -> postfix expression
 * and eval
 */


	class Expression {
		
		static String operators[] = { "+" , "-" , "*" , "/"};
		static String numbers[] = { "0" , "1" , "2" , "3" , "4", "5"
							, "6" , "7" , "8" , "9" };
		
		
		static void stackprint(Stack<String> s)
		{
			if(!s.empty())
			{
				int size = s.size();
			
				String arr[] = new String[size];
			
				for(int i = size -1 ; i >=0 ; i--)
				{
					arr[i] = s.pop();
				}
				for(int i = 0 ; i < size ; i++)
				{
					System.out.print(arr[i] + " ");
					s.push(arr[i]);
				}
			}
			System.out.println("");
			
		}
		
		static void vecprint(Vector<String> v)
		{
			int n = v.size();
			System.out.print("[");
			for(int i = 0 ; i < n ; i++)
			{	if(i == n-1) System.out.print(v.get(i) + "]");
				else System.out.print(v.get(i) + " ");
			}
			System.out.println("");
		}
		
		
		static boolean isOp(String op)
		{
			boolean ret = false;
			for(int i = 0 ; i < operators.length ; i++)
			{	
				if(operators[i].equals(op)) 
				{
					ret = true;
					break;
				}
					
			}
			return ret;
		}
		static boolean isNum(String num)
		{
			boolean ret = false;
			for(int i = 0 ; i < numbers.length ; i++)
			{	
				if(numbers[i].equals(num)) 
				{
					ret = true;
					break;
				}
					
			}
			return ret;
		}
		static int icp(String op)
		{
			if(op.equals("+")) return 3;
			else if(op.equals("-")) return 3;
			else if(op.equals("*")) return 2;
			else if(op.equals("/")) return 2;
			else if(op.equals("(") ) return 0;
			else return -1;
		}
		static int isp(String op)
		{
			if(op.equals("+")) return 3;
			else if(op.equals("-")) return 3;
			else if(op.equals("*")) return 2;
			else if(op.equals("/")) return 2;
			else if(op.equals("(") ) return 8;
			else return -1;
		}

		static double Eval(Vector<String> infix) throws Exception {
			
			System.out.println("infix expression: ");
			vecprint(infix);
			
			Vector<String> postfix = toPost(infix);
			
			System.out.println("Postfix expression: ");
			vecprint(postfix);
			
			double ret = 0.0 ;
			
			Stack<Double> nums = new Stack<Double>();
			for(int i = 0 ; i < postfix.size() ; i++)
			{
				String temp = postfix.get(i);
				if(isNum(temp))
				{
					nums.push(Double.parseDouble(temp));
				}
				else
				{
					double num1 = nums.pop();
					double num2 = nums.pop();
					if(temp.equals("/")) nums.push(num2 / num1);
					else if(temp.equals("*")) nums.push(num1 * num2);
					else if(temp.equals("+")) nums.push(num1 + num2);
					else if(temp.equals("-")) nums.push(num2 - num1);
				}
			}
			
			ret = nums.pop();
			if(!nums.empty()) System.out.println("something's wrong: Stack is not empty");
			////this shouldn't be printed
			
			return ret;
		}
		
		static Vector<String> toPost(Vector<String> infix)
		{
			Stack<String> st = new Stack<String>();
			Vector<String> postfix = new Vector<String>();
			for(int i = 0 ; i < infix.size(); i++)
			{
				String temp = infix.get(i);
				if(isNum(temp)) postfix.add(temp);
				
				else if(isOp(temp))
				{
					if(st.empty()) st.push(temp);
					else
					{
						   while( !st.empty() && (icp(temp) >= isp(st.peek()) ) )
						   {
							   postfix.add(st.pop());
						   }
						   st.push(temp);
					}
				}
				
				else if(temp.equals("(")) st.push(temp);
				
				else if(temp.equals(")"))
				{	
					while(true)
					{
						String garo = st.pop();
						if(garo.equals("(") ) break;
						postfix.add(garo);
					}	
				}
				
					
				
				System.out.println("Token: " + temp);
				System.out.print("Stack: ");
				if(!st.empty()) stackprint(st);
				else System.out.println("");
	
			}
			if(!st.empty())
			{
				while(!st.empty())
				{
					postfix.add(st.pop());
				}
			}
			return postfix;
		}
		
	}; 




