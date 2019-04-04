# Colors-management

## The application manages people and their favorite colors

GET /persons
---
Returns all persons from persons.csv

GET /persons/{id}
---
Returns person with given id from persons.csv

GET /persons/color/{color}
---
Returns all persons with given favorite color from persons.csv

POST /persons
---
Creates new person and adds him to persons.csv, returns all persons from from persons.csv with new added person
