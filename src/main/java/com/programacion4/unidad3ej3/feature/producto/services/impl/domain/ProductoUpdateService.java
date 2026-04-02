package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoUpdateService implements IProductoUpdateService {

    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto execute(Long id, ProductoUpdateRequestDto dto) {
        // 1. Buscamos el producto. Si no existe, lanzamos la 404 que ya programamos.
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. ID no encontrado: " + id));

        // 2. Actualizamos los campos (puedes elegir cuáles permitir cambiar)
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        // Si el DTO trae nombre, podrías usar tu lógica de Capitalize aquí también.

        // 3. Guardamos los cambios
        Producto productoActualizado = productoRepository.save(producto);

        // 4. Devolvemos el DTO de respuesta usando el Mapper
        return ProductoMapper.toResponseDto(productoActualizado);
    }
}
