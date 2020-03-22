package REST.demo.domains.city.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityApi {

    private Long people;
    private String name;
    private Long provinceId;

}
