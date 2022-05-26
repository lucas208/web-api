package rn.sead.gov.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import rn.sead.gov.dto.EnderecoDtoRequest;
import rn.sead.gov.dto.EnderecoDtoResponse;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Endereco> create(@RequestBody EnderecoDtoRequest dto) {
		Endereco endereco = service.create(dto.convertToEndereco());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(endereco);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<EnderecoDtoResponse> findById(@PathVariable Long id) {
		Optional<Endereco> endereco = service.findById(id);
		if(endereco.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			EnderecoDtoResponse enderecoDtoResponse = new EnderecoDtoResponse(endereco.get());
			return ResponseEntity.ok().body(enderecoDtoResponse);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody EnderecoDtoRequest dto) {
		Endereco endereco = dto.convertToEndereco();
		return (ResponseEntity<Endereco>) service.update(id, endereco)
				.map(record -> ResponseEntity.ok().body((Endereco) record)).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public void disable(@PathVariable Long id) {
		service.softDelete(id);
	}
}
