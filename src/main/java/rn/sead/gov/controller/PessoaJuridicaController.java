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
import rn.sead.gov.dto.PessoaJuridicaDtoRequest;
import rn.sead.gov.dto.PessoaJuridicaDtoResponse;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.service.PessoaJuridicaService;


import java.util.Optional;

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
	public ResponseEntity<PessoaJuridica> create(@RequestBody PessoaJuridicaDtoRequest dto) {
		PessoaJuridica pessoaJuridica = service.create(dto.convertToPessoaJuridica());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaJuridica.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoaJuridica);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PessoaJuridicaDtoResponse> findById(@PathVariable Long id) {
		Optional<PessoaJuridica> pessoaJuridica = service.findById(id);

		if(pessoaJuridica.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			PessoaJuridicaDtoResponse pessoaJuridicaDtoResponse = new PessoaJuridicaDtoResponse(pessoaJuridica.get());
			return ResponseEntity.ok().body(pessoaJuridicaDtoResponse);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridicaDtoRequest dto) {
		PessoaJuridica pessoaJuridica = dto.convertToPessoaJuridica();
		return service.update(id, pessoaJuridica)
				.map(rec -> ResponseEntity.ok().body(rec))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<PessoaJuridica> disable(@PathVariable Long id) {
		if (Boolean.TRUE.equals(service.softDelete(id))) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
