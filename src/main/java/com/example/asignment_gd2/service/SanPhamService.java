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
        // Kiểm tra thông tin sản phẩm không hợp lệ
        if (sanPham.getMa().isEmpty() || sanPham.getTen().isEmpty()) {
            throw new IllegalArgumentException("Thông tin sản phẩm không hợp lệ");
        }

        // Kiểm tra độ dài tên sản phẩm
        if (sanPham.getTen().length() > 100) {
            throw new IllegalArgumentException("Tên sản phẩm không được quá 100 ký tự");
        }

        // Kiểm tra tên sản phẩm không chứa ký tự đặc biệt
        if (sanPham.getTen().matches(".*[@$%&*()!+=<>?/\\[\\]{}^|:;,.].*")) {
            throw new IllegalArgumentException("Tên sản phẩm không được chứa ký tự đặc biệt @$%");
        }

        // Kiểm tra mã sản phẩm không chứa dấu cách
        if (sanPham.getMa().contains(" ")) {
            throw new IllegalArgumentException("Mã sản phẩm không được chứa dấu cách");
        }

        // Kiểm tra trùng lặp mã hoặc tên sản phẩm
        Optional<SanPham> existingProduct = sanPhams.stream()
                .filter(sp -> sp.getMa().equals(sanPham.getMa()) || sp.getTen().equals(sanPham.getTen()))
                .findFirst();

        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Không thể thêm sản phẩm có mã hoặc tên trùng lặp");
        }

        // Thêm sản phẩm vào danh sách
        sanPhams.add(sanPham);
        return sanPham;
    }






}
