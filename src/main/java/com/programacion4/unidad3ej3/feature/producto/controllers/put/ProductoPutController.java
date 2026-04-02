package com.programacion4.unidad3ej3.feature.producto.controllers.put;

import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoPutController {

    private final IProductoUpdateService updateService;

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoResponseDto> update(@PathVariable Long id, @RequestBody ProductoUpdateRequestDto dto) {
        return ResponseEntity.ok(updateService.execute(id, dto));
    }
}
