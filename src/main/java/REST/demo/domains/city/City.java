package REST.demo.domains.city;

import REST.demo.domains.city.dto.CityApi;
import REST.demo.domains.province.Province;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long people;

    @ManyToOne(optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;


    public City(CityApi api, Province province) {
        this.name = api.getName();
        this.people = api.getPeople();
        this.province = province;
    }

    public void update(CityApi api, Province province) {
        this.name = api.getName();
        this.people = api.getPeople();
        this.province = province;
    }

}
