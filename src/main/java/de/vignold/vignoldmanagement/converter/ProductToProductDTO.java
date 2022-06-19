package de.vignold.vignoldmanagement.converter;

import de.vignold.vignoldmanagement.dto.ProductDTO;
import de.vignold.vignoldmanagement.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductToProductDTO {

    public static List<ProductDTO> convertProductTOProductDTO(List<Product> productList) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDescription(product.getDescription());
            productDTO.setCreatedDate(product.getCreatedDate());
            productDTO.setUpdatedDate(product.getUpdatedDate());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
