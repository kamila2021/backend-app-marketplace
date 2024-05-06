package com.noseque.resolvers;

import com.noseque.entities.Producto;
import com.noseque.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductoGraphQLController {

    @Autowired
    private ProductoRepository productoRepository;

    @QueryMapping
    public List<Producto> listarProductos()
    {
        return productoRepository.findAll();
    }
    @QueryMapping
    public Producto listarProductoPorId(@Argument String id)
    {
        return productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Producto %s no encontrado",id))
        );
    }
}
