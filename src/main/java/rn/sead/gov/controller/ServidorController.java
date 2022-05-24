package rn.sead.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rn.sead.gov.model.Servidor;
import rn.sead.gov.service.ServidorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/servidores")
public class ServidorController {

    @Autowired
    private ServidorService service;

    @GetMapping
    public ResponseEntity<List<Servidor>> findAll() {
        List<Servidor> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Servidor> create(@RequestBody Servidor entity) {
        entity = service.create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Servidor> findById(@PathVariable Long id) {
        return (ResponseEntity<Servidor>) service.findById(id).map(record -> ResponseEntity.ok().body((Servidor) record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Servidor> update(@PathVariable Long id, @RequestBody Servidor entity) {

        return (ResponseEntity<Servidor>) service.update(id, entity).map(record -> ResponseEntity.ok().body((Servidor) record))
                .orElse(ResponseEntity.notFound().build());
    }

	@DeleteMapping(path = "/{id}")
	public void disable(@PathVariable Long id) {
		service.softDelete(id);
	}

//	@DeleteMapping(path = { "/{id}" })
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		if (service.delete(id)) {
//			return ResponseEntity.ok().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

}
