package br.com.studies.grpcspringcourse.service.impl;

import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;
import br.com.studies.grpcspringcourse.exception.AlreadyExistsException;
import br.com.studies.grpcspringcourse.repository.ProductRepository;
import br.com.studies.grpcspringcourse.service.IProductService;
import br.com.studies.grpcspringcourse.utils.ProductConverterUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductOutputDTO create(ProductInputDTO inputDTO) {
        isProductRegistered(inputDTO.getName());
        var product = ProductConverterUtil.toProductEntity(inputDTO);
        var productCreated = this.productRepository.save(product);
        return ProductConverterUtil.toProductOutputDTO(productCreated);
    }

    @Override
    public ProductOutputDTO findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProductOutputDTO> findAll() {
        return null;
    }

    private void isProductRegistered(String name){
        this.productRepository.findByNameIgnoreCase(name)
                .ifPresent(exception -> {
                    throw new AlreadyExistsException(name);
                });
    }
}
