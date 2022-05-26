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

import rn.sead.gov.dto.DeficienciaDtoRequest;
import rn.sead.gov.dto.DeficienciaDtoResponse;
import rn.sead.gov.model.Deficiencia;
import rn.sead.gov.service.DeficienciaService;

@RestController
@RequestMapping(value = "/deficiencias")
public class DeficienciaController {

	@Autowired
	private DeficienciaService service;

	@GetMapping
	public ResponseEntity<List<Deficiencia>> findAll() {
		List<Deficiencia> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Deficiencia> create(@RequestBody DeficienciaDtoRequest dto) {
		Deficiencia deficiencia = service.create(dto.convertToDeficiencia());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deficiencia.getId()).toUri();
		return ResponseEntity.created(uri).body(deficiencia);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<DeficienciaDtoResponse> findById(@PathVariable Long id) {
		Optional<Deficiencia> deficiencia = service.findById(id);

		if(deficiencia.isEmpty()) {
			return ResponseEntity.notFound().build();

		} else {
			DeficienciaDtoResponse deficienciaDtoResponse = new DeficienciaDtoResponse(deficiencia.get());
			return ResponseEntity.ok().body(deficienciaDtoResponse);

		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Deficiencia> update(@PathVariable Long id, @RequestBody DeficienciaDtoRequest dto) {
		Deficiencia deficiencia = dto.convertToDeficiencia();
		return (ResponseEntity<Deficiencia>) service.update(id, deficiencia)
				.map(record -> ResponseEntity.ok().body((Deficiencia) record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public void disable(@PathVariable Long id) {
		service.softDelete(id);
	}
}
