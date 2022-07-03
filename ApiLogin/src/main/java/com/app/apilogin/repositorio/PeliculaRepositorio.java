package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.apilogin.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {

}