package color.org.service;

import color.org.exceptionhandler.CustomException;
import color.org.model.CsvModelPerson;
import color.org.utils.CsvTransfer;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ReadingPersonFromCsv {

    List<CsvModelPerson> getPersonCsvPojoObjects() {
        Path path = getPath();
        return beanBuilder(path);
    }

    private List<CsvModelPerson> beanBuilder(Path path) {
        CsvTransfer csvTransfer = new CsvTransfer();
        ColumnPositionMappingStrategy<CsvModelPerson> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(CsvModelPerson.class);

        handleReader(path, ms, csvTransfer);

        return csvTransfer.getCsvList();
    }

    private void handleReader(Path path, ColumnPositionMappingStrategy<CsvModelPerson> ms, CsvTransfer csvTransfer){
        Reader reader;
        try {
            reader = Files.newBufferedReader(path);
            CsvToBean cb = new CsvToBeanBuilder(reader)
                    .withType(CsvModelPerson.class)
                    .withMappingStrategy(ms)
                    .build();
            csvTransfer.setCsvList(cb.parse());
            reader.close();
        } catch (IOException e) {
            throw new CustomException("Something is wrong with Reader");
        }
    }

    private Path getPath(){
        try {
            return Paths.get(
                    ClassLoader.getSystemResource("persons.csv").toURI());
        } catch (Exception e) {
            throw new CustomException("There is something wrong with path creating..");
        }
    }
}
