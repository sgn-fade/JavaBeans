package TableBean;

import java.util.ArrayList;

public class DataSheet {
    private ArrayList<Data> data = new ArrayList<Data>();

    public int size() {
        return data.size();
    }
    public Data getDataItem(int index) {
        return data.get(index);
    }
    public void addRow(){
        data.add(new Data());
    }
    public void deleteRow(){
        data.remove(data.size() - 1);
    }
}
