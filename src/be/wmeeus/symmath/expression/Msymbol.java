package be.wmeeus.symmath.expression;

public class Msymbol extends Mnode {
	String name = null;
	
	public Msymbol(String s) {
		name = s;
	}
	
	public String toString() {
		return name;
	}
}
