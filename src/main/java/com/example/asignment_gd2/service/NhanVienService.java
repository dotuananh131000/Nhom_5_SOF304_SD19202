package com.example.asignment_gd2.service;

import com.example.asignment_gd2.model.NhanVien;
import com.example.asignment_gd2.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;

    public boolean AddOrUpdateNhanVien(NhanVien nhanVien){
        if(nhanVien.getMaNV().trim().equals("")||!nhanVien.getMaNV().matches("^[a-zA-Z0-9 ]+$")){
            return false;
        }
        if(nhanVien.getTen().trim().equals("")||!nhanVien.getTen().matches("^[a-zA-Z]+$")){
            return false;
        }
        for (NhanVien nhanVien1 : nhanVienRepository.findAll()){
            if(nhanVien.getMaNV().trim().equals(nhanVienRepository.findById(nhanVien1.getId()))){
                return false;
            }
        }

        if(nhanVien.getTenDangNhap().trim().equals("")||!nhanVien.getTenDangNhap().matches("^[a-zA-Z]+$")){
            return false;
        }
        if(nhanVien.getMatKhau().length()<=2 || nhanVien.getMatKhau().trim().equals("")){
            return false;
        }

        nhanVienRepository.save(nhanVien);
        return true;
    }

    public List<NhanVien> hienThiDanhSachNhanVien(){
        return nhanVienRepository.findAll();
    }
    public Optional<NhanVien> timNhanVienTheoMa(String ma){
        return nhanVienRepository.findByMa(ma);
    }

    public List<NhanVien>timNhanVienTheoTen(String ten){
        return nhanVienRepository.findByTenContaining(ten);
    }

    public List<NhanVien>timNhanVienTheoTrangThai(Boolean tt){
        return nhanVienRepository.findByTrangThai(tt);
    }

    public void xoaNhanVien(Integer id){
        nhanVienRepository.deleteById(id);
    }
}
