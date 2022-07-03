package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.apilogin.modelo.Contenido;

public interface ContenidoRepositorio extends JpaRepository<Contenido, Integer> {

}