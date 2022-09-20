package com.example.module4_minitesttuan1.service;

import com.example.module4_minitesttuan1.model.Product;
import com.example.module4_minitesttuan1.model.Province;
import com.example.module4_minitesttuan1.repository.IProductRepository;
import com.example.module4_minitesttuan1.service.ServiceInterface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByProvince(Province province) {
        return productRepository.findAllByProvince(province);
    }
}