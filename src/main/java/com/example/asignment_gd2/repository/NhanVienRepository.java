package com.example.asignment_gd2.repository;

import com.example.asignment_gd2.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM NhanVien WHERE maNV LIKE %:maNV%")

    ArrayList<NhanVien> getNhanVienByMaNV(String maNV);

    // Thêm phương thức phân trang

    Page<NhanVien> findAll(Pageable pageable);

    public Optional<NhanVien> findByMa(String ma);

    public List<NhanVien>findByTenContaining(String ten);

    public List<NhanVien>findByTrangThai(Boolean ten);
}
