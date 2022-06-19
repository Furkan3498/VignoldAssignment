package de.vignold.vignoldmanagement.controller;

import de.vignold.vignoldmanagement.converter.ProductToProductDTO;
import de.vignold.vignoldmanagement.dao.ProductRepository;
import de.vignold.vignoldmanagement.dto.ProductDTO;
import de.vignold.vignoldmanagement.entity.Product;
import de.vignold.vignoldmanagement.entity.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            return ResponseEntity.ok(ProductToProductDTO.convertProductTOProductDTO(productList));
        }
        return null;
    }

    @PostMapping("/create")
    public void createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setState(State.RUNNING);
        product.setDescription(productDTO.getDescription());
        product.setCreatedDate(ZonedDateTime.now());
        product.setUpdatedDate(ZonedDateTime.now());
        productRepository.save(product);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(Long id) {
        Product product = productRepository.getOne(id);
        product.setDeleted(Boolean.TRUE);
        product.setState(State.FINISHED);
        productRepository.save(product);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestParam Long productId, ProductDTO productDTO) {
        Product product = productRepository.getOne(productId);
        product.setDescription(productDTO.getDescription());
        product.setUpdatedDate(ZonedDateTime.now());
        productRepository.save(product);
    }
}
