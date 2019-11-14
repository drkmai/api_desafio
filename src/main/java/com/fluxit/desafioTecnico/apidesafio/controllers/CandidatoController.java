package com.fluxit.desafioTecnico.apidesafio.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fluxit.desafioTecnico.apidesafio.DTO.CandidatoDTO;
import com.fluxit.desafioTecnico.apidesafio.model.candidato.Candidato;
import com.fluxit.desafioTecnico.apidesafio.services.CandidatoService;

@RestController
public class CandidatoController
{

	@Autowired
	private CandidatoService candidatoRepository;
	
	@GetMapping("/candidatos")
	public Resources<Resource<CandidatoDTO>> all(@RequestParam(required = false) String nombre,@RequestParam(required = false) String dni, @RequestParam(required = false)Integer page)
	{
		return candidatoRepository.all(nombre,dni, page);
	}

	@GetMapping("/candidatos/{id}")
	public Resource<Candidato> one(@PathVariable Long id)
	{
		return candidatoRepository.one(id);
	}
	
	@PostMapping("/candidatos")
	public ResponseEntity<?> newCandidato(@RequestBody Candidato newCandidato) throws URISyntaxException
	{
	    return  candidatoRepository.save(newCandidato);
	}
	
	@PutMapping(path = "/candidatos/{id}")
	public ResponseEntity<?> replaceCandidato(@RequestBody Candidato candidato, @PathVariable Long id) throws URISyntaxException
	{
		return candidatoRepository.replaceCandidato(candidato, id);
	}
	
	@DeleteMapping("/candidatos/{id}")
	public ResponseEntity<?> deleteCandidato(@PathVariable Long id)
	{
		return candidatoRepository.deleteById(id);
	}
	
}
