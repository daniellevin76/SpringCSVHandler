package io.handlingCSVData.CSVdata;

import java.util.Comparator;
import java.util.List;

class ListComp implements Comparator<List<String>> {

    @Override
    public int compare(List<String> l1, List<String> l2) {
        int index = DataHandler.getSortIndex();
        return l1.get(index).compareTo(l2.get(index));
    }

}
