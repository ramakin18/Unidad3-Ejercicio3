package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoFindAllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductoFindAllService implements IProductoFindAllService {

    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> execute() {

        List<Producto> productos = (List<Producto>) productoRepository.findAll();


        return productos.stream()
                .filter(producto -> !producto.isEstaEliminado())
                .map(ProductoMapper::toResponseDto)
                .toList();

    }
}
