package com.programacion4.unidad3ej3.feature.producto.controllers.get;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoFindAllService;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoFindByIdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoGetController {

    private final IProductoFindAllService findAllService;
    private final IProductoFindByIdService findByIdService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductoResponseDto>> findAll() {
        // US02: Devuelve la lista de todos los productos
        return ResponseEntity.ok(findAllService.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> findById(@PathVariable Long id) {
        // US03: Devuelve un producto o lanza 404 si no existe
        return ResponseEntity.ok(findByIdService.execute(id));
    }
}
