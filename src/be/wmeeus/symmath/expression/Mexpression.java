package be.wmeeus.symmath.expression;

import java.util.ArrayList;

public class Mexpression extends Mnode {
	String op;
	ArrayList<Mnode> args = new ArrayList<Mnode>();

	public Mexpression(Mnode l, String o, Mnode r) {
		args.add(l);
		args.add(r);
		op = o;
	}

	public Mexpression(Mnode l, String o, String r) {
		args.add(l);
		args.add(new Msymbol(r));
		op = o;
	}

	public Mexpression(Mnode l, String o, int r) {
		args.add(l);
		args.add(new Mvalue(r));
		op = o;
	}

	public Mexpression(String l, String o, Mnode r) {
		args.add(new Msymbol(l));
		args.add(r);
		op = o;
	}

	public Mexpression(String l, String o, String r) {
		args.add(new Msymbol(l));
		args.add(new Msymbol(r));
		op = o;
	}

	public Mexpression(String l, String o, int r) {
		args.add(new Msymbol(l));
		args.add(new Mvalue(r));
		op = o;
	}

	public Mexpression(int l, String o, Mnode r) {
		args.add(new Mvalue(l));
		args.add(r);
		op = o;
	}

	public Mexpression(int l, String o, String r) {
		args.add(new Mvalue(l));
		args.add(new Msymbol(r));
		op = o;
	}

	public Mexpression(int l, String o, int r) {
		args.add(new Mvalue(l));
		args.add(new Mvalue(r));
		op = o;
	}

	public Mexpression(String o, Mnode r) {
		args.add(r);
		op = o;
	}

	public Mexpression(String o, String r) {
		args.add(new Msymbol(r));
		op = o;
	}

	public Mexpression(String o, int r) {
		args.add(new Mvalue(r));
		op = o;
	}

	public boolean isSum() {
		return op.equals("+");
	}

	public static Mnode add(Mnode a, Mnode n) {
		if (a==null || a.isZero()) return n;
		if (n==null || n.isZero()) return a;
		if ((a instanceof Mvalue) && (n instanceof Mvalue)) {
			return Mvalue.mkvalue(((Mvalue)a).value + ((Mvalue)n).value);
		}
		if ((a instanceof Mexpression) && (((Mexpression)a).op.equals("+")) ) {
			((Mexpression)a).args.add(n);
			return a;
		}
		return new Mexpression(a, "+", n);
	}

	public static Mnode subtract(Mnode a, Mnode b) {
		if ((a instanceof Mvalue) && (b instanceof Mvalue)) {
			return Mvalue.mkvalue(((Mvalue)a).value - ((Mvalue)b).value);
		}
		return new Mexpression(a, "-", b);
	}
	
	public static Mnode add(Mnode a, String n) {
		return add(a, new Msymbol(n));
	}

	public static Mnode add(Mnode a, int n) {
		return add(a, new Mvalue(n));
	}

	public String toString() {
		if (op.equals("+") && args.size()==1)
			return args.get(0).toString(); // omit unary plus

		String result = "";
		if (args.size()>1) result = args.get(0).toString();
		for (int i=1; i<args.size(); i++) 
			result += " " + op + " " + args.get(i);
		return result;
	}

	public String getOp() {
		return op;
	}

	public ArrayList<Mnode> getArgs() {
		return args;
	}

}
