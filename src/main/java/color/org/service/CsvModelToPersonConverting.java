package color.org.service;

import color.org.model.Person;
import color.org.model.CsvModelPerson;
import color.org.utils.ColorsMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CsvModelToPersonConverting {

    private final ReadingPersonFromCsv service;
    private Integer personID;

    List<Person> getPersons() {
        personID = 1;

        return service.getPersonCsvPojoObjects()
                .stream()
                .map(person -> convert((CsvModelPerson) person))
                .collect(Collectors.toList());
    }

    private Person convert(CsvModelPerson csvPojo) {
        Person person = new Person();
        person.setId(personID++);
        person.setName(csvPojo.getFirstName());
        person.setLastName(csvPojo.getLastName());
        addColor(csvPojo, person);
        addAddress(csvPojo, person);

        return person;
    }

    private void addAddress(CsvModelPerson csvModelPerson, Person person){
        String address = csvModelPerson.getAddress().trim();

        String zipcode = address.substring(0, address.indexOf(' '));
        person.setZipCode(Long.parseLong(zipcode));
        person.setCity(address.substring(address.indexOf(' ')+1));
    }

    private void addColor(CsvModelPerson csvModelPerson, Person person){
        person.setColor(getColor(csvModelPerson.getNumberOfColor()));
    }

    private String getColor(Integer numberOfColor){
        switch (numberOfColor) {
            case 1: return "blue";
            case 2: return "green";
            case 3: return "purple";
            case 4: return "red";
            case 5: return "yellow";
            case 6: return "turquois";
            case 7: return "white";
            default: return "some color";
        }
//        return ColorsMap.COLORS.stream()
//                .filter(k->k.getLeft().equals(numberOfColor))
//                .findFirst().orElseGet("")
   }
}
