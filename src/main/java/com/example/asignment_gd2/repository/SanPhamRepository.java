package com.example.asignment_gd2.repository;

import com.example.asignment_gd2.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM SanPham WHERE ma LIKE %:ma%"
    )

    ArrayList<SanPham> getSanPhamByMa(String ma);

    // Thêm phương thức phân trang
    Page<SanPham> findAll(Pageable pageable);
}
