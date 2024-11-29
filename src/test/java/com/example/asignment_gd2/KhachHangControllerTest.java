package com.example.asignment_gd2;


import com.example.asignment_gd2.model.KhachHang;
import com.example.asignment_gd2.repository.KhachHangRepository;
import com.example.asignment_gd2.service.KhachHangService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KhachHangControllerTest {

//    @Autowired
//    private KhachHangService khachHangService;

    @Autowired
    private KhachHangRepository khachHangRepository;


    @AfterEach
    public void tearDown() {

    }


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testFindAll(){
        initData();

        Pageable pageable = PageRequest.of(0, 5);
        Page<KhachHang> list = khachHangRepository.findAll(pageable);
        assertEquals(5, list.getTotalElements());

        clearData();
    }

    @Test
    public void testEmptyData(){
        khachHangRepository.deleteAll();
        Pageable pageable = PageRequest.of(0, 5);
        Page<KhachHang> list = khachHangRepository.findAll(pageable);
        assertEquals(0, list.getTotalElements());
    }

    @Test
    public void testAddNewKhachHang(){
        initData();
        KhachHang newKhachHang = new KhachHang(6, "Mai", "0901234568", "KH006", true);
        khachHangRepository.save(newKhachHang);
        Pageable pageable = PageRequest.of(0, 5);
        Page<KhachHang> list = khachHangRepository.findAll(pageable);
        assertEquals(6, list.getTotalElements());
        clearData();
    }
    @Test
    public void testFindKhachHangByName() {
        initData();
        Pageable pageable = PageRequest.of(0, 5);
        Page<KhachHang> list = khachHangRepository.findAll(pageable);
        assertTrue(list.getContent().stream().anyMatch(kh -> kh.getTen().equals("Nguyen")));
        clearData();
    }

    @Test
    public void testFindByTrangThai() {
        initData();
        List<KhachHang> khachHangsTrue = khachHangRepository.findByTrangThai(true);
        assertNotNull(khachHangsTrue);
        assertEquals(5, khachHangsTrue.size());
        List<KhachHang> khachHangsFalse = khachHangRepository.findByTrangThai(false);
        assertNotNull(khachHangsFalse);
        assertEquals(0, khachHangsFalse.size());
        clearData();
    }

    @Test
    public void testFindByMa() {
        initData();
        List<KhachHang> khachHang = khachHangRepository.findByMaKH("KH001");
        assertNotNull(khachHang);
        assertEquals("KH001", khachHang.get(0).getMaKH());
        clearData();
    }

    @Test
    public void testAddKhachHangWithSpecialCharactersInName() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "nguyenthib", "KH002@%#", true);
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testAddKhachHangWithEmptyMaKH() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "nguyenthib", "", true);
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testAddKhachHangWithEmptyTen() {
        initData();
        KhachHang khachHang = new KhachHang(0, "", "nguyenthib", "KH003", true);
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testAddKhachHangWithEmptySdt() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "", "KH003", true);
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testAddKhachHangWithNullTrangThai() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "nguyenthib", "KH003", false);  // trangThai is set to false
        khachHang.setTrangThai(null);  // set trangThai as null
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testAddKhachHangWithNegativeId() {
        initData();
        KhachHang khachHang = new KhachHang(-1, "Nguyen Van A", "nguyenvana", "KH001", true);
        assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class, () -> {
            khachHangRepository.saveWithValidation(khachHang); // Use the custom save method
        });
        clearData();
    }



    @Test
    public void testAddKhachHangWithZeroId() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "nguyenthib", "KH003", true);  // ID is set to 0
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            khachHangRepository.saveWithValidation(khachHang);
        });
        assertEquals("ID cannot be zero", exception.getMessage());
        clearData();
    }



    @Test
    public void testAddKhachHangWithNullId() {
        initData();
        KhachHang khachHang = new KhachHang(null, "Nguyen Thi B", "nguyenthib", "KH003", true);  // ID is set to null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            khachHangRepository.saveWithValidation(khachHang);
        });
        assertEquals("ID cannot be null", exception.getMessage());
        clearData();
    }



    @Test
    public void testAddKhachHangSuccess() {
        initData();
        KhachHang khachHang = new KhachHang(6, "Nguyen Thi X", "vanx", "KH9999", true);
        khachHangRepository.save(khachHang);
        Pageable pageable = PageRequest.of(0, 5);
        Page<KhachHang> list = khachHangRepository.findAll(pageable);
        assertEquals(6, list.getTotalElements());
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithSpecialCharactersInMaKH() {
        initData();
        KhachHang khachHang = new KhachHang(1, "Nguyen Thi B", "nguyenthib", "KH001@%#", true);  // MaKH contains special characters
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithNonExistentId() {
        initData();
        KhachHang khachHang = new KhachHang(999, "Nguyen Thi B", "nguyenthib", "KH003", true);  // ID does not exist
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithEmptyTen() {
        initData();
        KhachHang khachHang = new KhachHang(1, "", "nguyenthib", "KH001", true);  // Ten is empty
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithEmptyEmail() {
        initData();
        KhachHang khachHang = new KhachHang(1, "Nguyen Thi B", "", "KH001", true);  // Email is empty
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithEmptySdt() {
        initData();
        KhachHang khachHang = new KhachHang(1, "Nguyen Thi B", "", "KH001", true);  // Sdt is empty
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithNullTrangThai() {
        initData();
        KhachHang khachHang = new KhachHang(1, "Nguyen Thi B", "nguyenthib", "KH001", null);  // TrangThai is null
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithNegativeId() {
        initData();
        KhachHang khachHang = new KhachHang(-1, "Nguyen Thi B", "nguyenthib", "KH001", true);  // ID is negative
        assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithZeroId() {
        initData();
        KhachHang khachHang = new KhachHang(0, "Nguyen Thi B", "nguyenthib", "KH003", true);  // ID is zero
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            khachHangRepository.save(khachHang);
        });
        assertEquals("ID cannot be zero", exception.getMessage());
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithNullId() {
        initData();
        KhachHang khachHang = new KhachHang(null, "Nguyen Thi B", "nguyenthib", "KH003", true);  // ID is null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            khachHangRepository.save(khachHang);
        });
        assertEquals("ID cannot be null", exception.getMessage());
        clearData();
    }

    @Test
    public void testUpdateKhachHangWithInvalidPhoneNumber() {
        initData();
        KhachHang khachHang = new KhachHang(1, "Nguyen Thi B", "invalidphone", "KH001", true);  // Invalid phone number format
        assertThrows(Exception.class, () -> {
            khachHangRepository.save(khachHang);
        });
        clearData();
    }

    @Test
    public void testSearchWithValidId() {
        initData();
        KhachHang khachHang = khachHangRepository.findById(1).orElse(null);
        assertNotNull(khachHang);
        assertEquals(1, khachHang.getId());
        clearData();
    }

    @Test
    public void testSearchWithNonExistentId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            KhachHang khachHang = khachHangRepository.findById(999).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithNullId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            KhachHang khachHang = khachHangRepository.findById(null).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithNegativeId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            KhachHang khachHang = khachHangRepository.findById(-1).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithZeroId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            KhachHang khachHang = khachHangRepository.findById(0).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithMaxValueId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            KhachHang khachHang = khachHangRepository.findById(Integer.MAX_VALUE).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithCharacterId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            Object invalidId = "test";
            khachHangRepository.findById((Integer) invalidId).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithSpecialCharacterId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            Object invalidId = "*^^&";
            khachHangRepository.findById((Integer) invalidId).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithFloatingPointId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            Object invalidId = 4.5;
            khachHangRepository.findById((Integer) invalidId).orElseThrow(ClassCastException::new);
        });
        clearData();
    }

    @Test
    public void testSearchWithAlphanumericId() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            Object invalidId = "123abc";
            khachHangRepository.findById((Integer) invalidId).orElseThrow(ClassCastException::new);
        });
        clearData();
    }


    @Test
    public void testSearchWithEmptyMa() {
        initData();
        List<KhachHang> resultList = khachHangRepository.findByMaKH("");
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
        clearData();
    }

    @Test
    public void testSearchWithNonExistentMa() {
        initData();
        List<KhachHang> resultList = khachHangRepository.findByMaKH("aaa");
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
        clearData();
    }

    @Test
    public void testSearchWithMaHavingSingleRecord() {
        initData();
        List<KhachHang> resultList = khachHangRepository.findByMaKH("Hoang");
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals("Hoang", resultList.get(0).getMaKH());
        clearData();
    }

    @Test
    public void testSearchWithMaHavingMultipleRecords() {
        initData();
        // Assuming multiple records exist with "ma" = "Hoang"
        List<KhachHang> resultList = khachHangRepository.findByMaKH("Hoang");
        assertNotNull(resultList);
        assertTrue(resultList.size() > 1);
        assertTrue(resultList.stream().allMatch(kh -> kh.getMaKH().equals("Hoang")));
        clearData();
    }

    @Test
    public void testSearchNameAccuracyWithMa() {
        initData();
        List<KhachHang> resultList = khachHangRepository.findByMaKH("Nguyen");
        assertNotNull(resultList);
        assertTrue(resultList.stream().allMatch(kh -> kh.getMaKH().equals("Nguyen")));
        clearData();
    }

    @Test
    public void testSearchMaAccuracy() {
        initData();
        List<KhachHang> resultList = khachHangRepository.findByMaKH("Hoang");
        assertNotNull(resultList);
        assertTrue(resultList.stream().allMatch(kh -> kh.getMaKH().equals("Hoang")));
        clearData();
    }



    @Test
    public void testSearchWithSpecialCharacterMa() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            khachHangRepository.findByMaKH("@7*####");
        });
        clearData();
    }

    @Test
    public void testSearchWithExcessivelyLongMa() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            khachHangRepository.findByMaKH("dddgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        });
        clearData();
    }

    @Test
    public void testSearchWithShortMa() {
        initData();
        assertThrows(ClassCastException.class, () -> {
            khachHangRepository.findByMaKH("ddd");
        });
        clearData();
    }




















    private void initData(){
        khachHangRepository.save(new KhachHang(1, "Nguyen", "0901234567", "KH001", true));
        khachHangRepository.save(new KhachHang(2, "Tran", "0901234568", "KH002", true));
        khachHangRepository.save(new KhachHang(3, "Le", "0901234569", "KH003", true));
        khachHangRepository.save(new KhachHang(4, "Hung", "0901234570", "KH004", true));
        khachHangRepository.save(new KhachHang(5, "Khang", "0901234571", "KH005", true));

    }
    private void clearData(){

        khachHangRepository.deleteAll();


    }



}
