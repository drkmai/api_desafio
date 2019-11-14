package com.fluxit.desafioTecnico.apidesafio.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.fluxit.desafioTecnico.apidesafio.DTO.CandidatoDTO;
import com.fluxit.desafioTecnico.apidesafio.controllers.CandidatoController;

@Component
public class CandidatoDTOAssembler  implements ResourceAssembler<CandidatoDTO, Resource<CandidatoDTO>>
{

	  @Override
	  public Resource<CandidatoDTO> toResource(CandidatoDTO candidato)
	  {
	    return new Resource<>(candidato,
	  	      linkTo(methodOn(CandidatoController.class).all(candidato.getNombreYApellido(),candidato.getDni(),null)).withRel("candidatos"));
	  }

}