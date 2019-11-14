package com.fluxit.desafioTecnico.apidesafio.services;

import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fluxit.desafioTecnico.apidesafio.DTO.UserDTO;
import com.fluxit.desafioTecnico.apidesafio.model.user.UserDAO;
import com.fluxit.desafioTecnico.apidesafio.repositories.user.UserDAORepository;


@Service
public class JwtUserDetailsService implements UserDetailsService
{
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserDAORepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		UserDAO user = userRepository.findByUsername(username);
		if (user == null)
		{
			throw new UsernameNotFoundException("No se encontro un usuario con ese nombre y/o contraseña: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	@Transactional
	public UserDAO save(UserDTO newUser) throws URISyntaxException
	{
		UserDAO userToSave = new UserDAO();
		userToSave.setUsername(newUser.getUsername());
		userToSave.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(userToSave);
	}
	
}