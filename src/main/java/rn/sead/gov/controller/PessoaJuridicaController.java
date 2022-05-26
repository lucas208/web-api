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

import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.service.PessoaJuridicaService;

@RestController
@RequestMapping(value = "/pessoas_juridicas")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService service;

	@GetMapping
	public ResponseEntity<Page<PessoaJuridica>> findAll(Pageable pageable) {
		Page<PessoaJuridica> page =service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}

	@PostMapping
	public ResponseEntity<PessoaJuridica> create(@RequestBody PessoaJuridica entity) {
		entity = service.create(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
		return (ResponseEntity<PessoaJuridica>) service.findById(id)
				.map(record -> ResponseEntity.ok().body((PessoaJuridica) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridica entity) {

		return (ResponseEntity<PessoaJuridica>) service.update(id, entity)
				.map(record -> ResponseEntity.ok().body((PessoaJuridica) record))
				.orElse(ResponseEntity.notFound().build());
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
