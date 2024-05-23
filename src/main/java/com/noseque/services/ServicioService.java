package com.noseque.services;

import com.noseque.entities.Servicio;
import com.noseque.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public Servicio crearServicio(Servicio servicio) {
        this.servicioRepository.save(servicio);
        return servicio;
    }
}
