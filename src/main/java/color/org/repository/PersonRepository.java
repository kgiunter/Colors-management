package color.org.repository;

import color.org.model.PersonEntity;
import color.org.model.PersonJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByColor(String color);

    List<PersonEntity> findAll();

    PersonEntity getOne(Long id);

    List<PersonEntity> addNewPerson(PersonJson personJson);
}
