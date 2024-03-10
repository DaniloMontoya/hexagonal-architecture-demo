package co.com.flypass.f2xfinancialentity.controller;

import co.com.flypass.f2xfinancialentity.model.dto.ClientDTO;

import co.com.flypass.f2xfinancialentity.model.dto.ProductCreateDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductDTO;
import co.com.flypass.f2xfinancialentity.model.dto.ProductUpdateDTO;
import co.com.flypass.f2xfinancialentity.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
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
    @ApiResponse(responseCode = "409", description = "Cuando ya existe un Producto con el número de identificación")
    @ApiResponse(responseCode = "422", description = "Cuando hay alguna restricción no valida para crear el Producto")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada al crear el Producto")
    @PostMapping("create")
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.create(productCreateDTO);
    }

    @Operation(summary = "Actualizar Producto", description = "Actualiza el Producto con los datos indocados")
    @ApiResponse(responseCode = "200", description = "Cuando se actualiza el Producto correctamente", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Cuando no se envia un dato que es requerido")
    @ApiResponse(responseCode = "422", description = "Cuando hay alguna restricción no valida para actualizar el Producto")
    @ApiResponse(responseCode = "500", description = "Cuando ocurre una excepcion inesperada actualizando el Producto")
    @PostMapping("update")
    public ProductDTO update(@RequestBody ProductUpdateDTO productUpdateDTO){
        return productService.update(productUpdateDTO);
    }

    @PutMapping("enable/{id}")
    public String enableProduct(@PathVariable(name = "id") String id){
        return productService.enable(id);
    }

    @PutMapping("disable/{id}")
    public String disableProduct(@PathVariable(name = "id") String id){
        return productService.disable(id);
    }
}
