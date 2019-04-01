package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

public class Msymbol extends Mnode {
	String name = null;
	
	public Msymbol(String s) {
		name = s;
		symbols.put(s, this);
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	static Hashtable<String, Msymbol> symbols = new Hashtable<String, Msymbol>();
	public static Msymbol mksymbol(String s) throws Mexception {
		if (s == null) {
			throw new Mexception("Can't make null symbol");
		}
		if (symbols.containsKey(s)) {
			return symbols.get(s);
		}
		return new Msymbol(s);
	}

	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		if (paramvalues.containsKey(this)) {
			return Mvalue.mkvalue(paramvalues.get(this));
		}
		throw new Mexception("Unresolved symbol: " + name);
	}

}
