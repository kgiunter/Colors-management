package color.org.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonJson {

    @NotNull
    String lastname;
    @NotNull
    String firstname;
    @NotNull
    String zipcode;
    @NotNull
    String city;
    @NotNull
    Integer numberOfColor;
}
