package rn.sead.gov.controller;

import java.net.URI;
import java.util.Optional;


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

import rn.sead.gov.dto.ServidorDtoRequest;
import rn.sead.gov.dto.ServidorDtoResponse;
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
	public ResponseEntity<Servidor> create(@RequestBody ServidorDtoRequest dto) {
		Servidor servidor = service.create(dto.convertToDtoServidor());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servidor.getId()).toUri();
		return ResponseEntity.created(uri).body(servidor);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<ServidorDtoResponse> findById(@PathVariable Long id) {
		Optional<Servidor> servidor = service.findById(id);
		if(servidor.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			ServidorDtoResponse servidorDtoResponse = new ServidorDtoResponse(servidor.get());
			return ResponseEntity.ok().body(servidorDtoResponse);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Servidor> update(@PathVariable Long id, @RequestBody ServidorDtoRequest dto) {
		Servidor servidor = dto.convertToDtoServidor();
		return service.update(id, servidor)
				.map(rec -> ResponseEntity.ok().body(rec)).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Servidor> disable(@PathVariable Long id) {
		if (Boolean.TRUE.equals(service.softDelete(id))) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
