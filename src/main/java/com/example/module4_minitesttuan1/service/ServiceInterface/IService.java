package com.example.module4_minitesttuan1.service.ServiceInterface;

import java.util.List;

public interface IService<T>{
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
}
