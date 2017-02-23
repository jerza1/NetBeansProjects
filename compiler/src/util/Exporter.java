package util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JTable;

import exception.FormatException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Team 3
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class Exporter {
	// Attributes:
	private File file;
	private List<String> fileNames;
	private List<JTable> tableList;

	// Operations:
	// Constructor:
	public Exporter(File file, List<JTable> table, List<String> fileNames) throws FormatException {
		this.file = file;
		this.tableList = table;
		this.fileNames = fileNames;

		if (fileNames.size() != tableList.size())
			throw new FormatException("Algo anda mal, sorry.");
		export();
	}

	// Export:
	public boolean export() {
		try {
			// Output stream to write on a File.
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			// Represents out .xls file and need an OutputStream to knows where
			// to store data.
			WritableWorkbook w = Workbook.createWorkbook(out);

			for (int index = 0; index < tableList.size(); index++) {
				JTable table = tableList.get(index);

				// Excel has many sheets. This creates or takes the sheet.
				// Sets the Tab and index of tableList.
				WritableSheet s = w.createSheet(fileNames.get(index), 0);

				// Writing with tableList applying a blue TAHOMA font.
				WritableFont font = new WritableFont(WritableFont.TAHOMA);
				font.setColour(jxl.format.Colour.BLUE);
				WritableCellFormat cellformat = new WritableCellFormat(font);
				for (int i = 0; i < table.getColumnCount(); i++) {
					for (int j = 0; j < table.getRowCount(); j++) {
						Object object = table.getValueAt(j, i);
						s.addCell(new Label(i, j, String.valueOf(object)));
					}
				}
				for (int i = 0; i < table.getColumnCount(); i++) {
					String titulo = table.getColumnName(i);
					Object objeto = titulo;
					s.addCell(new Label(i, 0, String.valueOf(objeto), cellformat));
				}
			}

			w.write();
			w.close();
			out.close();

			return true;
		} catch (IOException | WriteException e) {
			return false;
		}
	}
}