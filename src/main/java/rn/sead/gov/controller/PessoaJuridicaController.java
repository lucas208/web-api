package rn.sead.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rn.sead.gov.dto.PessoaJuridicaDtoRequest;
import rn.sead.gov.dto.PessoaJuridicaDtoResponse;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.service.PessoaJuridicaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pessoas_juridicas")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService service;

	@GetMapping
	public ResponseEntity<List<PessoaJuridica>> findAll() {
		List<PessoaJuridica> list = service.findAll();
		return ResponseEntity.ok().body(list);
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
		return (ResponseEntity<PessoaJuridica>) service.update(id, pessoaJuridica)
				.map(record -> ResponseEntity.ok().body((PessoaJuridica) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public void disable(@PathVariable Long id) {
		service.softDelete(id);
	}
}
