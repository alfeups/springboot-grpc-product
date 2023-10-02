package br.com.studies.grpcspringcourse.resources;

import br.com.studies.grpcspringcourse.ProductRequest;
import br.com.studies.grpcspringcourse.ProductResponse;
import br.com.studies.grpcspringcourse.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class ProductResourceTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    @Test
    @DisplayName("when valid data is provided a product is created")
    public void whenCallCreateProduct_thenReturnProduct() {

        ProductRequest request = ProductRequest.newBuilder()
                .setName("Test Product")
                .setPrice(10.99)
                .setQuantityInStock(100)
                .build();

        ProductResponse productResponse = serviceBlockingStub.create(request);

        Assertions.assertThat(request)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(productResponse);

    }

}