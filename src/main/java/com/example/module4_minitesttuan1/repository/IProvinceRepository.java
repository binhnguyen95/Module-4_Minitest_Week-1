package com.example.module4_minitesttuan1.repository;

import com.example.module4_minitesttuan1.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository  extends PagingAndSortingRepository<Province, Long> {
}
