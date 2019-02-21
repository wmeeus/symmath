package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

public class Mparser {
	
	Mnode parsed;
	String source;
	
	public Mparser(String s) {
		source = s;
	}
	
	final String tokens = "()+-*/%";
	final String ops = "+-*/%";

	public Mnode parse() throws Mexception {
//		System.out.println("Mparser: source " + source);
		if (source==null) return null;
		ArrayList<Object> stack = new ArrayList<Object>();
		StringTokenizer st = new StringTokenizer(source, tokens, true);
		if (st.countTokens() < 2) {
			// only one token: must be either a value or a symbol
			try {
				int i = Integer.parseInt(source);
				return Mvalue.mkvalue(i);
			} catch (NumberFormatException ex) {
				// it's not a number, assume that it's a symbol
			}
			return Msymbol.mksymbol(source);
		}
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
//			System.out.println("Mparser: token " + tok);
			if (tok.equals(")")) {
				int obkt = stack.lastIndexOf("(");
				if (obkt < 0) throw new Mexception("Bracket imbalance in " + source);
				Mnode n = parseAdd(stack.subList(obkt+1, stack.size()));
				for (int i = stack.size() - 1; i > obkt; i--) {
					stack.remove(i);
				}
				stack.set(obkt, n);
			} else {
				stack.add(tok);
			}
		}
		if (stack.size() > 1) return parseAdd(stack);
		if (stack.isEmpty()) return null;
		if (stack.get(0) instanceof Mnode) return (Mnode)stack.get(0);
		return parseMult(stack);
	}
	
	private Mnode parseAdd(List<Object> stack) throws Mexception {
		int pind = stack.lastIndexOf("+");
		int mind = stack.lastIndexOf("-");
		int aind = Math.max(pind, mind);
		if (aind >= 0) {
			// TODO stack may contain Mnodes!!
			Mnode lhs = null;
			if (aind > 0) lhs = parseAdd(stack.subList(0, aind));
			Mnode rhs = parseMult(stack.subList(aind+1, stack.size()));
			return new Mexpression(lhs, (String)stack.get(aind), rhs);
		}
		// multiplicative expression
		return parseMult(stack);
	}
	
	private Mnode parseMult(List<Object> stack) throws Mexception {
		int mind = stack.lastIndexOf("*");
		int dind = stack.lastIndexOf("/");
		int rind = stack.lastIndexOf("%");
		int aind = Math.max(mind,  Math.max(dind, rind));
		if (aind>-1) {
			// we expect exactly one object after the operator!
			Mnode lhs = parseMult(stack.subList(0, aind));
			if (aind != stack.size() - 2) {
				throw new Mexception("Expecting an operator on the one-but last position ( = " + aind + "?) of " + stack);
			}
			Object rho = stack.get(aind + 1);
			Mnode rhs = null; 
			if (rho instanceof Mnode) {
				rhs = (Mnode)rho;
			} else {
				rhs = Mnode.mknode((String)rho);
			}
			return new Mexpression(lhs, (String)stack.get(aind), rhs);
		}
		if (stack.size() != 1) {
			throw new Mexception("No operator, expecting exactly one element but got " + stack);
		}
		Object o = stack.get(0);
		if (o instanceof Mnode) return (Mnode)o;
		return Mnode.mknode((String)o);
	}
	
}
