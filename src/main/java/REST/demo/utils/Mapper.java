package REST.demo.utils;

import REST.demo.domains.city.City;
import REST.demo.domains.city.dto.CityDTO;
import REST.demo.domains.province.Province;
import REST.demo.domains.province.dto.ProvinceDTO;

public class Mapper {

    public static ProvinceDTO toProvinceDTO(Province province) {
        return ProvinceDTO.builder()
                .id(province.getId())
                .name(province.getName())
                .build();
    }

    public static CityDTO toCityDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .people(city.getPeople())
                .provinceId(city.getProvince().getId())
                .build();
    }

}
