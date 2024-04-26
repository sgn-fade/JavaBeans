package MyApplication;

import TableBean.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private final DataSheetTable dataSheetTable = new DataSheetTable();
    private DataSheet dataSheet = new DataSheet();
    private final DataSheetGraph graph = new DataSheetGraph();

    public Window() {
        setResizable(false);
        setSize(600, 800);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        dataSheet.addDataItem(new Data());


        JPanel southPanel = new JPanel();
        JButton readButton = new JButton("Відкрити");
        JButton saveButton = new JButton("Зберегти");
        JButton clearButton = new JButton("Очистити");
        JButton closeButton = new JButton("Завершити");

        closeButton.addActionListener(arg0 -> dispose());
        clearButton.addActionListener(e -> {
            dataSheet = new DataSheet();
            dataSheet.addDataItem(new Data());
            dataSheetTable.getTableModel().setDataSheet(dataSheet);
            dataSheetTable.revalidate();
            graph.setDataSheet(dataSheet);
        });
        saveButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
//                    DataSheetToXML.saveXMLDoc(
//                            DataSheetToXML.createDataSheetDOM(dataSheet), fileName);
                JOptionPane.showMessageDialog(null,
                        "File " + fileName.trim() + " saved!", "Результати збережені",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
//                    dataSheet = SAXRead.XMLReadData(fileName);
                    dataSheetTable.getTableModel().setDataSheet(dataSheet);
                    dataSheetTable.revalidate();
                    graph.setDataSheet(dataSheet);
                }
            }
        });
        southPanel.add(readButton);
        southPanel.add(saveButton);
        southPanel.add(clearButton);
        southPanel.add(closeButton);
        add(southPanel, BorderLayout.SOUTH);

        graph.setDataSheet(dataSheet);

        dataSheetTable.getTableModel().setDataSheet(dataSheet);
        dataSheetTable.getTableModel().addDataSheetChangeListener(
                e -> {
                    graph.revalidate();
                    graph.repaint();
                });

        add(graph, BorderLayout.EAST);
        add(dataSheetTable, BorderLayout.WEST);
    }
}
