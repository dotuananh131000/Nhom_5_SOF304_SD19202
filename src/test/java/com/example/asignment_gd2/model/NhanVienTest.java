package com.example.asignment_gd2.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NhanVienTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private NhanVienService nhanVienService;

    @MockBean
    private NhanVienRepository nhanVienRepository;

    @BeforeEach
    void setUp() {
//        nhanVienService = new NhanVienService();
    }
    //
    @AfterEach
    void tearDown() {
//        nhanVienService = null;
    }
    // Add employee
    @Test
    public void testAddNhanVien_success()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "NVO01", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertTrue(nhanVienService.AddNhanVien(nhanVien),"Nhân viên thêm thành công");
        verify(nhanVienRepository, times(1)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_maTrong()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddNhanVien(nhanVien),"Nhân viên thêm thất bại");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_maKiTu()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "@###", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddNhanVien(nhanVien),"Nhân viên ko chứa kí tự đặc biệt");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }
}