package REST.demo.domains.city;

import REST.demo.domains.city.dto.CityApi;
import REST.demo.domains.city.dto.CityDTO;

import REST.demo.domains.city.City;
import REST.demo.domains.city.CityRepository;
import REST.demo.domains.province.Province;
import REST.demo.domains.province.ProvinceRepository;
import REST.demo.utils.PageableRequest;
import REST.demo.utils.Mapper;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityRepository repository;
    private ProvinceRepository provinceRepository;

    @Autowired
    public CityService(CityRepository repository, ProvinceRepository provinceRepository) {

        this.repository = repository;
        this.provinceRepository = provinceRepository;
    }

    public List<CityDTO> getAll(PageableRequest pageableRequest) {
        Page<City> cityPage = repository.findAllByNameIsNotNull(
                PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize())
        );
        return cityPage.stream()
                .map(Mapper::toCityDTO)
                .collect(Collectors.toList());
    }

    public List<CityDTO> getByProvince(PageableRequest pageableRequest, Long provinceId) throws NotFoundException {
        Province province = provinceRepository.findById(provinceId)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        Page<City> cityPage = repository.findAllByProvince(
                PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize()),
                province
        );
        return cityPage.stream()
                .map(Mapper::toCityDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getOne(Long id) throws NotFoundException {
        City city = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        return new CityDTO(city);
    }

    public CityDTO create(CityApi api) throws NotFoundException {
        Province province = provinceRepository.findById(api.getProvinceId())
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        City city = new City(api, province);
        repository.save(city);
        return new CityDTO(city);
    }

    public CityDTO update(Long id, CityApi api) throws NotFoundException {
        City city = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        Province province = provinceRepository.findById(api.getProvinceId())
                .orElseThrow( () -> new NotFoundException("NotFoundException"));;
        city.update(api, province);
        city = repository.save(city);
        return new CityDTO(city);
    }

    public void delete(Long id) throws NotFoundException {
        City city = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        repository.delete(city);
    }

}
