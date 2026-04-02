package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoDeleteService implements IProductoDeleteService {

    private final IProductoRepository productoRepository;

    @Override
    public void execute(Long id) {

        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. No existe el producto con ID: " + id);
        }


        productoRepository.deleteById(id);
    }
}
