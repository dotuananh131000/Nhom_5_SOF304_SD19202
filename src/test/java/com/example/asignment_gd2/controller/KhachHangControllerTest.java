package com.example.asignment_gd2.controller;

import com.example.asignment_gd2.model.KhachHang;
import com.example.asignment_gd2.repository.KhachHangRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class KhachHangControllerTest {

    @Mock
    private KhachHangRepository khachHangRepository;

    private KhachHangController khachHangController;

    private List<KhachHang> khachHangList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        khachHangController = new KhachHangController(); // Constructor doesn't take repository anymore

        KhachHang khachHang1 = new KhachHang(1, "Nguyen", "nguyen123", "KH001", true);
        KhachHang khachHang2 = new KhachHang(2, "Tran", "tran456", "KH002", true);
        KhachHang khachHang3 = new KhachHang(3, "Le", "le789", "KH003", true);
        khachHangList = Arrays.asList(khachHang1, khachHang2, khachHang3);

        // Mocking the repository behavior
        when(khachHangRepository.findAll()).thenReturn(khachHangList);
    }

    @Test
    public void TC_KH_01() {
        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(khachHangList));
    }

    @Test
    public void TC_KH_02() {
        // Mocking an empty list
        when(khachHangRepository.findAll()).thenReturn(Arrays.asList());

        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void TC_KH_03() {
        KhachHang newKhachHang = new KhachHang(null, "Hoang", "hoang123", "KH004", true);

        // Simulate adding the customer
        when(khachHangRepository.save(newKhachHang)).thenReturn(newKhachHang);

        khachHangController.addKhachHang(newKhachHang, null, null);

        // Verify repository interaction
        verify(khachHangRepository, times(1)).save(newKhachHang);

        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @Test
    public void TC_KH_04() {
        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertTrue(result.stream().anyMatch(kh -> kh.getTen().equals("Nguyen")));
        assertTrue(result.stream().anyMatch(kh -> kh.getTen().equals("Tran")));
        assertTrue(result.stream().anyMatch(kh -> kh.getTen().equals("Le")));
    }

    @Test
    public void TC_KH_05() {
        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        long trueStatusCount = result.stream().filter(KhachHang::isTrangThai).count();
        assertEquals(3, trueStatusCount);
    }

    @Test
    public void TC_KH_06() {
        // Simulate adding a customer with false status
        KhachHang newKhachHang = new KhachHang(null, "Hoang", "hoang123", "KH004", false);
        when(khachHangRepository.save(newKhachHang)).thenReturn(newKhachHang);

        khachHangController.addKhachHang(newKhachHang, null, null);

        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        long trueStatusCount = result.stream().filter(KhachHang::isTrangThai).count();
        assertEquals(3, trueStatusCount); // Only 3 should have true status
    }

    @Test
    public void TC_KH_07() {
        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertTrue(result.stream().anyMatch(kh -> kh.getMaKH().equals("KH001")));
        assertTrue(result.stream().anyMatch(kh -> kh.getMaKH().equals("KH002")));
        assertTrue(result.stream().anyMatch(kh -> kh.getMaKH().equals("KH003")));
    }


    @Test
    public void TC_KH_10() {
        List<KhachHang> result = khachHangController.showKhachHang(0, null);

        assertTrue(result.stream().anyMatch(kh -> kh.isTrangThai()));
    }
}
