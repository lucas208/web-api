package rn.sead.gov.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import rn.sead.gov.model.Servidor;
import rn.sead.gov.service.ServidorService;

@RestController
@RequestMapping(value = "/servidores")
public class ServidorController {

	@Autowired
	private ServidorService service;

	@GetMapping
	public ResponseEntity<Page<Servidor>> findAll(Pageable pageable) {
		Page<Servidor> page =service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}

	@PostMapping
	public ResponseEntity<Servidor> create(@RequestBody Servidor entity) {
		entity = service.create(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Servidor> findById(@PathVariable Long id) {
		return (ResponseEntity<Servidor>) service.findById(id)
				.map(record -> ResponseEntity.ok().body((Servidor) record)).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Servidor> update(@PathVariable Long id, @RequestBody Servidor entity) {

		return (ResponseEntity<Servidor>) service.update(id, entity)
				.map(record -> ResponseEntity.ok().body((Servidor) record)).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> disable(@PathVariable Long id) {
		if (service.softDelete(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
