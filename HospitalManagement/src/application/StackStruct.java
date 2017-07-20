package application;

public class StackStruct {
	private int[] a = new int[5];
	int top = -1;
	
	
	public void push(int id){
		a[++top] = id;
	}
	
	public int pop(){
		
		if(top==-1)
			return -1;
		return a[top--];
		
	}

}
