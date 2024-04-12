package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.controller;

import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product.ProductCreateDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.model.dto.product.ProductDTO;
import com.danilomontoya.hexagonalarchitecturedemo.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 11:58 PM
 **/
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    public static final String DISABLE_PRODUCT_MESSAGE = "Producto Inactivado: %s";
    public static final String ENABLE_PRODUCT_MESSAGE = "Producto Activado: %s";
    public static final String CANCELLED_PRODUCT_MESSAGE = "Producto cancelado correctamente: %s";
    private final ProductService productService;

    @Operation(summary = "Listar Productos", description = "Obtiene la lista de todos los Productos registrados")
    @ApiResponse(responseCode = "200", description = "Listado con la lista de Productos ó una lista vacia en caso de no existir Productos")
    @GetMapping("all")
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @Operation(summary = "Obtener Producto por ID", description = "Busca un Producto especifico por el ID")
    @Parameter(description = "Id del Producto que se quiere buscar", required = true, name = "id", in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200", description = "Producto encontrado con el id especificado", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "404", description = "Cuando no se encuentra un Producto por el id especificado")
    @GetMapping("productById/{id}")
    public ProductDTO findById(@PathVariable(name = "id") String id){
        return productService.findById(id);
    }

    @Operation(summary = "Crear Producto", description = "Crea un nuevo Producto en el sistema")
    @ApiResponse(responseCode = "200", description = "Cuando se crea el Producto correctamente", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Cuando no se envia un dato que es requerido")
    @ApiResponse(responseCode = "404", description = "Cuando no se ecuentra el cliente + JSON cone el mensaje de error", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "422", description = "Cuando hay alguna restricción no valida para crear el Producto")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada al crear el Producto")
    @PostMapping("create")
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.create(productCreateDTO);
    }

    @Operation(summary = "Activar Producto", description = "Activa el producto con el ID enviado")
    @ApiResponse(responseCode = "200", description = "Cuando se activa el producto correctamente", content = {@Content(mediaType = "String")})
    @ApiResponse(responseCode = "404", description = "Cuando no se ecuentra el producto + JSON con el mensaje de error", content = {@Content(mediaType = "JSON")})
    @PutMapping("enable/{id}")
    public String enableProduct(@PathVariable(name = "id") String id){
        return String.format(ENABLE_PRODUCT_MESSAGE, productService.enable(id));
    }

    @Operation(summary = "Inactivar Producto", description = "Inactiva el producto con el ID enviado")
    @ApiResponse(responseCode = "200", description = "Cuando se Inactiva el producto correctamente", content = {@Content(mediaType = "String")})
    @ApiResponse(responseCode = "404", description = "Cuando no se ecuentra el producto + JSON con el mensaje de error", content = {@Content(mediaType = "JSON")})
    @PutMapping("disable/{id}")
    public String disableProduct(@PathVariable(name = "id") String id){
        return String.format(DISABLE_PRODUCT_MESSAGE, productService.disable(id));
    }

    @Operation(summary = "Cancelar Producto", description = "Cancela el producto con el ID enviado")
    @ApiResponse(responseCode = "200", description = "Cuando se Cancela el producto correctamente", content = {@Content(mediaType = "String")})
    @ApiResponse(responseCode = "404", description = "Cuando no se ecuentra el producto + JSON con el mensaje de error", content = {@Content(mediaType = "JSON")})
    @ApiResponse(responseCode = "405", description = "Cuando el saldo de la cuenta a cancelar no se encuentra en $0 (cero)", content = {@Content(mediaType = "JSON")})
    @PutMapping("cancel/{id}")
    public String cancelProduct(@PathVariable(name = "id") String id){
        return String.format(CANCELLED_PRODUCT_MESSAGE, productService.cancel(id));
    }
}
