package REST.demo.domains.province;

import REST.demo.domains.province.dto.ProvinceApi;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "province")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public Province(ProvinceApi api) {
        this.name = api.getName();
    }

    public void update(ProvinceApi api) {
        this.name = api.getName();
    }
}
