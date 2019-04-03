package color.org.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvModelPerson {

    @CsvBindByPosition(position = 0)
    private String lastName;
    @CsvBindByPosition(position = 1)
    private String firstName;
    @CsvBindByPosition(position = 2)
    private String address;
    @CsvBindByPosition(position = 3)
    private Integer numberOfColor;
}
