package com.noseque.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.noseque.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,String> {
}
