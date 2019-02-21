package be.wmeeus.symmath.expression;

import java.util.Hashtable;

import be.wmeeus.symmath.util.Mexception;

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
	
	static Hashtable<Integer, Mvalue> values = new Hashtable<Integer, Mvalue>();
	
	static {
		values.put(0, ZERO);
		values.put(1, ONE);
	}
	
	public static Mvalue mkvalue(int i) {
		if (values.containsKey(i)) {
			return values.get(i);
		}
		return new Mvalue(i);
	}
	
	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		return this;
	}

}
