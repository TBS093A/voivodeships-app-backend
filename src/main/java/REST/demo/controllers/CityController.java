package REST.demo.controllers;


import REST.demo.domains.city.dto.CityApi;
import REST.demo.domains.city.dto.CityDTO;
import REST.demo.domains.city.CityService;
import REST.demo.utils.PageableRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javassist.NotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Cities")
public class CityController {

    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<CityDTO>> getAllCities(
            @PathVariable int page,
            @PathVariable int size
    ) {
        PageableRequest pageableRequest = new PageableRequest(page, size);
        return ResponseEntity.ok(cityService.getAll(pageableRequest));
    }

    @GetMapping("/province/{provinceId}/{page}/{size}")
    public ResponseEntity<List<CityDTO>> getByProvince(
            @PathVariable Long provinceId,
            @PathVariable int page,
            @PathVariable int size
    ) throws NotFoundException {
        PageableRequest pageableRequest = new PageableRequest(page, size);
        return ResponseEntity.ok(cityService.getByProvince(pageableRequest, provinceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getOne(
            @PathVariable Long id
    ) throws NotFoundException {
        return ResponseEntity.ok(cityService.getOne(id));
    }

    @PostMapping("/")
    public ResponseEntity<CityDTO> create(
            @RequestBody CityApi api
    ) throws NotFoundException {
        return ResponseEntity.ok(cityService.create(api));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(
            @RequestBody CityApi api,
            @PathVariable Long id
    ) throws NotFoundException {
        return ResponseEntity.ok(cityService.update(id, api));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) throws NotFoundException {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
