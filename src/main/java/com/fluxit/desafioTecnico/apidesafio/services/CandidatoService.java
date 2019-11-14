package com.fluxit.desafioTecnico.apidesafio.services;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fluxit.desafioTecnico.apidesafio.DTO.CandidatoDTO;
import com.fluxit.desafioTecnico.apidesafio.assemblers.CandidatoAssembler;
import com.fluxit.desafioTecnico.apidesafio.assemblers.CandidatoDTOAssembler;
import com.fluxit.desafioTecnico.apidesafio.controllers.CandidatoController;
import com.fluxit.desafioTecnico.apidesafio.exceptions.CandidatoNotFoundException;
import com.fluxit.desafioTecnico.apidesafio.model.candidato.Candidato;
import com.fluxit.desafioTecnico.apidesafio.repositories.candidatos.CandidatoRepository;
@Service
public class CandidatoService
{
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private CandidatoAssembler assembler;
	@Autowired
	private CandidatoDTOAssembler assemblerDTO;
	
	@SuppressWarnings("unused") //Logger.
	private static final Logger log = LoggerFactory.getLogger(CandidatoService.class);
	
	@Transactional
	public Resources<Resource<CandidatoDTO>> all(String nombre,String dni, Integer page)
	{
		if (page == null)
			page = 0;
		Integer size = 25;
		Candidato example = new Candidato();
		example.setNombreYApellido(nombre);
		example.setDni(dni);
		Pageable pageable = PageRequest.of(page,size);
		ExampleMatcher matcher = ExampleMatcher.matchingAny() // porque es uno o el otro.
                .withMatcher("nombreYApellido", match -> match.contains().ignoreCase())
                .withMatcher("dni", match -> match.contains());
		List<Resource<CandidatoDTO>> items = candidatoRepository.findAll(Example.of(example,matcher), pageable).stream()
				.map(CandidatoDTO::toDTO)
			    .map(assemblerDTO::toResource)
			    .collect(Collectors.toList());

			  return new Resources<>(items,
			    linkTo(methodOn(CandidatoController.class).all(nombre,dni, page)).withSelfRel());
	}

	public Resource<Candidato> one(Long id)
	{
		Candidato candidato = candidatoRepository.findById(id).orElseThrow(()-> new CandidatoNotFoundException(id));
		System.out.println(candidato.getFechaNacimiento());
		return assembler.toResource(candidato);
	}

	@Transactional
	public ResponseEntity<?> save(Candidato newCandidato) throws URISyntaxException {
		Resource<Candidato> resource = assembler.toResource(candidatoRepository.save(newCandidato));
		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
	}

	@Transactional
	public ResponseEntity<?> replaceCandidato(Candidato newCandidato, Long id) throws URISyntaxException
	{
		  Candidato updatedCandidato =  candidatoRepository.findById(id)
	    	      .map(candidato -> {
	    	    	  candidato.setNombreYApellido(newCandidato.getNombreYApellido());
	    	    	  candidato.setFechaNacimiento(newCandidato.getFechaNacimiento());
	    	    	  candidato.setTelefono(newCandidato.getTelefono());
	    	    	  candidato.setEmail(newCandidato.getEmail());
	    	    	  candidato.setDomicilio(newCandidato.getDomicilio());
	    	        return candidatoRepository.save(candidato);
	    	      })
	    	      .orElseGet(() -> {
	    	    	  newCandidato.setId(id);
	    	        return candidatoRepository.save(newCandidato);
	    	      });
	    Resource<Candidato> resource = assembler.toResource(updatedCandidato);

	    return ResponseEntity
	      .created(new URI(resource.getId().expand().getHref()))
	      .body(resource);
	}

	@Transactional
	public ResponseEntity<?> deleteById(Long id)
	{
		candidatoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
