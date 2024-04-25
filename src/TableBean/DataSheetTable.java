package TableBean;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class DataSheetTable extends JPanel {
    DataSheetTableModel tableModel = new DataSheetTableModel();
    JTable table = new JTable();

    public DataSheetTable() {

        setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();
        JButton addButton = new JButton("Add (+)");
        JButton deleteButton = new JButton("Del (-)");
        southPanel.add(addButton);
        southPanel.add(deleteButton);
        add(southPanel, BorderLayout.SOUTH);

        DataSheet dataSheet = new DataSheet();
        tableModel.setDataSheet(dataSheet);
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);



        addButton.addActionListener(e -> {
            tableModel.addRow();
        });
        deleteButton.addActionListener(e -> {
            tableModel.deleteRow();
        });




    }
    public DataSheetTableModel getTableModel() {
        return tableModel;
    }
    public void setTableModel(DataSheetTableModel tableModel) {
        this.tableModel = tableModel;
        table.revalidate();
    }
    public void revalidate() {
        if (table != null) table.revalidate();
    }
}
