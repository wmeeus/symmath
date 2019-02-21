package be.wmeeus.symmath.expression;

import java.util.Hashtable;

import be.wmeeus.symmath.util.Mexception;

public class Mnode {

	public boolean isZero() {
		return false;
	}
	
	public static Mnode mknode(String s) throws Mexception {
		try {
			int i = Integer.parseInt(s);
			return Mvalue.mkvalue(i);
		} catch (NumberFormatException ex) {
			// it's not a number, so try the parser then?
		}
//		return Msymbol.mksymbol(s);
		return new Mparser(s).parse();
	}

	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
