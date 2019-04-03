package color.org.service;

import color.org.model.CustomException;
import color.org.model.Person;
import color.org.model.PersonJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author egiunter
 *
 */
@RequiredArgsConstructor
@Service
public class PersonService implements PersonManagement {

    private final CsvModelToPersonConverting csvToPerson;
    private final PersonToCsvModelConverting personToCsv;

    /**
     * GET /persons
     * @return list of all persons from csv file
     */
    public List<Person> getPersons() {
        return csvToPerson.getPersons();
    }

    /**
     * GET /persons/{id}
     * @param id unique persons ID - number of row from csv file
     * @return person object by given ID
     */
    public Person getPersonById(Integer id) {
        Optional<Person> person = csvToPerson.getPersons().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (person.isPresent()) return person.get();
        else throw new CustomException("There isn't any person with such ID!");
    }

    /**
     * GET /persons/color/{color}
     * @param color name of color which person likes
     * @return list of all persons who likes given color
     */
    public List<Person> getPersonsByColor(String color) {
        List<Person> persons = csvToPerson.getPersons().stream()
                .filter(p -> p.getColor().equals(color))
                .collect(Collectors.toList());
        if (!persons.isEmpty()) return persons;
        else throw new CustomException("There isn't any person with such favorite color!");
    }

    /**
     * POST /persons
     * @param personJson model of person with fields: lastname, firstname, address, numberOfColor
     * @return list of all persons with new added person
     */
    public List<Person> createNewPerson(PersonJson personJson) {

        personToCsv.createNewPerson(personJson);
        return csvToPerson.getPersons();
    }
}
