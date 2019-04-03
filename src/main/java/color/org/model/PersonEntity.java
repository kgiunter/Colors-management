package color.org.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Entity
@Table(name = "PERSON_MANAGEMENT")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "idGenerator")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LASTNAME", nullable = false, length = 20)
    private String lastname;

    @Column(name = "FIRSTNAME", nullable = false, length = 20)
    private String firstname;

    @Column(name = "ADDRESS", nullable = false, length = 20)
    private String address;

    @Column(name = "COLOR", nullable = false, length = 20)
    private String color;
}
