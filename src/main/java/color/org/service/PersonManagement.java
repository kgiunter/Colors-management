package color.org.service;

import color.org.model.Person;
import color.org.model.PersonJson;

import java.util.List;

public interface PersonManagement {

    List<Person> getPersons();

    Person getPersonById(Integer id);

    List<Person> getPersonsByColor(String color);

    List<Person> createNewPerson(PersonJson personJson);

}
