package rn.sead.gov.controller;

import java.net.URI;

import java.util.Optional;

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
import rn.sead.gov.dto.PessoaFisicaDtoRequest;
import rn.sead.gov.dto.PessoaFisicaDtoResponse;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.service.*;

@RestController
@RequestMapping(value = "/pessoas_fisicas")
public class PessoaFisicaController {

	PessoaFisicaService service;
	
	public PessoaFisicaController(PessoaFisicaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<PessoaFisica> create(@RequestBody PessoaFisicaDtoRequest dto) {
		PessoaFisica pessoaFisica = service.create(dto.convertToPessoaFisica());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaFisica.getId()).toUri();
		return  ResponseEntity.created(uri).body(pessoaFisica);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PessoaFisicaDtoResponse> findById(@PathVariable Long id) {
		Optional<PessoaFisica> optionalPessoaFisica = service.findById(id);

		if (optionalPessoaFisica.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			PessoaFisicaDtoResponse pessoaFisica = new PessoaFisicaDtoResponse(optionalPessoaFisica.get());
			return ResponseEntity.ok().body(pessoaFisica);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisicaDtoRequest dto) {
		PessoaFisica entity = dto.convertToPessoaFisica();
		return service.update(id, entity)
				.map(rec -> ResponseEntity.ok().body(rec))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Page<PessoaFisica>> findAll(Pageable pageable) {
		Page<PessoaFisica> page =service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<PessoaFisica> disable(@PathVariable Long id) {
		if (Boolean.TRUE.equals(service.softDelete(id))) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
