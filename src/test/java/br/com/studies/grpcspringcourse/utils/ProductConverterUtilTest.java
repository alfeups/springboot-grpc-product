package br.com.studies.grpcspringcourse.utils;

import br.com.studies.grpcspringcourse.domain.entities.Product;
import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductConverterUtilTest {

    @Test
    void whenConverterAProductToOutputDto_thenReturnAOutputDTO() {
        var product = new Product(1L, "Test Product", 10.0, 5);
        var productOutputDto = ProductConverterUtil.toProductOutputDTO(product);
//        Assertions.assertThat(product)
//                .usingRecursiveComparison()
//                .isEqualTo(productOutputDto);

        // This approach provides more explicit and fine-grained field-level comparisons.
        assertThat(productOutputDto.getId()).isEqualTo(product.getId());
        assertThat(productOutputDto.getName()).isEqualTo(product.getName());
        assertThat(productOutputDto.getPrice()).isEqualTo(product.getPrice());
        assertThat(productOutputDto.getQuantityInStock()).isEqualTo(product.getQuantityInStock());
    }

    @Test
    void whenConverterAProductInputDtoToProduct_thenReturnAProduct() {
        var productInput = new ProductInputDTO("Test Product", 10.0, 5);
        var product = ProductConverterUtil.toProductEntity(productInput);

        assertThat(product.getName()).isEqualTo(productInput.getName());
        assertThat(product.getPrice()).isEqualTo(productInput.getPrice());
        assertThat(product.getQuantityInStock()).isEqualTo(productInput.getQuantityInStock());
    }


}