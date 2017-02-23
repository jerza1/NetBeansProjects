package analyzer;

import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import statics.Files;
import util.Automaton;
import util.HelpingSet;

/**
 * Team 3
 * 
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public final class SyntaxAnalyzer {
	private static TreeSet<String> pending = new TreeSet<String>();
	private TreeMap<String, TreeSet<String>> firsts, nexts;
	private String[][] rules = new String[0][0];
	private Vector<HelpingSet> states;
	private TreeSet<String> queue;

	/**
	 * Principal constructor, initializes the SyntaxAnalyzer so we can use the
	 * same grammar for all the algorithms.
	 * 
	 * @param grammarPath
	 *            The path of the grammar.
	 */
	public SyntaxAnalyzer(String grammarPath) {
		String[] lines = Files.getCompleteFile(grammarPath).split("\n");
		rules = new String[lines.length][];
		pending.add("Pendientes");
		for (int i = 0; i < rules.length; i++)
			rules[i] = lines[i].split(" ");
	}

	/**
	 * Executes all the algorithms so we can analyze the syntax of a given file
	 * 
	 * @param file
	 *            The file to analyze.
	 */
	public String[][] analyze(String[][] tokenList) {
		String table[][] = syntaxAnalyzeTable();
		Vector<String> st = new Vector<String>(), ptr = new Vector<String>(),
				rPro = new Vector<String>();
		String aux = "";
		Stack<String> stack = new Stack<String>();
		stack.push("0");
		for (int i = 1; i < tokenList.length;) {
			String s = tokenList[i][1];
			ptr.add(s);
			st.add(stack.toString());
			for (int j = 0; j < table[0].length; j++) {
				if (table[0][j].equals(s)) {
					if (aux.equals("-"))
						aux = "-";
					else
						aux = table[Integer.parseInt(stack.peek()) + 1][j];
					break;
				}
			}
			if (aux.contains("Aceptación")) {
				rPro.add("Aceptación");
				break;
			} else if (aux.charAt(0) == 'd') {
				stack.push(s);
				rPro.add("");
				stack.push(aux.substring(1));
				i++;
				// System.out.println(s + " " + aux.substring(1) + " " + stack);
			} else if (aux.charAt(0) == 'r') {
				int rule = Integer.parseInt(aux.substring(1));
				String r = "";
				// System.out.println("Reducción " + rule + " " + stack);
				for (int j = 0; j < rules[rule].length; j++)
					r += rules[rule][j];
				// System.out.println(r);
				rPro.add(r);
				aux = rules[rule][0];
				if (rules[rule][2].equals("epsilon"))
					rule = 0;
				else
					rule = rules[rule].length - 2;
				rule <<= 1;
				for (int k = 0; k < rule; k++)
					stack.pop();
				// System.out.println(stack);
				rule = Integer.parseInt(stack.peek());
				stack.push(aux);
				// System.out.println(stack);
				for (int j = 1; j < table[0].length; j++) {
					if (table[0][j].equals(stack.peek())) {
						aux = table[rule + 1][j];
						break;
					}
				}
				stack.push(aux);
				// System.out.println("Saliendo de la reducción: " + stack);
			} else {
				// System.out.println(s + "Error: " + aux + stack);
				rPro.add("Error en la línea: " + tokenList[i][0]);
				break;
			}
		}
		table = new String[st.size() + 1][3];
		table[0][0] = "Pila";
		table[0][1] = "ptr";
		table[0][2] = "Salida";

		for (int i = 1; i < table.length; i++) {
			table[i][0] = st.elementAt(i - 1);
			table[i][1] = ptr.elementAt(i - 1);
			table[i][2] = rPro.elementAt(i - 1);
		}
		return table;
	}

	/**
	 * Executes canonical collection algorithm to the grammar.
	 * 
	 * @return An array with table format ready to convert to JTable.
	 */
	public String[][] canonicalCollection() {
		firstAndNext();

		states = new Vector<HelpingSet>();
		Vector<String> symbols = new Vector<String>();
		TreeSet<HelpingSet> marked = new TreeSet<HelpingSet>();
		String[] tmp = rulesCanonicalFormat();
		String[][] table;

		states.add(clausureE(new HelpingSet(tmp[0]), tmp));
		states.elementAt(0).setRealName("0");

		while (marked.size() < states.size()) {
			HelpingSet currentHS = states.elementAt(marked.size());
			String[] aux;
			marked.add(currentHS);
			for (String s : currentHS.stringsIn()) {
				aux = s.split(" ");
				for (int i = 2; i < aux.length - 1; i++)
					if (aux[i].equals("@"))
						symbols.add(aux[i + 1]);
			}
			for (String s : symbols) {
				HelpingSet next = currentHS.goTo(s);
				if (next.hasStrings) {
					next = clausureE(next, tmp);
					if (states.contains(next)) {
						next = states.elementAt(states.indexOf(next));
					} else {
						if (!next.realName().equals("Aceptación")) {
							next.setRealName("" + states.size());
							states.add(next);
						}
					}
					currentHS.addTransition(s, next);
				}
			}
			symbols.clear();
		}

		table = new String[states.size() + 1][2];
		table[0][0] = "Estado";
		table[0][1] = "Contenido";
		int i = 1;

		for (HelpingSet set : states) {
			table[i][0] = set.realName();
			String content = "";
			for (String s : set.stringsIn()) {
				content += "[" + s.replace("@", ".") + "]";
			}
			table[i][1] = content;
			i++;
			if (set.containsReductions()) {
				for (String s : set.stringsIn()) {
					if (s.endsWith("@")) {
						for (i = 0; i < tmp.length; i++) {
							if (s.replace(" @", "")
									.equals(tmp[i].replace(" @", ""))) {
								set.addReduction("r" + i);
							}
						}
					}
				}
			}
		}

		return table;
	}

	/**
	 * Sets every state to a set of states adding every missing String.
	 * 
	 * @param clausure
	 *            The state which the algorithm will be applied.
	 * @param aux
	 *            The grammar with the appropriate format.
	 * @return The reassign state.
	 */
	public HelpingSet clausureE(HelpingSet clausure, String[] aux) {
		Vector<String> set = clausure.stringsIn();
		Stack<String> stack = new Stack<String>();

		for (String s : set)
			stack.push(s);
		while (!stack.isEmpty()) {
			String[] t = stack.pop().split(" ");
			int arroba;
			for (arroba = 0; !t[arroba].equals("@"); arroba++);
			if (arroba == t.length - 1)
				continue;
			if (!isTerminal(t[arroba + 1])) {
				for (int i = 0; i < aux.length; i++)
					if (aux[i].startsWith(t[arroba + 1] + " ")
							&& !aux[i].endsWith("epsilon")
							&& !clausure.contains(aux[i])) {
						clausure.add(aux[i]);
						stack.push(aux[i]);
					}
			}
		}

		return clausure;
	}

	/**
	 * Calculates the first and next of every non-terminal element in grammar.
	 * 
	 * @return An array with table format ready to convert to JTable.
	 */
	public String[][] firstAndNext() {
		firsts = new TreeMap<String, TreeSet<String>>();
		nexts = new TreeMap<String, TreeSet<String>>();

		for (int i = 0; i < rules.length; i++)
			for (int j = 0; j < rules[i].length; j++) {
				firsts(rules[i][j]);
			}
		queue = new TreeSet<String>();
		for (int k = 0; k == 0 || !queue.isEmpty(); k++) {
			for (int i = 0; i < rules.length; i++) {
				nexts(rules[i][0]);
			}
		}

		String[][] table = new String[firsts.size() + 1][3];
		int i = 1;
		table[0][0] = "Primeros y siguientes";
		table[0][1] = "Primeros";
		table[0][2] = "Siguientes";

		for (java.util.Map.Entry<String, TreeSet<String>> e : firsts
				.entrySet()) {
			String f = "", n = "";
			for (String s : e.getValue()) {
				f += s + " ";
			}
			if (nexts.get(e.getKey()) != null)
				for (String s : nexts.get(e.getKey())) {
					n += s + " ";
				}
			table[i][0] = e.getKey();
			table[i][1] = f;
			table[i++][2] = n;
		}

		return table;
	}

	private void firsts(String s) {
		TreeSet<String> value = new TreeSet<String>();

		for (int i = 0; i < rules.length; i++)
			if (rules[i][0].equals(s)) {
				if (!firsts.containsKey(rules[i][2])) {
					if (!rules[i][2].equals(s))
						firsts(rules[i][2]);
					else
						continue;
				}
				value.addAll(firsts.get(rules[i][2]));
			}

		if (value.size() == 0)
			value.add(s);

		firsts.put(s, value);
	}

	private boolean isTerminal(String s) {
		for (int i = 0; i < rules.length; i++)
			if (rules[i][0].equals(s))
				return false;
		return true;
	}

	private void nexts(String s) {
		if (nexts.containsKey(s))
			return;

		TreeSet<String> value = new TreeSet<String>();

		if (s.equals(rules[0][0]))
			value.add("$");

		for (int i = 0; i < rules.length; i++) {
			for (int j = 2; j < rules[i].length; j++) {
				if (rules[i][j].equals(s)) {
					if (j + 1 == rules[i].length) {
						if (!s.equals(rules[i][0])) {
							nexts(rules[i][0]);
							value.addAll(nexts.get(rules[i][0]));
						}
					} else {
						value.addAll(firsts.get(rules[i][j + 1]));
						for (int k = 1; value.contains("epsilon"); k++) {
							value.remove("epsilon");
							if (j + k + 1 == rules[i].length) {
								if (!s.equals(rules[i][0])) {
									nexts(rules[i][0]);
									value.addAll(nexts.get(rules[i][0]));
								}
							} else {
								value.addAll(firsts.get(rules[i][j + k + 1]));
							}
						}
					}
				}
			}
		}
		nexts.put(s, value);
		queue.remove(s);
	}

	private String[] rulesCanonicalFormat() {
		String[] out = new String[rules.length];

		for (int i = 0; i < rules.length; i++) {
			out[i] = rules[i][0] + " " + rules[i][1];
			out[i] += " @";
			for (int j = 2; j < rules[i].length; j++)
				out[i] += " " + rules[i][j];
		}
		out[0] += " $";

		return out;
	}

	/**
	 * Executes first and next and canonical collection algorithm to generate a
	 * table to be able to analyze the syntax of a file.
	 * 
	 * @return An array with table format ready to convert to JTable.
	 */
	public String[][] syntaxAnalyzeTable() {
		String[][] table;
		canonicalCollection();
		TreeSet<String> terminals = new TreeSet<String>();
		TreeSet<String> noTerminals = new TreeSet<String>();

		for (int i = 0; i < rules.length; i++)
			for (int j = 0; j < rules[i].length; j++)
				if (isTerminal(rules[i][j]))
					terminals.add(rules[i][j]);
				else
					noTerminals.add(rules[i][j]);

		terminals.add("$");
		terminals.remove("->");

		table = new String[states.size() + 1][terminals.size()
				+ noTerminals.size() + 1];
		table[0][0] = "TAS";
		int i = 1;
		for (String s : terminals)
			table[0][i++] = s;
		for (String s : noTerminals)
			table[0][i++] = s;

		for (i = 1; i < table.length; i++) {
			table[i][0] = "" + (i - 1);
			if (states.elementAt(i - 1).containsReductions()) {
				for (String r : states.elementAt(i - 1).getReductions()) {
					String s = rules[Integer.parseInt(r.replace("r", ""))][0];
					TreeSet<String> set = nexts.get(s);
					for (int j = 1; j <= terminals.size(); j++) {
						if (set.contains(table[0][j])) {
							if (table[i][j] == null)
								table[i][j] = r;
							else
								table[i][j] += " " + r;
						}
					}
				}
			}
		}
		for (i = 1; i < table.length; i++) {
			TreeMap<String, HelpingSet> set = states.elementAt(i - 1)
					.getTransitions();
			for (int j = 1; j < table[0].length; j++) {
				if (set.containsKey(table[0][j])) {
					// System.out.println(table[0][j] + " "
					// + set.get(table[0][j]).realName());
					if (table[i][j] == null)
						table[i][j] = (j > terminals.size() ? "" : "d")
								+ set.get(table[0][j]).realName();
					else
						table[i][j] += (j > terminals.size() ? "" : "d")
								+ set.get(table[0][j]).realName();
				}
				if (table[i][j] == null)
					table[i][j] = Automaton.NOSTATE;
			}
		}

		return table;
	}
}
