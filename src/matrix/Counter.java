package matrix;

public class Counter {
	private int counter;
	public Counter(){
		counter=0;
	}
	public int getCount(){
		return counter;
	}
	public void setCount(int n){
		counter = n;
	}
	public void inc(){
		counter++;
	}
	public void dec(){
		counter--;
	}
}
