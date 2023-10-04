package br.com.studies.grpcspringcourse.service.impl;

import br.com.studies.grpcspringcourse.domain.entities.Product;
import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;
import br.com.studies.grpcspringcourse.exception.ProductAlreadyExistsException;
import br.com.studies.grpcspringcourse.exception.ProductNotFoundException;
import br.com.studies.grpcspringcourse.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("When creating a product, should return the created product")
    void whenCreateProduct_thenReturnCreatedProduct() {
        Product product = buildProduct();
        ProductInputDTO inputDTO = buildProductInputDTO();

        when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductOutputDTO outputDTO = productService.create(inputDTO);

        assertThat(outputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    @DisplayName("When create a new product which already exists, should throw ProductAlreadyExistsException")
    void whenCreateProductThatExists_thenThrowProductAlreadyExistsException() {
        Product product = buildProduct();
        ProductInputDTO inputDTO = buildProductInputDTO();

        when(productRepository.findByNameIgnoreCase(Mockito.any())).thenReturn(Optional.of(product));

        Assertions.assertThatExceptionOfType(ProductAlreadyExistsException.class)
                .isThrownBy(() -> productService.create(inputDTO));
    }

    @Test
    @DisplayName("when call findById, then return a product")
    void whenCallFindById_thenReturnProduct() {
        Long id = 1L;
        Product product = buildProduct();

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductOutputDTO outputDTO = productService.findById(id);

        assertThat(outputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    @DisplayName("When call findByd with invalid id, throw ProductNotFoundException.")
    void whenCallFindById_givenInvalidId_thenThrowProductNotFoundException() {
        Long id = 1L;
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productService.findById(id));
    }

    @Test
    @DisplayName("when call delete product with valid id, then delete it.")
    void whenCallDeleteProduct_thenDelete() {
        Long id = 1L;
        Product product = buildProduct();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThatNoException().isThrownBy(() -> productService.delete(id));
    }

    @Test
    @DisplayName("when call delete with invalid id, should throw ProductNotFoundException")
    void whenCallDeleteProduct_givenInvalidId_thenThrowProductNotFoundException() {
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() ->
                        productService.delete(id));
    }

    @Test
    @DisplayName("when call findAll, then return a list of products.")
    void whenCallFindAll_thenReturnAListOfProducts() {
        List<Product> products = buildProductsList();

        when(productRepository.findAll()).thenReturn(products);

        List<ProductOutputDTO> outputDTOList = productService.findAll();

        assertThat(outputDTOList)
                .extracting("id", "name", "price", "quantityInStock")
                .contains(
                        tuple(1L, "Test Product A", 10.0, 5),
                        tuple(2L, "Test Product B", 11.0, 50)
                );
    }

    private List<Product> buildProductsList() {
        return List.of(
                new Product(1L, "Test Product A", 10.0, 5),
                new Product(2L, "Test Product B", 11.0, 50)
        );

    }

    private static Product buildProduct() {
        Product product = new Product(1L, "Test Product", 10.0, 5);
        return product;
    }

    private static ProductInputDTO buildProductInputDTO() {
        ProductInputDTO inputDTO = new ProductInputDTO("Test Product", 10.0, 5);
        return inputDTO;
    }
}