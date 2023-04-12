package com.nero.apiRestbasico.repository;

import com.nero.apiRestbasico.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
}
