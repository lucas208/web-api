package rn.sead.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rn.sead.gov.model.Deficiencia;
import rn.sead.gov.service.DeficienciaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/deficiencias")
public class DeficienciaController {

    @Autowired
    private DeficienciaService service;

    @GetMapping
    public ResponseEntity<List<Deficiencia>> findAll() {
        List<Deficiencia> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Deficiencia> create(@RequestBody Deficiencia entity) {
        entity = service.create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Deficiencia> findById(@PathVariable Long id) {
        return (ResponseEntity<Deficiencia>) service.findById(id).map(record -> ResponseEntity.ok().body((Deficiencia) record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Deficiencia> update(@PathVariable Long id, @RequestBody Deficiencia entity) {

        return (ResponseEntity<Deficiencia>) service.update(id, entity).map(record -> ResponseEntity.ok().body((Deficiencia) record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
