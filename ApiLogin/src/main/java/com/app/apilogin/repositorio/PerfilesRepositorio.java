package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.apilogin.modelo.Perfiles;

@Repository
public interface PerfilesRepositorio extends JpaRepository<Perfiles, Long> {

}