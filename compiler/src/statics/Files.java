// Files management

package statics;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;

import exception.FormatException;
import util.Exporter;

/**
 * Team 3
 * 
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class Files {
	public static void export(String[][][] tables, String[] names, String path)
			throws FormatException {
		if (!path.endsWith(".xls"))
			path += ".xls";
		File file = new File(path);
		Vector<String> nameVector = new Vector<String>();
		Vector<JTable> tableVector = new Vector<JTable>();
		for (int i = 0; i < tables.length; i++) {
			nameVector.add(names[i]);
			tableVector.add(new JTable(tables[i], tables[i][0]));
		}
		new Exporter(file, tableVector, nameVector).export();
	}

	public static String getCompleteFile(String path) {
		FileReader fr;
		String file = "";

		try {
			fr = new FileReader(path);
			Scanner in = new Scanner(fr);

			for (int i = 0; in.hasNextLine(); i++)
				if (i > 0)
					file += "\n" + in.nextLine();
				else
					file += in.nextLine();

			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	public static void printConsole(String[][] t) {
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++)
				System.out.print(t[i][j] + "\t");
			System.out.println();
		}
	}
}
