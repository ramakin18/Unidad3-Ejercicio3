package com.programacion4.unidad3ej3.feature.producto.controllers.patch;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoToggleStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoPatchController {

    private final IProductoToggleStatusService toggleStatusService;

    @PatchMapping("/{id}/status")
    public ResponseEntity<ProductoResponseDto> toggleStatus(@PathVariable Long id) {
        return ResponseEntity.ok(toggleStatusService.execute(id));
    }
}
