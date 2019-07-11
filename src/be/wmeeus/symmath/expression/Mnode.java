package be.wmeeus.symmath.expression;

import java.util.Hashtable;

import be.wmeeus.symmath.util.Mexception;

/**
 * Class Mnode is a superclass of all elements of the Symmath library.
 * @author Wim Meeus
 *
 */
public class Mnode {
	/**
	 * Indicates whether this element evaluates to zero
	 * @return true if this element evaluates to zero
	 */
	public boolean isZero() {
		return false;
	}
	
	/**
	 * Convert a String into the most appropriate symbolic math element
	 * @param s the String
	 * @return the symbolic math element
	 * @throws Mexception
	 */
	public static Mnode mknode(String s) throws Mexception {
		try {
			int i = Integer.parseInt(s);
			return Mvalue.mkvalue(i);
		} catch (NumberFormatException ex) {
			// it's not a number, so try the parser then?
		}
		return new Mparser(s).parse();
	}

	/**
	 * Evaluate this symbolic math element using the given parameter values. This method
	 * should be called on subclasses only.
	 * @param paramvalues the table of parameter values to be used for the evaluation
	 * @return the requested value
	 * @throws Mexception
	 */
	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
