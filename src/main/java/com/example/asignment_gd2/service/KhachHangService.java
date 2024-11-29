package com.example.asignment_gd2.service;

import com.example.asignment_gd2.model.KhachHang;
import com.example.asignment_gd2.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface KhachHangService {



    Page<KhachHang> findAll(Pageable pageable);
    KhachHang save(KhachHang kh);
    KhachHang update(KhachHang kh);
    void deleteByID(Integer id);
    List<KhachHang> findByMa(String maKH);
    KhachHang findByID(Integer id);








}
