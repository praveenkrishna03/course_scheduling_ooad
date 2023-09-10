package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class output_file_1 extends JPanel {
    private DefaultTableModel tableModel;

    public output_file_1(List<String> inp_1_rooms, List<String> inp_1_timing) {
        // Calculate the number of rows and columns for the table
        int numRows = inp_1_rooms.size() + 1;
        int numCols = inp_1_timing.size() + 1;

        // Create the table model with the specified number of rows and columns
        tableModel = new DefaultTableModel(numRows, numCols);

        // Populate the first row with inp_1_timing elements
        for (int i = 1; i < numCols; i++) {
            tableModel.setValueAt(inp_1_timing.get(i - 1), 0, i);
        }

        // Populate the first column with inp_1_rooms elements
        for (int i = 1; i < numRows; i++) {
            tableModel.setValueAt(inp_1_rooms.get(i - 1), i, 0);
        }

        JTable table = new JTable(tableModel);
        
        // Hide the heading column
        table.getTableHeader().setVisible(false);

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}
