package com.noseque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EjemploApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemploApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(CategoriaRepository categoriaRepository, ProductoRepository productoRepository)
	{
		Random random = new Random();
		return args ->
		{
			List.of("Computadoras","Impresoras","Smartphones").forEach(cat->{
				Categoria categoria = Categoria.builder().nombre(cat).build();
				categoriaRepository.save(categoria);
			});
			 categoriaRepository.findAll().forEach(categoria -> {
				 for (int i = 0; i<10;i++)
				 {
					 Producto producto = Producto.builder()
							 .id(UUID.randomUUID().toString())
							 .nombre(categoria.getNombre()+i)
							 .precio(100+Math.random()*500)
							 .cantidad(random.nextInt(100))
							 .categoria(categoria)
							 .build();
					 productoRepository.save(producto);
				 }
			 });
		};
	}*/

}
