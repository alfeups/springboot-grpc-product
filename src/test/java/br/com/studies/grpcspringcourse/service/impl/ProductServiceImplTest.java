package br.com.studies.grpcspringcourse.service.impl;

import br.com.studies.grpcspringcourse.domain.entities.Product;
import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;
import br.com.studies.grpcspringcourse.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("When creating a product, should return the created product")
    void whenCreateProduct_thenReturnCreatedProduct() {
        Product product = new Product(1, "Test Product", 10.0, 5);
        ProductInputDTO inputDTO = new ProductInputDTO("Test Product", 10.0, 5);

        when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductOutputDTO outputDTO = productService.create(inputDTO);

        assertThat(outputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}