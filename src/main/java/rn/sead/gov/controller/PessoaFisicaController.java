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

import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.service.PessoaFisicaService;

@RestController
@RequestMapping(value = "/deficiencias")
public class PessoaFisicaController {
	
	@Autowired
	private PessoaFisicaService service;

	@GetMapping
	public ResponseEntity<List<PessoaFisica>> findAll() {
		List<PessoaFisica> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<PessoaFisica> create(@RequestBody PessoaFisica entity) {
		entity = service.create(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PessoaFisica> findById(@PathVariable Long id) {
		return (ResponseEntity<PessoaFisica>) service.findById(id).map(record -> ResponseEntity.ok().body((PessoaFisica) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisica entity) {

		return (ResponseEntity<PessoaFisica>) service.update(id, entity).map(record -> ResponseEntity.ok().body((PessoaFisica) record))
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
