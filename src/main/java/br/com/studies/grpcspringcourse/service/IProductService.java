package br.com.studies.grpcspringcourse.service;

import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;

import java.util.List;

public interface IProductService {
    ProductOutputDTO create(ProductInputDTO inputDTO);
    ProductOutputDTO findById(Long id);
    void delete(Long id);
    List<ProductOutputDTO> findAll();
}
