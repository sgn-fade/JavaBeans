package MyApplication;

import TableBean.*;
import xml.DataSheetToXML;
import xml.SAXPar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private final DataSheetTable dataSheetTable = new DataSheetTable();
    private DataSheet dataSheet = new DataSheet();
    private final DataSheetGraph graph = new DataSheetGraph();

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 610, 500);
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
            dataSheetTable.setDataSheet(dataSheet);
            dataSheetTable.revalidate();
            graph.setDataSheet(dataSheet);
        });
        saveButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                DataSheetToXML.saveXMLDoc(dataSheet, fileName);
                JOptionPane.showMessageDialog(null,
                        "File " + fileName.trim() + " saved!", "Результати збережені",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        readButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                dataSheet = SAXPar.XMLReadData(fileName);
                dataSheetTable.setDataSheet(dataSheet);
                dataSheetTable.revalidate();
                graph.setDataSheet(dataSheet);
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
        graph.setPreferredSize(new Dimension(400, 300));
        dataSheetTable.setPreferredSize(new Dimension(200, 300));
        add(graph, BorderLayout.EAST);
        add(dataSheetTable, BorderLayout.WEST);
    }
}
