package color.org.utils;

import color.org.model.CsvModelPerson;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CsvTransfer {

    private List<CsvModelPerson> csvList;

    public void setCsvList(List<CsvModelPerson> csvList) {
        this.csvList = csvList;
    }

    public List<CsvModelPerson> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<>();
    }
}