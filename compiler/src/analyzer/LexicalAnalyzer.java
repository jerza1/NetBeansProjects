package analyzer;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import exception.FormatException;
import statics.Files;
import util.AFD;
import util.TokenScanner;

/**
 * Team 3
 * 
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class LexicalAnalyzer {
	public static final String[] types = {"Tira de tokens", "Table de símbolos",
			"Table de errores"};

	private TreeSet<String> reservedWords;
	private TreeMap<String, String> symbolCharacters;
	private Vector<Integer> tLine;
	private Vector<String> tokenList, symbols, errors;
	private TreeMap<String, AFD> tokenTypes;

	/**
	 * Initializes the lexical analyzer with certain paths to perform properly
	 * all the algorithms.
	 * 
	 * @param expressionsPath
	 *            The path of regular expressions to build the AFD.
	 * @param wordsPath
	 *            The path of the reserved words.
	 * @param symbolsPath
	 *            The path of the reserver symbols.
	 * @throws FormatException
	 *             In case of missing file.
	 */
	public LexicalAnalyzer(String expressionsPath, String wordsPath,
			String symbolsPath) throws FormatException {
		tokenTypes = new TreeMap<String, AFD>();
		String[] afds = getExpressions(expressionsPath);

		for (int i = 0; i < afds.length; i++) {
			String[] keys = afds[i].split(":");
			tokenTypes.put(keys[0], new AFD(keys[1]));
		}

		Scanner in = new Scanner(Files.getCompleteFile(wordsPath));
		reservedWords = new TreeSet<String>();
		while (in.hasNext())
			reservedWords.add(in.next());
		in.close();

		symbolCharacters = new TreeMap<String, String>();
		in = new Scanner(Files.getCompleteFile(symbolsPath));
		while (in.hasNext())
			symbolCharacters.put(in.next(), in.next());
		in.close();
	}

	/**
	 * Analyze a given file with the settings previously given.
	 * 
	 * @param file
	 *            The file to analyze.
	 * @return An array with arrays (tokens, errors, symbols) ready to convert
	 *         to JTable.
	 */
	public String[][][] analyze(String file) {
		tLine = new Vector<Integer>();
		tokenList = new Vector<String>();
		symbols = new Vector<String>();
		errors = new Vector<String>();

		TokenScanner in = new TokenScanner(file, symbolCharacters.keySet());
		String[][] tiraTokens, symbolTable = new String[1][2];
		symbolTable[0][0] = "Token";
		symbolTable[0][1] = "id";
		String[][] errorTable = new String[1][2];
		errorTable[0][0] = "#Línea";
		errorTable[0][1] = "Error";

		for (int i; in.hasNext();) {
			i = in.getLine();
			String word = in.next();
			if (process(word) != null
					&& (!process(word).equals("symbol")
							|| symbolCharacters.containsKey(word))
					|| (word.startsWith("/*") && word.endsWith("*/"))) {
				tLine.add(i);
				tokenList.add(word);
				if (!symbols.contains(word) && !reservedWords.contains(word)
						&& Character.isAlphabetic(word.charAt(0)))
					symbols.add(word);
			} else {
				errors.add(i + " " + word);
			}
		}

		if (symbols.size() > 0) {
			symbolTable = new String[symbols.size() + 1][];
			symbolTable[0] = new String[]{"Número", "Símbolo"};

			for (int i = 1; i < symbolTable.length; i++)
				symbolTable[i] = new String[]{"" + i, symbols.elementAt(i - 1)};
		}
		if (errors.size() > 0) {
			errorTable = new String[errors.size() + 1][];
			errorTable[0] = new String[]{"token", "id"};

			for (int i = 1; i < errorTable.length; i++) {
				String s = errors.elementAt(i - 1);
				int j = s.indexOf(' ');
				errorTable[i] = new String[2];
				errorTable[i][0] = s.substring(0, j);
				errorTable[i][1] = s.substring(j + 1);
				errorTable[i][1] = "Símbolo en token " + errorTable[i][1]
						+ " no está definido.";
			}
		}

		tiraTokens = new String[tLine.size() + 2][3];
		tiraTokens[0][0] = "#Linea";
		tiraTokens[0][1] = "Token";
		tiraTokens[0][2] = "Lexema";
		for (int i = 1; i < tiraTokens.length - 1; i++) {
			tiraTokens[i][0] = tLine.elementAt(i - 1).toString();
			tiraTokens[i][2] = tokenList.elementAt(i - 1);
			if (reservedWords.contains(tokenList.elementAt(i - 1)))
				tiraTokens[i][1] = tokenList.elementAt(i - 1);
			else if (symbolCharacters.containsKey(tokenList.elementAt(i - 1)))
				// tiraTokens[i][1] = symbolCharacters
				// .get(tokenList.elementAt(i - 1));
				tiraTokens[i][1] = tokenList.elementAt(i - 1);
			else if (tokenList.elementAt(i - 1).startsWith("/*")
					&& tokenList.elementAt(i - 1).endsWith("*/"))
				tiraTokens[i][1] = "comment";
			else
				tiraTokens[i][1] = process(tokenList.elementAt(i - 1));
		}
		int last = tiraTokens.length - 1;
		tiraTokens[last][0] = "Final";
		tiraTokens[last][1] = "$";
		tiraTokens[last][1] = "$";

		String[][][] tables = new String[3][][];
		tables[0] = tiraTokens;
		tables[2] = errorTable;
		tables[1] = symbolTable;
		for (int i = 0; i < 3; i++) {
			// Files.printConsole(tables[i]);
		}

		return tables;
	}

	private String[] getExpressions(String path) {
		return Files.getCompleteFile(path).split("\n");
	}

	private String process(String s) {
		String type = null;
		for (java.util.Map.Entry<String, AFD> e : tokenTypes.entrySet())
			if (e.getValue().process(s)) {
				type = e.getKey();
				break;
			}

		return type;
	}
	public String processApart(String s) {
		String type = null;
		for (java.util.Map.Entry<String, AFD> e : tokenTypes.entrySet()) {
			System.out.println(e.getKey());
			if (e.getValue().processApart(s)) {
				type = e.getKey();
				break;
			}
			System.out.println();
		}
		return type;
	}
}
