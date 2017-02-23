// Frame.java

package util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import analyzer.Compiler;
import analyzer.LexicalAnalyzer;
import analyzer.SyntaxAnalyzer;
import exception.FormatException;
import statics.Files;
import statics.Thompson;

/**
 * Team 3
 * 
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class Frame extends JFrame implements ActionListener {
	private static final String[][] fileTypes = {
			{"Text", "Automaton", "C", "Grammar"},
			{"txt", "aut", "cpp", "txt"}};
	private static final long serialVersionUID = 1L;
	public static final int TXT = 0, AUT = 1, PAS = 2, GRM = 3;
	private Compiler currentCompiler;
	private JScrollPane currentPane;
	private String path = System.getProperty("user.dir");

	public Frame() throws FormatException {
		// Setting the Window.
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setTitle("Compilador");
		setMenuBar();
		super.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		super.setVisible(true);

		currentCompiler = new Compiler();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menuItem = (JMenuItem) event.getSource();
		String option = menuItem.getText();

		try {
			if (option.equals("Desde archivo N"))
				fileRegularExpressionToAFN();
			else if (option.equals("Escribir N"))
				writeRegularExpressionToAFN();
			else if (option.equals("Desde archivo D"))
				fileRegularExpressionToAFD();
			else if (option.equals("Escribir D"))
				writeRegularExpressionToAFD();
			else if (option.equals("Analizador léxico"))
				analyzeLexically();
			else if (option.equals("Primeros y siguientes"))
				firstAndNext();
			else if (option.equals("Colección canónica"))
				canonicalCollection();
			else if (option.equals("Tabla A. Sintáctico"))
				syntaxAnalyzeTable();
			else if (option.equals("A. Sintáctico"))
				analyzeSyntax();
			else
				throw new FormatException(
						"Esta función aún no está implementada.");
		} catch (FormatException e) {
			JOptionPane.showMessageDialog(this, e);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Algo salió mal, revisa consola...");
			e.printStackTrace();
		}

		super.setSize(1000, 500);
		super.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

	private void analyzeLexically() throws FormatException {
		Files.export(
				currentCompiler
						.analyzeLexical(Files.getCompleteFile(getNewPath(PAS))),
				LexicalAnalyzer.types,
				JOptionPane.showInputDialog(this, "Nombre del archivo"));
	}
	private void analyzeSyntax() throws FormatException {
		String[][] table = currentCompiler
				.analyzeSyntax(Files.getCompleteFile(getNewPath(PAS)));
		setPane(new JTable(table, table[0]));
	}

	private void canonicalCollection() throws FormatException {
		SyntaxAnalyzer syntax = new SyntaxAnalyzer(getNewPath(TXT));
		String[][] cc = syntax.canonicalCollection();
		setPane(new JTable(cc, cc[0]));
	}

	private void fileRegularExpressionToAFD() throws FormatException {
		String file = Files.getCompleteFile(getNewPath(TXT));

		if (file.split("\n").length != 1)
			throw new FormatException("Tu archivo tiene más de una línea");

		setPane(new AFD(Thompson.algorithm(file)).getTable());
	}

	private void fileRegularExpressionToAFN() throws FormatException {
		String file = Files.getCompleteFile(getNewPath(TXT));

		if (file.split("\n").length != 1)
			throw new FormatException("Algo anda mal con tu archivo.");

		setPane(Thompson.algorithm(file).getTable());
	}

	private void firstAndNext() throws FormatException {
		SyntaxAnalyzer syntax = new SyntaxAnalyzer(getNewPath(GRM));
		String[][] fn = syntax.firstAndNext();
		setPane(new JTable(fn, fn[0]));
	}

	private String getNewPath(int ending) throws FormatException {
		JFileChooser openFile = new JFileChooser(path);

		openFile.setFileFilter(new FileNameExtensionFilter(
				fileTypes[0][ending] + " files", fileTypes[1][ending]));
		int selection = openFile.showOpenDialog(this);

		if (selection == JFileChooser.APPROVE_OPTION)
			return openFile.getSelectedFile().getAbsolutePath();
		else
			throw new FormatException("No escogiste ningún archivo.");
	}

	private void setMenuBar() {
		// Setting the Menus
		JMenuBar menuBar = new JMenuBar();
		JMenu menu, submenu;
		JMenuItem menuItem;
		// File Menu
		// We decided to quit this.
		// Lexical analyzer Menu
		menu = new JMenu("A. Léxico");
		submenu = new JMenu("AFN");
		menuItem = new JMenuItem("Desde archivo N");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menuItem = new JMenuItem("Escribir N");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menu.add(submenu);
		submenu = new JMenu("AFD");
		menuItem = new JMenuItem("Desde archivo D");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menuItem = new JMenuItem("Escribir D");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menu.add(submenu);
		menuItem = new JMenuItem("Analizador léxico");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		// Syntax analyzer Menu
		menu = new JMenu("A. Sintáctico");
		menuItem = new JMenuItem("Primeros y siguientes");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Colección canónica");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Tabla A. Sintáctico");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("A. Sintáctico");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		// Semantic analyzer menu
		menu = new JMenu("A. Semántico");
		menuBar.add(menu);
		// Help Menu
		menu = new JMenu("Ayuda");
		menuItem = new JMenuItem("Cómo usar");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		super.setJMenuBar(menuBar);
	}

	private void setPane(Component c) {
		if (currentPane != null)
			super.remove(currentPane);

		currentPane = new JScrollPane(c,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		super.add(currentPane);
	}

	private void syntaxAnalyzeTable() throws FormatException {
		SyntaxAnalyzer syntax = new SyntaxAnalyzer(getNewPath(GRM));
		String[][] sat = syntax.syntaxAnalyzeTable();
		setPane(new JTable(sat, sat[0]));
	}

	private void writeRegularExpressionToAFD() throws FormatException {
		setPane(new AFD(Thompson.algorithm(JOptionPane.showInputDialog(this,
				"Escribe la expresión regular"))).getTable());
	}

	private void writeRegularExpressionToAFN() throws FormatException {
		setPane(Thompson.algorithm(JOptionPane.showInputDialog(this,
				"Escribe la expresión regular")).getTable());
	}
}
