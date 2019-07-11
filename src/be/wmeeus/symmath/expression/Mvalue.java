package be.wmeeus.symmath.expression;

import java.util.Hashtable;

import be.wmeeus.symmath.util.Mexception;

/**
 * Class Mvalue contains an integer value
 * @author Wim Meeus
 *
 */
public class Mvalue extends Mnode {
	/**
	 * The integer value
	 */
	int value = 0;
	
	/**
	 * Indicates that no value has been set
	 */
	boolean novalue = false;

	/**
	 * Create an "empty" value
	 */
	public Mvalue() {
		novalue = true;
	}
	
	/**
	 * Create a value from a particular integer
	 * @param i
	 */
	public Mvalue(int i) {
		value = i;
	}

	/**
	 * Returns the value
	 * @return the value
	 */
	public int get() {
		return value;
	}
	
	/**
	 * Returns a String representation of this value
	 */
	public String toString() {
		return Integer.toString(value);
	}

	/**
	 * Indicates whether this value is zero
	 */
	public boolean isZero() {
		return (value==0);
	}
	
	/**
	 * Indicates whether this is a value (i.e. not an "empty" value)
	 * @return
	 */
	public boolean isNone() {
		return novalue;
	}
	
	/**
	 * Value 0
	 */
	public static Mvalue ZERO = new Mvalue(0);
	
	/**
	 * Value 1
	 */
	public static Mvalue ONE  = new Mvalue(1);
	
	/**
	 * No / empty value
	 */
	public static Mvalue NONE = new Mvalue();
	
	/**
	 * A table of all values that have been constructed 
	 */
	static Hashtable<Integer, Mvalue> values = new Hashtable<Integer, Mvalue>();
	
	/**
	 * Initialize the values table
	 */
	static {
		values.put(0, ZERO);
		values.put(1, ONE);
	}
	
	/**
	 * Return a value element with the indicated value, create a new element if necessary
	 * @param i the value
	 * @return the value element
	 */
	public static Mvalue mkvalue(int i) {
		if (values.containsKey(i)) {
			return values.get(i);
		}
		return new Mvalue(i);
	}
	
	/**
	 * Evaluate i.e. return this value
	 */
	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		return this;
	}

}
