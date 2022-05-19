package rn.sead.gov.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import rn.sead.gov.model.Vinculo;
import rn.sead.gov.service.VinculoService;

@RestController
@RequestMapping(value = "/vinculos")
public class VinculoController {
	
	@Autowired
	private VinculoService service;

	@GetMapping
	public ResponseEntity<List<Vinculo>> findAll() {
		List<Vinculo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Vinculo> create(@RequestBody Vinculo entity) {
		entity = service.create(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Vinculo> findById(@PathVariable Long id) {
		return (ResponseEntity<Vinculo>) service.findById(id).map(record -> ResponseEntity.ok().body((Vinculo) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vinculo> update(@PathVariable Long id, @RequestBody Vinculo entity) {

		return (ResponseEntity<Vinculo>) service.update(id, entity).map(record -> ResponseEntity.ok().body((Vinculo) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
