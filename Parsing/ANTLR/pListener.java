// Generated from p.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pParser}.
 */
public interface pListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(pParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link pParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(pParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link pParser#reg}.
	 * @param ctx the parse tree
	 */
	void enterReg(pParser.RegContext ctx);
	/**
	 * Exit a parse tree produced by {@link pParser#reg}.
	 * @param ctx the parse tree
	 */
	void exitReg(pParser.RegContext ctx);
	/**
	 * Enter a parse tree produced by {@link pParser#imm}.
	 * @param ctx the parse tree
	 */
	void enterImm(pParser.ImmContext ctx);
	/**
	 * Exit a parse tree produced by {@link pParser#imm}.
	 * @param ctx the parse tree
	 */
	void exitImm(pParser.ImmContext ctx);
	/**
	 * Enter a parse tree produced by {@link pParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(pParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link pParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(pParser.InstContext ctx);
}