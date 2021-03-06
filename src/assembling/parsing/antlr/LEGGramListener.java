// Generated from LEGGram.g4 by ANTLR 4.7.1

    package assembling.parsing.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LEGGramParser}.
 */
public interface LEGGramListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#filePath}.
	 * @param ctx the parse tree
	 */
	void enterFilePath(LEGGramParser.FilePathContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#filePath}.
	 * @param ctx the parse tree
	 */
	void exitFilePath(LEGGramParser.FilePathContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LEGGramParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LEGGramParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(LEGGramParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(LEGGramParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#reg}.
	 * @param ctx the parse tree
	 */
	void enterReg(LEGGramParser.RegContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#reg}.
	 * @param ctx the parse tree
	 */
	void exitReg(LEGGramParser.RegContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#imm}.
	 * @param ctx the parse tree
	 */
	void enterImm(LEGGramParser.ImmContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#imm}.
	 * @param ctx the parse tree
	 */
	void exitImm(LEGGramParser.ImmContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#memcall}.
	 * @param ctx the parse tree
	 */
	void enterMemcall(LEGGramParser.MemcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#memcall}.
	 * @param ctx the parse tree
	 */
	void exitMemcall(LEGGramParser.MemcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(LEGGramParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(LEGGramParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(LEGGramParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(LEGGramParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LEGGramParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(LEGGramParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link LEGGramParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(LEGGramParser.InstContext ctx);
}