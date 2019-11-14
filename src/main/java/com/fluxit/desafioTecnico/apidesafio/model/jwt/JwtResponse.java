package com.fluxit.desafioTecnico.apidesafio.model.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable
{

	private static final long serialVersionUID = -333817695248471815L;
	
	private final String jwttoken;
	
	public JwtResponse(String jwttoken)
	{
		this.jwttoken = jwttoken;
	}
	
	public String getToken()
	{
		return this.jwttoken;
	}
}