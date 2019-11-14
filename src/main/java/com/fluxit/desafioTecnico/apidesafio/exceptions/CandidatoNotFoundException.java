package com.fluxit.desafioTecnico.apidesafio.exceptions;

public class CandidatoNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 6765738537899302295L;

	public CandidatoNotFoundException(Long id)
	{
		super("No se encontro candidato con id: "+ id);
	}
}
