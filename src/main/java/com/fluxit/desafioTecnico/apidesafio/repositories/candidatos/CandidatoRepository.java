package com.fluxit.desafioTecnico.apidesafio.repositories.candidatos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluxit.desafioTecnico.apidesafio.model.candidato.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Long>{
	
	List<Candidato> findAllByNombreYApellidoContaining(String nombreYApellido, Pageable pageable);
	List<Candidato> findAllByDniContaining(String dni, Pageable pageable);
}
