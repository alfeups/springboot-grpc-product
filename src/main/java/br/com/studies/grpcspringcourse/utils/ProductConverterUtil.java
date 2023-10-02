package br.com.studies.grpcspringcourse.utils;

import br.com.studies.grpcspringcourse.domain.entities.Product;
import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;

public class ProductConverterUtil {

    public static ProductOutputDTO toProductOutputDTO(Product product) {
        return new ProductOutputDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantityInStock()
        );
    }

    public static Product toProductEntity(ProductInputDTO inputDTO) {
        return new Product(
                null,
                inputDTO.getName(),
                inputDTO.getPrice(),
                inputDTO.getQuantityInStock()
        );
    }
}
