package be.wmeeus.symmath.util;

@SuppressWarnings("serial")
/**
 * A specific exception for the symbolic math library
 * @author Wim Meeus
 *
 */
public class Mexception extends Exception {
	public Mexception() {
		super();
	}
	public Mexception(String s) {
		super(s);
	}
}
