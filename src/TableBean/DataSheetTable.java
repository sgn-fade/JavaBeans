package TableBean;

import javax.swing.*;
import java.awt.*;


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
        setTableModel(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        addButton.addActionListener(e -> {
            tableModel.getDataSheet().addDataItem(new Data());
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            table.revalidate();
            tableModel.fireDataSheetChange();
        });
        deleteButton.addActionListener(e -> {
            if (tableModel.getRowCount() > 1) {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                tableModel.getDataSheet().removeDataItem(
                        tableModel.getDataSheet().size() - 1);
                table.revalidate();
                tableModel.fireDataSheetChange();
            } else {
                tableModel.getDataSheet().getDataItem(0).setDate("");
                tableModel.getDataSheet().getDataItem(0).setX(0);
                tableModel.getDataSheet().getDataItem(0).setY(0);
                table.revalidate();
                table.repaint();
                tableModel.fireDataSheetChange();
            }
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
