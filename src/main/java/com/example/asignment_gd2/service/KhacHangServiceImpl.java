package com.example.asignment_gd2.service;

import com.example.asignment_gd2.model.KhachHang;
import com.example.asignment_gd2.repository.KhachHangRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhacHangServiceImpl implements KhachHangService {


    private KhachHangRepository khachHangRepository;

    public KhacHangServiceImpl(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    public Page<KhachHang> findAll(Pageable pageable) {
       return khachHangRepository.findAll(pageable);
    }


    @Override
    public KhachHang save(KhachHang kh) {
        return khachHangRepository.save(kh);
    }

    @Override
    public KhachHang update(KhachHang kh) {
        return khachHangRepository.save(kh);
    }

    @Override
    public void deleteByID(Integer id) {
         khachHangRepository.deleteById(id);
    }

    @Override
    public List<KhachHang> findByMa(String maKH) {
        return khachHangRepository.getKhachHangByMa(maKH);
    }

    @Override
    public KhachHang findByID(Integer id) {
        return khachHangRepository.findById(id).get();
    }
}
