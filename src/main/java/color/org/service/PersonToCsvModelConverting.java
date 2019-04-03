package color.org.service;

import color.org.model.CsvModelPerson;
import color.org.model.PersonJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PersonToCsvModelConverting {

    private final WritingPersonToCsv service;

    void createNewPerson(PersonJson personJson) {

        CsvModelPerson csvModelPerson = new CsvModelPerson();
        csvModelPerson.setFirstName(personJson.getFirstname());
        csvModelPerson.setLastName(personJson.getLastname());
        csvModelPerson.setAddress(getAddress(personJson.getZipcode(), personJson.getCity()));
        csvModelPerson.setNumberOfColor(personJson.getNumberOfColor());

        service.writeCsvFromBean(csvModelPerson);
    }

    private String getAddress(String zipcode, String city){
        String address = "";

        if (nonNull(zipcode) && nonNull (city)) address = zipcode + ' ' + city;
        if (nonNull(zipcode) && isNull(city)) address = zipcode;
        if (isNull(zipcode) && nonNull(city)) address = city;
        return address;
    }
}
