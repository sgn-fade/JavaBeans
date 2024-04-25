package TableBean;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DataSheetTable table = new DataSheetTable();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 400, 300, 400);
        frame.add(table);
        frame.setVisible(true);

    }
}
