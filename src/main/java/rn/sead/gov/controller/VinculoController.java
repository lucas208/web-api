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

import rn.sead.gov.dto.VinculoDtoRequest;
import rn.sead.gov.dto.VinculoDtoResponse;
import rn.sead.gov.model.Vinculo;
import rn.sead.gov.service.VinculoService;

@RestController
@RequestMapping(value = "/vinculos")
public class VinculoController {

	@Autowired
	private VinculoService service;

	@GetMapping
	public ResponseEntity<Page<Vinculo>> findAll(Pageable pageable) {
		Page<Vinculo> page =service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}

	@PostMapping
	public ResponseEntity<Vinculo> create(@RequestBody VinculoDtoRequest dto) {
		Vinculo vinculo = service.create(dto.convertToDtoVinculo());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vinculo.getId()).toUri();
		return ResponseEntity.created(uri).body(vinculo);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<VinculoDtoResponse> findById(@PathVariable Long id) {
		Optional<Vinculo> vinculo = service.findById(id);

		if(vinculo.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			VinculoDtoResponse vinculoDtoResponse = new VinculoDtoResponse(vinculo.get());
			return ResponseEntity.ok().body(vinculoDtoResponse);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Vinculo> update(@PathVariable Long id, @RequestBody VinculoDtoRequest dto) {
		Vinculo vinculo = dto.convertToDtoVinculo();
		return service.update(id, vinculo)
				.map(rec -> ResponseEntity.ok().body(rec)).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Vinculo> disable(@PathVariable Long id) {
		if (Boolean.TRUE.equals(service.softDelete(id))) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
