package REST.demo.domains.province;

import REST.demo.domains.province.dto.ProvinceApi;
import REST.demo.domains.province.dto.ProvinceDTO;

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
public class ProvinceService {

    private ProvinceRepository repository;

    @Autowired
    public ProvinceService(ProvinceRepository repository) {
        this.repository = repository;
    }

    public List<ProvinceDTO> getAll(PageableRequest pageableRequest) {
        Page<Province> provincePage = repository.findAllByNameIsNotNull(
                PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize())
        );
        return provincePage.stream()
                .map(Mapper::toProvinceDTO)
                .collect(Collectors.toList());
    }

    public ProvinceDTO getOne(Long id) throws NotFoundException {
        Province province = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        return new ProvinceDTO(province);
    }

    public ProvinceDTO create(ProvinceApi api) {
        Province province = new Province(api);
        repository.save(province);
        return new ProvinceDTO(province);
    }

    public ProvinceDTO update(Long id, ProvinceApi api) throws NotFoundException {
        Province province = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        province.update(api);
        province = repository.save(province);
        return new ProvinceDTO(province);
    }

    public void delete(Long id) throws NotFoundException {
        Province province = repository.findById(id)
                .orElseThrow( () -> new NotFoundException("NotFoundException"));
        repository.delete(province);
    }
}
