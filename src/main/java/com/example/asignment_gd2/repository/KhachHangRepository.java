package com.example.asignment_gd2.repository;

import com.example.asignment_gd2.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM KhachHang WHERE maKH LIKE %:maKH%"
    )

    ArrayList<KhachHang> getKhachHangByMa(String maKH);

    Page<KhachHang> findAll(Pageable pageable);

    List<KhachHang> findByTrangThai(boolean trangThai);
    List<KhachHang> findByMaKH(String ma);

    default KhachHang saveWithValidation(KhachHang khachHang) {
        if (khachHang.getId() < 0) {
            throw new InvalidDataAccessApiUsageException("ID cannot be negative");
        }
        return save(khachHang);
    }

    default ArrayList<KhachHang> getValidatedKhachHangByMa(String maKH) {
        if (maKH == null || maKH.length() < 5 || maKH.length() > 50 || !maKH.matches("[a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("Invalid 'maKH' value. Must be alphanumeric and 5-50 characters long.");
        }
        return getKhachHangByMa(maKH);
    }
    default List<KhachHang> findByMaKHWithNullHandling(String ma) {
        if (ma == null) {
            throw new IllegalArgumentException("ma cannot be null");
        }
        return findByMaKH(ma);
    }



}
