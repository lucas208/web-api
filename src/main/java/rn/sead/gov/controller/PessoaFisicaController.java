package rn.sead.gov.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rn.sead.gov.dto.PessoaFisicaDtoRequest;
import rn.sead.gov.dto.PessoaFisicaDtoResponse;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.service.PessoaFisicaService;
import rn.sead.gov.service.ServidorService;

@RestController
@RequestMapping(value = "/pessoas_fisicas")
public class PessoaFisicaController {

	PessoaFisicaService service;
	
	public PessoaFisicaController(PessoaFisicaService service, ServidorService servidorService) {
		this.service = service;
	}

	@PostMapping
	public PessoaFisica create(@RequestBody PessoaFisicaDtoRequest dto) {
		return service.create(dto.convertToPessoaFisica());
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
		return (ResponseEntity<PessoaFisica>) service.update(id, entity)
				.map(record -> ResponseEntity.ok().body((PessoaFisica) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<PessoaFisica>> findAll() {
		List<PessoaFisica> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping(path = "/{id}")
	public void disable(@PathVariable Long id) {
		service.softDelete(id);
	}
}
