package com.example.module4_minitesttuan1.service;

import com.example.module4_minitesttuan1.model.Product;
import com.example.module4_minitesttuan1.repository.RepoInterface.IProductRepository;
import com.example.module4_minitesttuan1.service.ServiceInterface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.remove(id);
    }
}