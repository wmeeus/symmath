package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

/**
 * Class Mexpression contains a mathematical expression
 * @author Wim Meeus
 *
 */
public class Mexpression extends Mnode {
	/**
	 * Math operator
	 */
	String op;
	
	/**
	 * Arguments
	 */
	ArrayList<Mnode> args = new ArrayList<Mnode>();

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(Mnode l, String o, Mnode r) {
		args.add(l);
		args.add(r);
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(Mnode l, String o, String r) {
		args.add(l);
		args.add(new Msymbol(r));
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(Mnode l, String o, int r) {
		args.add(l);
		args.add(new Mvalue(r));
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String l, String o, Mnode r) {
		args.add(new Msymbol(l));
		args.add(r);
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String l, String o, String r) {
		args.add(new Msymbol(l));
		args.add(new Msymbol(r));
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String l, String o, int r) {
		args.add(new Msymbol(l));
		args.add(new Mvalue(r));
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(int l, String o, Mnode r) {
		args.add(new Mvalue(l));
		args.add(r);
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(int l, String o, String r) {
		args.add(new Mvalue(l));
		args.add(new Msymbol(r));
		op = o;
	}

	/**
	 * Construct an expression
	 * @param l the LHS argument
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(int l, String o, int r) {
		args.add(new Mvalue(l));
		args.add(new Mvalue(r));
		op = o;
	}

	/**
	 * Construct a unary expression
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String o, Mnode r) {
		args.add(r);
		op = o;
	}

	/**
	 * Construct a unary expression
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String o, String r) {
		args.add(new Msymbol(r));
		op = o;
	}

	/**
	 * Construct a unary expression
	 * @param o the operator
	 * @param r the RHS operand
	 */
	public Mexpression(String o, int r) {
		args.add(new Mvalue(r));
		op = o;
	}

	/**
	 * Indicates whether the expression is a sum i.e. if the operator is +
	 * @return true if the expression is a sum
	 */
	public boolean isSum() {
		return op.equals("+");
	}

	/**
	 * Return an Mnode which represents the sum of the arguments
	 * @param a the first argument
	 * @param n the second argument
	 * @return the sum
	 */
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

	/**
	 * Return an Mnode which represents the difference (subtraction) of the arguments
	 * @param a the first argument
	 * @param b the second argument
	 * @return the difference
	 */
	public static Mnode subtract(Mnode a, Mnode b) {
		if ((a instanceof Mvalue) && (b instanceof Mvalue)) {
			return Mvalue.mkvalue(((Mvalue)a).value - ((Mvalue)b).value);
		}
		return new Mexpression(a, "-", b);
	}

	/**
	 * Return an Mnode which represents the sum of the arguments
	 * @param a the first argument
	 * @param n the second argument
	 * @return the sum
	 */
	public static Mnode add(Mnode a, String n) {
		return add(a, new Msymbol(n));
	}

	/**
	 * Return an Mnode which represents the sum of the arguments
	 * @param a the first argument
	 * @param n the second argument
	 * @return the sum
	 */
	public static Mnode add(Mnode a, int n) {
		return add(a, new Mvalue(n));
	}

	/**
	 * Returns a String representation of this expression
	 */
	public String toString() {
		if (op.equals("+") && args.size()==1)
			return args.get(0).toString(); // omit unary plus

		String result = "";
		if (args.size()>1) result = args.get(0).toString();
		for (int i=1; i<args.size(); i++) 
			result += " " + op + " " + args.get(i);
		return result;
	}

	/**
	 * Returns the operator
	 * @return the operator
	 */
	public String getOp() {
		return op;
	}

	/**
	 * Returns the arguments
	 * @return the arguments
	 */
	public ArrayList<Mnode> getArgs() {
		return args;
	}

	/**
	 * Try to evaluate the expression to a particular value, substituting parameters
	 * by their values. 
	 */
	public Mvalue eval(Hashtable<Msymbol, Integer> paramvalues) throws Mexception {
		if (args == null || args.isEmpty()) return null;
		if (args.size()==1) { // unary something
			if (op.equals("-")) {
				return Mvalue.mkvalue(0 - args.get(0).eval(paramvalues).get());
			}
			return args.get(0).eval(paramvalues);
		}

		int value = 0;
		boolean start = true;
		for (Mnode n: args) {
			int arg = n.eval(paramvalues).get();
			if (start) {
				value = arg;
				start = false;
			} else {
				if (op.equals("+")) {
					value = value + arg;
				} else if (op.equals("-")) {
					value = value - arg;
				} else if (op.equals("*")) {
					value = value * arg;
				} else if (op.equals("/")) {
					value = value / arg;
				} else if (op.equals("%")) {
					value = value % arg;
				} else {
					throw new Mexception("Unsupported operation: " + op);
				}
			}
		}
		return Mvalue.mkvalue(value);
	}

}
