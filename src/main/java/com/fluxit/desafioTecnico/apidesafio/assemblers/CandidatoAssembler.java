package com.fluxit.desafioTecnico.apidesafio.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.fluxit.desafioTecnico.apidesafio.controllers.CandidatoController;
import com.fluxit.desafioTecnico.apidesafio.model.candidato.Candidato;

@Component
public class CandidatoAssembler  implements ResourceAssembler<Candidato, Resource<Candidato>>
{

	  @Override
	  public Resource<Candidato> toResource(Candidato candidato)
	  {
	    return new Resource<>(candidato,
	      linkTo(methodOn(CandidatoController.class).one(candidato.getId())).withSelfRel());
	  }

}