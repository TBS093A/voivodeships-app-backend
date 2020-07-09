package REST.demo.domains.city.dto;

import REST.demo.domains.city.City;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;
    private Long provinceId;
    private String name;
    private Long people;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.people = city.getPeople();
        this.provinceId = city.getProvince().getId();
    }

}
