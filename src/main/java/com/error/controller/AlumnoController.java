package com.error.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.error.model.Alumno;

@RestController
@RequestMapping("/api")
public class AlumnoController {

	@GetMapping("/alumnos")
	public Alumno getMethodName() {
		Alumno al1 = new Alumno();
		al1.setApellido("correa");
		al1.setNombre("breiner");
		al1.setEmail("breiner.correa@unmsm.edu.pe");
		al1.setEmail("xxx");
		
		return al1;
	}
	
	@PostMapping("/alumnoEnvia")
	public ResponseEntity<?> getMethodName(@Valid @RequestBody Alumno al1, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			/*List<String> errors = new ArrayList<>();
			for (FieldError err : result.getFieldErrors()) {
				errors.add("El campo "+err.getField()+":"+err.getDefaultMessage());
			}*/
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo "+err.getField()+":"+err.getDefaultMessage())
					.collect(Collectors.toList());
					
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("mensaje", "El cliente ha sido creado con exito");
		response.put("cliente", al1);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/alumno")
	public Alumno mensaje() {
		Alumno al1 = new Alumno();
		al1.setEmail("brdfdf");
		al1 = null;
		return al1;
	}

}
