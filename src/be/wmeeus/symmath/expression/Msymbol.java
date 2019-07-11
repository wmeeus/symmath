package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

/**
 * Class Msymbol represents a symbolic math symbol
 * @author Wim Meeus
 *
 */
public class Msymbol extends Mnode {
	/**
	 * Symbol name
	 */
	String name = null;
	
	/**
	 * Make a new symbol
	 * @param s the symbol name
	 */
	public Msymbol(String s) {
		name = s;
		symbols.put(s, this);
	}
	
	/**
	 * Returns a String representation of this math symbol
	 */
	public String toString() {
		return name;
	}

	/**
	 * Returns the name of this symbol
	 * @return the name of this symbol
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A table of all symbols
	 */
	static Hashtable<String, Msymbol> symbols = new Hashtable<String, Msymbol>();
	
	/**
	 * Get a symbol if it already exists, create a new symbol if not
	 * @param s the symbol name
	 * @return the requested symbol
	 * @throws Mexception
	 */
	public static Msymbol mksymbol(String s) throws Mexception {
		if (s == null) {
			throw new Mexception("Can't make null symbol");
		}
		if (symbols.containsKey(s)) {
			return symbols.get(s);
		}
		return new Msymbol(s);
	}

	/**
	 * Evaluate this symbol. If it is in the list with parameter values, return the value.
	 * If not, throw an exception. 
	 */
	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		if (paramvalues.containsKey(this)) {
			return Mvalue.mkvalue(paramvalues.get(this));
		}
		throw new Mexception("Unresolved symbol: " + name);
	}

}
