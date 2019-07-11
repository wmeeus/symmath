package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

/**
 * Class parameter describes a parameter i.e. an symbol and its set of values
 * @author Wim Meeus
 *
 */
public class Mparameter extends Mnode {
	/**
	 * The symbol 
	 */
	Msymbol symbol = null;
	
	/**
	 * The set of values. This can either be an enumeration of all possible values 
	 * (when isrange == false) or the minimum and maximum value of a range (when isrange == true).
	 */
	ArrayList<Integer> values = null;
	
	/**
	 * Indicates whether the set of values is the minimum and maximum of a range (true), or an
	 * enumeration of all parameter values (false)
	 */
	boolean isrange = false;
	
	/**
	 * Returns the symbol
	 * @return the symbol
	 */
	public Msymbol getSymbol() {
		return symbol;
	}
	
	/**
	 * Construct a parameter from text input
	 * @param s the symbol name
	 * @param v the values: either a single value, a comma-seperated list of enumerated values, or
	 *   a range <min>..<max>
	 * @throws Mexception
	 */
	public Mparameter(String s, String v) throws Mexception {
		symbol = Msymbol.mksymbol(s);
		setValues(v);
	}
	
	/**
	 * Construct a parameter with a single value
	 * @param s the symbol name
	 * @param v the value
	 * @throws Mexception
	 */
	public Mparameter(String s, int v) throws Mexception {
		symbol = Msymbol.mksymbol(s);
		values = new ArrayList<Integer>();
		values.add(v);
	}
	
	/**
	 * Construct a parameter
	 * @param s the symbol
	 * @param v the values: either a single value, a comma-seperated list of enumerated values, or
	 *   a range <min>..<max>
	 */
	public Mparameter(Msymbol s, String v) {
		symbol = s;
		setValues(v);
	}

	/**
	 * Parse the value(s) from a String
	 * @param v the input String containing the values
	 */
	private void setValues(String v) {
		values = new ArrayList<Integer>();
		if (v.contains("..")) {
			isrange = true;
			int sep = v.indexOf("..");
			values.add(Integer.valueOf(v.substring(0, sep)));
			values.add(Integer.valueOf(v.substring(sep+2)));
		} else if (v.contains(",")) {
			String[] vs = v.split(",");
			for (int i=0; i<vs.length; i++) {
				values.add(Integer.valueOf(vs[i]));
			}
		} else {
			values.add(Integer.valueOf(v));
		}
	}
	
	/**
	 * A static iterator to run over the values
	 */
	int it = 0;
	
	/**
	 * Set the iterator to the first value and return the first value
	 * @return the first parameter value
	 */
	public int start() {
		if (isrange) {
			it = values.get(0);
		} else {
			it = 0;
		}
		return values.get(0);
	}

	/**
	 * Set the iterator to the next value and return the next value
	 * @return the next parameter value
	 */
	public int next() {
		if (isrange) {
			if (it < values.get(1)) {
				it++;
				return it;
			} else {
				return -1;
			}
		}
		if (values.size() > it+1) {
			it++;
			return values.get(it);
		}
		return -1;
	}
	
	/**
	 * Returns the number of parameter values
	 * @return the number of parameter values
	 */
	public int count() {
		if (isrange) {
			return values.get(1) - values.get(0) + 1;
		}
		return values.size();
	}

	/**
	 * Returns the parameter value. Assumes that the parameter only has one value. Know what you're
	 * doing when calling this method.
	 * @return the parameter value
	 */
	public int getValue() /*TODO when multiple values: throws Mexception*/ {
		return values.get(0);
	}

	/**
	 * Returns a String representation of this parameter
	 */
	public String toString() {
		String r = symbol.toString() + ": ";
		if (isrange) {
			r += values.get(0).toString() + ".." + values.get(1).toString();
		} else {
			boolean isfirst = true;
			for (Integer i: values) {
				if (isfirst) {
					r = i.toString();
					isfirst = false;
				} else {
					r += "," + i.toString();
				}
			}
		}
		return r;
	}

	/**
	 * Calculates and returns the image of the parameter values for a function f
	 * @param f the function
	 * @return the image of the parameter values
	 * @throws Mexception
	 */
	public ArrayList<Integer> image(Mnode f) throws Mexception {
		Hashtable<Msymbol, Integer> paramvalues = new Hashtable<Msymbol, Integer>();
		if (values == null || values.isEmpty()) return null;
		ArrayList<Integer> img = new ArrayList<Integer>();
		for (Integer i = start(); i != -1; i = next()) {
			paramvalues.put(symbol, i);
			Integer fi = f.eval(paramvalues).get();
			if (!img.contains(fi)) {
				img.add(fi);
			}
		}
		return img;
	}

	/**
	 * Calculates the domain of the parameter values of function f i.e. a set of parameter values that
	 * have a unique image. 
	 * @param f the function
	 * @return the domain of the parameter values
	 * @throws Mexception
	 */
	public ArrayList<Integer> domain(Mnode f) throws Mexception {
		Hashtable<Msymbol, Integer> paramvalues = new Hashtable<Msymbol, Integer>();
		if (values == null || values.isEmpty()) return null;
		ArrayList<Integer> img = new ArrayList<Integer>();
		ArrayList<Integer> dom = new ArrayList<Integer>();
		for (Integer i = start(); i != -1; i = next()) {
			paramvalues.put(symbol, i);
			Integer fi = f.eval(paramvalues).get();
			if (!img.contains(fi)) {
				img.add(fi);
				dom.add(i);
			}
		}
		return dom;
	}
	
}
