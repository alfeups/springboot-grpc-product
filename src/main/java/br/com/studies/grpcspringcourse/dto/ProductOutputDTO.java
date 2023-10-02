package br.com.studies.grpcspringcourse.dto;

public class ProductOutputDTO {
    private final Integer id;
    private final String name;
    private final Double price;
    private final Integer quantityInStock;

    public ProductOutputDTO(Integer id, String name, Double price, Integer quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

}
