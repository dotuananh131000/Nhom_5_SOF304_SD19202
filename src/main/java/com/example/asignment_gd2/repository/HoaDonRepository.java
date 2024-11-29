package com.example.asignment_gd2.repository;

import com.example.asignment_gd2.model.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    // Thêm phương thức phân trang
    Page<HoaDon> findAll(Pageable pageable);
}
