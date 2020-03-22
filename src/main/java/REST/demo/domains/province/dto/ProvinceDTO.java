package REST.demo.domains.province.dto;

import REST.demo.domains.province.Province;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {

    private Long id;
    private String name;

    public ProvinceDTO(Province province) {
        this.id = province.getId();
        this.name = province.getName();
    }

}
