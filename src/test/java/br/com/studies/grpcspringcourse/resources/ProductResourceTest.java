package br.com.studies.grpcspringcourse.resources;

import br.com.studies.grpcspringcourse.*;
import br.com.studies.grpcspringcourse.domain.entities.Product;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class ProductResourceTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    @Mock
    private Flyway flyway;

    @BeforeEach
    public void setUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    @DisplayName("when valid data is provided a product is created")
    public void whenCallCreateProduct_thenReturnProduct() {

        ProductRequest request = ProductRequest.newBuilder()
                .setName("Test Product")
                .setPrice(10.99)
                .setQuantityInStock(100)
                .build();

        ProductResponse productResponse = serviceBlockingStub.create(request);

        assertThat(request)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(productResponse);

    }

    @Test
    @DisplayName("When try to create a new product which already exists, should throw ProductAlreadyExistsException")
    public void whenCreateProductThatExists_thenThrowProductAlreadyExistsException() {

        ProductRequest request = ProductRequest.newBuilder()
                .setName("Product A")
                .setPrice(10.99)
                .setQuantityInStock(100)
                .build();

        assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() ->
                        serviceBlockingStub.create(request))
                .withMessage("ALREADY_EXISTS: Product Product A is already registered in the system.");

    }

    @Test
    @DisplayName("when call findById return a product.")
    public void whenCallFindByIdProduct_thenReturnAValidProduct() {
        var request = RequestById.newBuilder().setId(1L).build();
        ProductResponse productResponse = serviceBlockingStub.findById(request);

        assertThat(productResponse.getId()).isEqualTo(request.getId());
    }

    @Test
    @DisplayName("when call findById with invalid id, should throw ProductNotFoundException")
    public void whenCallFindByIdProduct_givenInvalidId_thenThrowProductNotFoundException() {
        var request = RequestById.newBuilder().setId(100L).build();

        assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.findById(request))
                .withMessage("NOT_FOUND: Product with id 100 not found.");
    }

    @Test
    @DisplayName("when call delete with valid id, should not throw exception")
    public void whenCallDeleteProduct_thenShouldNotThrowException() {
        var request = RequestById.newBuilder().setId(1L).build();

        assertThatNoException()
                .isThrownBy(() -> serviceBlockingStub.delete(request));
    }

    @Test
    @DisplayName("when call findById with invalid id, should throw ProductNotFoundException")
    public void whenCallDeleteProduct_thenThrowProductNotFoundException() {
        var request = RequestById.newBuilder().setId(100L).build();

        assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.delete(request))
                .withMessage("NOT_FOUND: Product with id 100 not found.");
    }

    @Test
    @DisplayName("when call findAll return a list of products.")
    public void whenCallfindAll_thenReturnAListOfProducts() {
        EmptyRequest request = EmptyRequest.newBuilder().build();

        ProductResponseList productResponseList = serviceBlockingStub.findAll(request);

        assertThat(productResponseList).isInstanceOf(ProductResponseList.class);
        assertThat(productResponseList.getProductsCount()).isEqualTo(2);
        assertThat(productResponseList.getProductsList())
                .extracting("id", "name", "price", "quantityInStock")
                .contains(
                        tuple(1L, "Product A", 10.99, 10),
                        tuple(2L, "Product B", 10.99, 10)
                );
    }

}