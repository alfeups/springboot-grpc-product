package br.com.studies.grpcspringcourse.service.impl;

import br.com.studies.grpcspringcourse.dto.ProductInputDTO;
import br.com.studies.grpcspringcourse.dto.ProductOutputDTO;
import br.com.studies.grpcspringcourse.exception.ProductAlreadyExistsException;
import br.com.studies.grpcspringcourse.exception.ProductNotFoundException;
import br.com.studies.grpcspringcourse.repository.ProductRepository;
import br.com.studies.grpcspringcourse.service.IProductService;
import br.com.studies.grpcspringcourse.utils.ProductConverterUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductOutputDTO create(ProductInputDTO inputDTO) {
        isProductRegistered(inputDTO.getName());

        return ProductConverterUtil.toProductOutputDTO(
                this.productRepository.save(
                        ProductConverterUtil.toProductEntity(inputDTO)));
    }

    @Override
    public ProductOutputDTO findById(Long id) {
        return ProductConverterUtil.toProductOutputDTO(
                this.productRepository.findById(id)
                        .orElseThrow( ()-> new ProductNotFoundException(id)));
    }

    @Override
    public void delete(Long id) {
        var product = this.productRepository.findById(id)
                .orElseThrow( ()->
                        new ProductNotFoundException(id));
        this.productRepository.delete(product);
    }

    @Override
    public List<ProductOutputDTO> findAll() {
        return this.productRepository.findAll()
                .stream()
                .map(ProductConverterUtil::toProductOutputDTO)
                .collect(Collectors.toList());
    }

    private void isProductRegistered(String name){
        this.productRepository.findByNameIgnoreCase(name)
                .ifPresent(exception -> {
                    throw new ProductAlreadyExistsException(name);
                });
    }
}
