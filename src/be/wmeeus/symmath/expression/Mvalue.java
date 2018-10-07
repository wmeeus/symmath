package be.wmeeus.symmath.expression;

public class Mvalue extends Mnode {
	int value;
	
	public Mvalue(int i) {
		value = i;
	}
	
	public int get() {
		return value;
	}
	
	public String toString() {
		return Integer.toString(value);
	}
	
	public boolean isZero() {
		return (value==0);
	}
	
	public static Mvalue ZERO = new Mvalue(0);
	public static Mvalue ONE  = new Mvalue(1);
}
