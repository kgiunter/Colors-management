package color.org.controller;

import color.org.exceptionhandler.CustomException;
import color.org.model.Error;
import color.org.model.Person;
import color.org.model.PersonJson;
import color.org.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequiredArgsConstructor
public class ColorController {

    private final PersonService service;

    @GetMapping(value = "/persons", produces = {"application/json"})
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(service.getPersons(), HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{id}", produces = {"application/json"})
    public ResponseEntity<Person> getPersonsByID (@PathVariable("id") String id) {
        return new ResponseEntity<>(service.getPersonById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/persons/color/{color}", produces = {"application/json"})
    public ResponseEntity<List<Person>> getPersonsByColor(@PathVariable("color") String color) {
        return new ResponseEntity<>(service.getPersonsByColor(color), HttpStatus.OK);
    }

    @PostMapping(value = "/persons", produces = {"application/json"})
    public ResponseEntity<List<Person>> createNewPerson(@RequestBody PersonJson personJson) {
        return new ResponseEntity<>(service.createNewPerson(personJson), HttpStatus.OK);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<Error> handleColorsRestExceptions(CustomException e) {
        return new ResponseEntity<>(new Error(BAD_REQUEST.name(), e.getMessage(), BAD_REQUEST), BAD_REQUEST);
    }
}
