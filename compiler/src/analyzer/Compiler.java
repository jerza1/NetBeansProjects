// Compiler.java

package analyzer;

import exception.FormatException;

/**
 * Team 3
 * 
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class Compiler {
	private LexicalAnalyzer lexical;
	private final String lexicalRules = "expressions.dll",
			reservedSymbols = "symbols.dll", reservedWords = "words.dll";

	private final String productionRules = "grammar.dll";
	private SemanticAnalyzer semantic;
	private SyntaxAnalyzer syntax;

	/**
	 * Initializes the compiler with files already loaded.
	 * 
	 * @throws FormatException
	 *             In case of missing file.
	 */
	public Compiler() throws FormatException {
		lexical = new LexicalAnalyzer(lexicalRules, reservedWords,
				reservedSymbols);
		syntax = new SyntaxAnalyzer(productionRules);
		// semantic = new SemanticAnalyzer();
	}

	/**
	 * Invokes analyze method from his LexicalAnalyzer.
	 * 
	 * @param file
	 *            The file to analyze.
	 * @return An array with arrays (tokens, errors, symbols) ready to convert
	 *         to JTable.
	 */
	public String[][][] analyzeLexical(String file) {
		return lexical.analyze(file);
	}

	/**
	 * Invokes analyze method from his SemanticAnalyzer.
	 * 
	 * @param file
	 *            The file to analyze.
	 */
	public void analyzeSemantic(String file) {
		semantic.analyze();
	}

	/**
	 * Invokes analyze method from his SyntaxAnalyzer.
	 * 
	 * @param file
	 *            The file to analyze.
	 */
	public String[][] analyzeSyntax(String file) {
		String[][][] lexicalOut = analyzeLexical(file);
		// statics.Files.printConsole(lexicalOut[0]);
		return syntax.analyze(lexicalOut[0]);
	}
}
