package color.org.service;

import color.org.model.CustomException;
import color.org.model.CsvModelPerson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class WritingPersonToCsv {

    void writeCsvFromBean(CsvModelPerson csvPojo) {
        Path path = getPath();
        writeFromBeanToCsv(path, csvPojo);
    }

    private void writeFromBeanToCsv(Path path, CsvModelPerson csvPojo) {
         try {
             Writer writer  = new FileWriter(path.toString(), true);

             StatefulBeanToCsv<CsvModelPerson> sbc = new StatefulBeanToCsvBuilder<CsvModelPerson>(writer)
                     .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                     .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                     .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                     .build();

             sbc.write(csvPojo);
             writer.close();
         } catch (Exception e) {
             throw new CustomException("Something is wrong with Writer..");
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
