package be.wmeeus.symmath.expression;

import java.util.*;

import be.wmeeus.symmath.util.Mexception;

public class Mparameter extends Mnode {
	Msymbol symbol = null;
	ArrayList<Integer> values = null;
	boolean isrange = false;
	
	public Msymbol getSymbol() {
		return symbol;
	}
	
	public Mparameter(String s, String v) throws Mexception {
		symbol = Msymbol.mksymbol(s);
		setValues(v);
	}
	
	public Mparameter(Msymbol s, String v) {
		symbol = s;
		setValues(v);
	}

	private void setValues(String v) {
		values = new ArrayList<Integer>();
		if (v.contains("..")) {
			isrange = true;
			int sep = v.indexOf("..");
			values.add(Integer.valueOf(v.substring(0, sep)));
			values.add(Integer.valueOf(v.substring(sep+2)));
		} else if (v.contains(",")) {
			String[] vs = v.split(",");
			for (int i=0; i<vs.length; i++) {
				values.add(Integer.valueOf(vs[i]));
			}
		} else {
			values.add(Integer.valueOf(v));
		}
	}
	
	int it = 0;
	public int start() {
		if (isrange) {
			it = values.get(0);
		} else {
			it = 0;
		}
		return values.get(0);
	}

	public int next() {
		if (isrange) {
			if (it < values.get(1)) {
				it++;
				return it;
			} else {
				return -1;
			}
		}
		if (values.size() > it+1) {
			it++;
			return values.get(it);
		}
		return -1;
	}
	
	public int count() {
		if (isrange) {
			return values.get(1) - values.get(0) + 1;
		}
		return values.size();
	}
	
	public String toString() {
		String r = symbol.toString() + ": ";
		if (isrange) {
			r += values.get(0).toString() + ".." + values.get(1).toString();
		} else {
			boolean isfirst = true;
			for (Integer i: values) {
				if (isfirst) {
					r = i.toString();
					isfirst = false;
				} else {
					r += "," + i.toString();
				}
			}
		}
		return r;
	}

	public Mparameter transform(Mparameter p, Mexpression exp) throws Mexception {
		
		
		return null;
	}
	
	public ArrayList<Integer> image(Mnode f) throws Mexception {
		Hashtable<Msymbol, Integer> paramvalues = new Hashtable<Msymbol, Integer>();
		if (values == null || values.isEmpty()) return null;
		ArrayList<Integer> img = new ArrayList<Integer>();
		for (Integer i = start(); i != -1; i = next()) {
			paramvalues.put(symbol, i);
			Integer fi = f.eval(paramvalues).get();
			if (!img.contains(fi)) {
				img.add(fi);
			}
		}
		System.out.println("** Image ** of " + this + " under " + f + " is " + img);
		return img;
	}
	
	public ArrayList<Integer> domain(Mnode f) throws Mexception {
		Hashtable<Msymbol, Integer> paramvalues = new Hashtable<Msymbol, Integer>();
		if (values == null || values.isEmpty()) return null;
		ArrayList<Integer> img = new ArrayList<Integer>();
		ArrayList<Integer> dom = new ArrayList<Integer>();
		for (Integer i = start(); i != -1; i = next()) {
			paramvalues.put(symbol, i);
			Integer fi = f.eval(paramvalues).get();
			if (!img.contains(fi)) {
				img.add(fi);
				dom.add(i);
			}
		}
		System.out.println("** Domain ** of " + this + " under " + f + " is " + dom);
		return dom;
	}
	
}
