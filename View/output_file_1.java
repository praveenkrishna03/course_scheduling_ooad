package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class output_file_1 extends JPanel {
    private DefaultTableModel tableModel;

    public output_file_1(String[][] data) {
        int numRows = data.length + 1;
        int numCols = data[0].length + 1;
    
        tableModel = new DefaultTableModel(numRows, numCols);
    
        for (int i = 1; i < numCols; i++) {
            tableModel.setValueAt(data[0][i - 1], 0, i);
        }
    
        for (int i = 1; i < numRows; i++) {
            tableModel.setValueAt(data[i - 1][0], i, 0);
        }
    
        JTable table = new JTable(tableModel);
        
        // Hide the heading column
        table.getTableHeader().setVisible(false);
    
        JScrollPane scrollPane = new JScrollPane(table);
    
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
    
}
