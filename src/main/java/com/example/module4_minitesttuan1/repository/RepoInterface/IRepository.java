package com.example.module4_minitesttuan1.repository.RepoInterface;

import java.util.List;

public interface IRepository<T>{
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
}
