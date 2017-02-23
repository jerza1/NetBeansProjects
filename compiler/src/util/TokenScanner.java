/**
 * 
 */
package util;

import java.util.Set;

/**
 * @author Héctor J. Vásquez
 *
 */
public class TokenScanner {
	private String read;
	private int element;
	private int line;
	Set<String> symbols;
	public TokenScanner(String file, Set<String> s) {
		read = file;
		element = 0;

		line = 1;
		for (; element < read.length(); element++) {
			char c = read.charAt(element);
			if (c != ' ' && c != '\t' && c != '\n')
				break;
			if (c == '\n')
				line++;
		}
		symbols = s;

	}
	public String next() {
		String out = null;
		char c = read.charAt(element);
		int e = element++;

		if (c == '\"') {
			char aux;
			for (; element < read.length(); element++) {
				aux = read.charAt(element);

				if (aux == '\n')
					line++;
				if (aux == '\"') {
					element++;
					break;
				}
			}

		} else if (c == '/' && element < read.length()
				&& read.charAt(element) == '/') {
			char aux;
			for (; element < read.length(); element++) {
				aux = read.charAt(element);

				if (aux == '\n') {
					line++;
					break;
				}
			}

		} else if (c == '/' && element < read.length()
				&& read.charAt(element) == '*') {
			char aux;
			for (; element < read.length(); element++) {
				aux = read.charAt(element);

				if (aux == '\n') {
					line++;
				}
				if (aux == '*' && element + 1 < read.length()
						&& read.charAt(element + 1) == '/') {
					element += 2;
					break;
				}
			}

		} else if (c == '\'') {
			if (element + 1 < read.length()
					&& read.charAt(element + 1) == '\'') {
				element += 2;
			}

		} else if (Character.isAlphabetic(c)) {
			char aux;
			for (; element < read.length(); element++) {
				aux = read.charAt(element);
				if (aux == '\n' || aux == '\t' || aux == ' '
						|| symbols.contains("" + aux))
					break;
			}

		} else if (Character.isDigit(c)) {
			char aux;
			for (; element < read.length(); element++) {
				aux = read.charAt(element);
				if (aux == '.') {
					if ((element + 1 < read.length())
							&& read.charAt(element + 1) != '.') {
						continue;
					}
				}
				if (aux == '\n' || aux == '\t' || aux == ' '
						|| symbols.contains("" + aux)) {
					break;
				}
			}

		} else if (symbols.contains("" + c)) {
			String selected = "";
			for (String s : symbols) {
				int i;
				// System.out.println(s);
				if (s.startsWith("" + c)) {
					for (i = 1; i < s.length()
							&& (element + i - 1) < read.length(); i++) {
						if (s.charAt(i) != read.charAt(i + element - 1))
							break;
					}
					if (i == s.length()) {
						selected = s;
					}
				}
				/*
				 * if (aux == '\\') { switch (read.charAt(++element)) { case 'n'
				 * : aux = '\n'; break; case '0' : aux = '\0'; break; case 't' :
				 * aux = '\t'; break; case '\"' : aux = '\"'; break; case '\'' :
				 * break; default : aux = '\b'; break; } } out += "" + aux;
				 */

			}
			element += selected.length() - 1;

		}
		out = read.substring(e, element);

		for (; element < read.length(); element++) {
			c = read.charAt(element);
			if (c == '\n')
				line++;
			if (c != ' ' && c != '\n' && c != '\t') {
				break;
			}
		}

		return out;
	}
	public boolean hasNext() {
		return element < read.length();
	}
	public int getLine() {
		return line;
	}
}
