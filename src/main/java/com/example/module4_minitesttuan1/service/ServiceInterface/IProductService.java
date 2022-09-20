package com.example.module4_minitesttuan1.service.ServiceInterface;


import com.example.module4_minitesttuan1.model.Product;
import com.example.module4_minitesttuan1.model.Province;

public interface IProductService extends IService<Product> {
    Iterable<Product> findAllByProvince(Province province);
}
