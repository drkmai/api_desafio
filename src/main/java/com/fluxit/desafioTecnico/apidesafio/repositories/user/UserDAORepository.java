package com.fluxit.desafioTecnico.apidesafio.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluxit.desafioTecnico.apidesafio.model.user.UserDAO;

@Repository
public interface UserDAORepository extends JpaRepository<UserDAO,Long>
{

	UserDAO findByUsername(String username);

}
