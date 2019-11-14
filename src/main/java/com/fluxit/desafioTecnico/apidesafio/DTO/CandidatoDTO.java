package com.fluxit.desafioTecnico.apidesafio.DTO;

import com.fluxit.desafioTecnico.apidesafio.model.candidato.Candidato;

public class CandidatoDTO
{
	private String nombreYApellido;
	private String dni;
	
	public CandidatoDTO(String nombreYApellido, String dni)
	{
		setNombreYApellido(nombreYApellido);
		setDni(dni);
	}
	
	public String getNombreYApellido()
	{
		return nombreYApellido;
	}
	
	public void setNombreYApellido(String nombreYApellido)
	{
		this.nombreYApellido = nombreYApellido;
	}
	
	public String getDni()
	{
		return dni;
	}
	
	public void setDni(String dni)
	{
		this.dni = dni;
	}
	
	public static CandidatoDTO toDTO(Candidato candidato)
	{
		return new CandidatoDTO(candidato.getNombreYApellido(), candidato.getDni());
	}
}
