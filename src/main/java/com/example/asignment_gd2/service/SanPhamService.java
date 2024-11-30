package com.example.asignment_gd2.service;

import com.example.asignment_gd2.model.SanPham;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SanPhamService {
    private List<SanPham> sanPhams = new ArrayList<>();

    public List<SanPham> getAllSanPham() {
        if (sanPhams.isEmpty()) {
            throw new IllegalStateException("Danh sách sản phẩm trống");
        }
        return new ArrayList<>(sanPhams);
    }

    public void deleteSanPham(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ");
        }
        Optional<SanPham> productToRemove = sanPhams.stream()
                .filter(sp -> sp.getId() == id)
                .findFirst();

        if (productToRemove.isEmpty()) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }
        sanPhams.remove(productToRemove.get());
    }

    public SanPham getSanPhamById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ");
        }
        return sanPhams.stream()
                .filter(sp -> sp.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
    }

    public SanPham createSanPham(SanPham sanPham) {
        if (sanPham.getMa().isEmpty() || sanPham.getTen().isEmpty()) {
            throw new IllegalArgumentException("Thông tin sản phẩm không hợp lệ");
        }
        Optional<SanPham> existingProduct = sanPhams.stream()
                .filter(sp -> sp.getMa().equals(sanPham.getMa()) || sp.getTen().equals(sanPham.getTen()))
                .findFirst();

        sanPhams.add(sanPham);
        return sanPham;
    }


}
