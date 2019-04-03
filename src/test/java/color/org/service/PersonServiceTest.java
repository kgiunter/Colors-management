package color.org.service;

import color.org.model.CustomException;
import color.org.model.Person;
import color.org.model.PersonJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersonServiceTest.TestContextConfiguration.class)
public class PersonServiceTest {

    @Autowired
    private PersonService tested;
    @MockBean
    private CsvModelToPersonConverting csvModelToPersonConverting;
    @MockBean
    private ReadingPersonFromCsv readingPersonFromCsv;
    @MockBean
    private PersonToCsvModelConverting personToCsvModelConverting;

    private String FIRSTNAME = "Andreas";
    private String LASTNAME = "Werner";
    private String CITY = "Berlin";
    private String COLOR = "blue";
    private Long ZIPCODE = 7777L;
    private Integer ID = 1;

    private String FIRSTNAME_2 = "Tom";
    private String LASTNAME_2 = "Braun";
    private String CITY_2 = "Munich";
    private String COLOR_2 = "green";
    private Long ZIPCODE_2 = 1111L;
    private Integer ID_2 = 2;


    @Test
    public void test_getAllPersonsFromCsv() {
        // Given
        Person person1 = getPerson1();
        Person person2 = getPerson2();
        List<Person> persons = asList(person1,person2);

        // When
        when(csvModelToPersonConverting.getPersons()).thenReturn(persons);

        // Then
        List<Person> actual = tested.getPersons();

        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertNotNull(actual.get(0));
        assertEquals(person1.getId(), actual.get(0).getId());
        assertEquals(person1.getLastName(), actual.get(0).getLastName());
        assertEquals(person1.getName(), actual.get(0).getName());
        assertEquals(person1.getZipCode(), actual.get(0).getZipCode());
        assertEquals(person1.getCity(), actual.get(0).getCity());
        assertEquals(person1.getColor(), actual.get(0).getColor());

        verify(csvModelToPersonConverting, times(1)).getPersons();
    }

    @Test
    public void test_getOnePersonByGivenId() {
        // Given
        Person person1 = getPerson1();
        Person person2 = getPerson2();
        List<Person> persons = asList(person1, person2);

        // When
        when(csvModelToPersonConverting.getPersons()).thenReturn(persons);

        // Then
        Person actual = tested.getPersonById(ID_2);

        assertNotNull(actual);
        assertEquals(ID_2, actual.getId());
        assertEquals(person2.getLastName(), actual.getLastName());
        assertEquals(person2.getName(), actual.getName());
        assertEquals(person2.getZipCode(), actual.getZipCode());
        assertEquals(person2.getCity(), actual.getCity());
        assertEquals(person2.getColor(), actual.getColor());

        verify(csvModelToPersonConverting, times(1)).getPersons();
    }

    @Test
    public void test_getAllPersonsFromCsvByGivenColor() {
        // Given
        Person person1 = getPerson1();
        Person person2 = getPerson2();
        List<Person> persons = asList(person1, person2);

        // When
        when(csvModelToPersonConverting.getPersons()).thenReturn(persons);

        // Then
        List<Person> actual = tested.getPersonsByColor(COLOR);

        assertNotNull(actual);
        assertNotNull(actual.get(0));
        assertEquals(person1.getId(), actual.get(0).getId());
        assertEquals(person1.getLastName(), actual.get(0).getLastName());
        assertEquals(person1.getName(), actual.get(0).getName());
        assertEquals(person1.getZipCode(), actual.get(0).getZipCode());
        assertEquals(person1.getCity(), actual.get(0).getCity());
        assertEquals(person1.getColor(), actual.get(0).getColor());

        verify(csvModelToPersonConverting, times(1)).getPersons();
    }

    @Test
    public void createNewPerson() {
        // Given
        Person person1 = getPerson1();
        Person person3 = getPerson3();
        List<Person> persons = asList(person1,person3);
        PersonJson personJson = getPersonJson();

        // When
        doNothing().when(personToCsvModelConverting).createNewPerson(getPersonJson());
        when(csvModelToPersonConverting.getPersons()).thenReturn(persons);

        // Then
        List<Person> actual = tested.createNewPerson(personJson);

        assertNotNull(actual);
        assertNotNull(actual.get(1));
        assertEquals(person3.getId(), actual.get(1).getId());
        assertEquals(person3.getLastName(), actual.get(1).getLastName());
        assertEquals(person3.getName(), actual.get(1).getName());
        assertEquals(person3.getZipCode(), actual.get(1).getZipCode());
        assertEquals(person3.getCity(), actual.get(1).getCity());
        assertEquals(person3.getColor(), actual.get(1).getColor());

        verify(personToCsvModelConverting, times(1)).createNewPerson(personJson);
        verify(csvModelToPersonConverting, times(1)).getPersons();
    }

    @Test(expected = CustomException.class)
    public void test_getPersonsByColor_WithCustomException(){
        // Given
        Person person1 = getPerson1();
        Person person2 = getPerson2();
        List<Person> persons = asList(person1, person2);

        // When
        when(csvModelToPersonConverting.getPersons()).thenReturn(persons);

        // Then
        tested.getPersonsByColor("red");
        verify(csvModelToPersonConverting, times(1)).getPersons();
    }


    private Person getPerson3(){
        return new Person(1,"Mike", "Holger",
                2323L, "London", "green");
    }

    private PersonJson getPersonJson(){
        return new PersonJson("Mike", "Holger", "London",
                "2323", 2);
    }

    private Person getPerson1(){
        return new Person(ID, FIRSTNAME, LASTNAME, ZIPCODE, CITY, COLOR);
    }

    private Person getPerson2(){
        return new Person(ID_2, FIRSTNAME_2, LASTNAME_2, ZIPCODE_2, CITY_2, COLOR_2);
    }

    @Configuration
    @ComponentScan(useDefaultFilters = false,
            includeFilters = @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = PersonService.class))
    static class TestContextConfiguration {
    }
}