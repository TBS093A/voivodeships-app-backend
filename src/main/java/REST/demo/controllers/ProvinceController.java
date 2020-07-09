package REST.demo.controllers;

import REST.demo.domains.province.dto.ProvinceApi;
import REST.demo.domains.province.dto.ProvinceDTO;
import REST.demo.domains.province.ProvinceService;
import REST.demo.utils.PageableRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javassist.NotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Provinces")
public class ProvinceController {

    ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces(
            @PathVariable int page,
            @PathVariable int size
            ) {
        PageableRequest pageableRequest = new PageableRequest(page, size);
        return ResponseEntity.ok(provinceService.getAll(pageableRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDTO> getOne(
            @PathVariable Long id
    ) throws NotFoundException {
        return ResponseEntity.ok(provinceService.getOne(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProvinceDTO> create(
            @RequestBody ProvinceApi api
            ) {
        return ResponseEntity.ok(provinceService.create(api));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinceDTO> update(
            @RequestBody ProvinceApi api,
            @PathVariable Long id
    ) throws NotFoundException {
        return ResponseEntity.ok(provinceService.update(id, api));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) throws NotFoundException {
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

