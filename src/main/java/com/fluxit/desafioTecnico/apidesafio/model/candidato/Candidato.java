package com.fluxit.desafioTecnico.apidesafio.model.candidato;

import java.util.Date;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Candidato
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombreYApellido;
	@NaturalId
	private String dni;
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	private String domicilio;
	private String telefono;
	private String email;
	
	@Transient
	private static final String emailPattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	@Transient
	private static final String dniPattern = "[\\d]+";
	
	public Candidato()
	{
		
	}
	
	public Candidato(String nombreYApellido, String dni, Date fechaNacimiento, String telefono, String email, String domicilio)
	{
		setNombreYApellido(nombreYApellido);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
		setTelefono(telefono);
		setEmail(email);
		setDomicilio(domicilio);
	}
	
	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id=id;	
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
		if (dni != null)
			if (!Pattern.matches(dniPattern, dni))
				throw new IllegalArgumentException("Ingrese solo numeros en el dni.");
		this.dni = dni;
	}

	public Date getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento)
	{
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		if (email!=null)
			if (!Pattern.matches(emailPattern, email))
				throw new IllegalArgumentException("El e-mail ingresado no tiene formato valido.");
		this.email = email;
	}

	public String getDomicilio()
	{
		return domicilio;
	}

	public void setDomicilio(String domicilio)
	{
		this.domicilio = domicilio;
	}
	
}
