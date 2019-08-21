package be.wmeeus.symmath.expression;

public class Mrange extends Mnode {
	private Mnode rstart = null;
	private Mnode rend = null;
	public Mrange(Mnode l, Mnode r) {
		rstart = l;
		rend = r;
	}
	public Mnode getStart() {
		return rstart;
	}
	public Mnode getEnd() {
		return rend;
	}
	public String toString() {
		return rstart.toString() + ".." + rend.toString();
	}
}
