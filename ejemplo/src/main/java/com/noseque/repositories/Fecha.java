package com.noseque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface Fecha extends JpaRepository<Fecha, LocalDate> {
}
