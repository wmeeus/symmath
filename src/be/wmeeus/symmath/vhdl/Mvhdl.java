package be.wmeeus.symmath.vhdl;
import java.util.*;
import be.wmeeus.symmath.expression.*;
import be.wmeeus.vhdl.*;

/**
 * Build a VHDL expression from a math expression
 * @author wmeeus
 *
 */
public class Mvhdl {
	
	/**
	 * Build a VHDL expression from a math expression.
	 * 
	 * @param n the math expression to convert into VHDL
	 * @param a the architecture in which the expression will be used
	 * @return the VHDL expression
	 * @throws VHDLexception 
	 */
	public static VHDLnode vhdl(Mnode n, VHDLarchitecture a) throws VHDLexception {
		if (n==null) return null;
		if (n instanceof Mvalue) {
			return new VHDLvalue(((Mvalue)n).get());
		}
		if (n instanceof Msymbol) {
			// look up generic, create if new
			return new VHDLgeneric(((Msymbol)n).toString(), VHDLinteger.INTEGER);
		}
		Mexpression e = (Mexpression)n;
		ArrayList<VHDLnode> args = new ArrayList<VHDLnode>();
		for (Mnode nn: e.getArgs()) {
			args.add(vhdl(nn, a));
		}
		return new VHDLexpression(e.getOp(), args);
	}
}
