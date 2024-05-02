package TableBean;

import java.util.ArrayList;

public class DataSheet {
    private ArrayList<Data> dataTable = new ArrayList<Data>();

    public ArrayList<Data> getDataTable() {
        return dataTable;
    }

    public int size() {
        return dataTable.size();
    }
    public Data getDataItem(int index) {
        return dataTable.get(index);
    }
    public void addDataItem(Data data) {
        dataTable.add(data);
    }
    public void removeDataItem(int index) {
        dataTable.remove(index);
    }
}
