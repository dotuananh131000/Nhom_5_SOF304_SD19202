package com.example.asignment_gd2.model;

import com.example.asignment_gd2.repository.NhanVienRepository;
import com.example.asignment_gd2.service.NhanVienService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class NhanVienTest {

    @Autowired
    private NhanVienService nhanVienService;

    @MockBean
    private NhanVienRepository nhanVienRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }



    // Add employee
    @Test
    public void testAddNhanVien_success()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "NVO01", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertTrue(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Nhân viên thêm thành công");
        verify(nhanVienRepository, times(1)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_maTrong()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Nhân viên thêm thất bại");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_maKiTu()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Đức Anh", "@###", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Nhân viên ko chứa kí tự đặc biệt");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_tenTrong()  {
        NhanVien nhanVien = new NhanVien(null, "", "NV001", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Tên không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_tenChuaKiTuDB()  {
        NhanVien nhanVien = new NhanVien(null, "123@@", "NV001", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Tên không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_trungMa()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Duc Anh", "NV01", "anh", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Tên không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_tenDangNhapTrong()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Duc Anh", "NV01", "", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Tên đăng nhập không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_tenDangNhapChuaKTDB()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Duc Anh", "NV01", "@##", "123", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Tên đăng nhập không hợp lệ");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_matKhauTrong()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Duc Anh", "NV01", "aaa", "", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Mật khẩu không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testAddNhanVien_matKhauThieu()  {
        NhanVien nhanVien = new NhanVien(null, "Cao Duc Anh", "NV01", "aaa", "12", true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien),"Mật khẩu không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    //Update nhân viên

    @Test
    public void testUpdateNhanVien_success() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        when(nhanVienRepository.save(nhanVien)).thenReturn(nhanVien);
        assertTrue(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Cập nhật nhân viên thành công");
        verify(nhanVienRepository, times(1)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_idNotFound() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(false);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Không tìm thấy nhân viên với ID");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_maTrong() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Mã nhân viên không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_maKiTuDB() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "@@@", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Mã nhân viên không hợp lệ");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_tenTrong() {
        NhanVien nhanVien = new NhanVien(1, "", "NV01", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Tên nhân viên không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_tenKiTuDB() {
        NhanVien nhanVien = new NhanVien(1, "123@@", "NV01", "anh", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Tên nhân viên không hợp lệ");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_tenDangNhapTrong() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Tên đăng nhập không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_tenDangNhapKTDB() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "@##", "123", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Tên đăng nhập không hợp lệ");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_matKhauTrong() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Mật khẩu không được bỏ trống");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    @Test
    public void testUpdateNhanVien_matKhauThieuKyTu() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "12", true);
        when(nhanVienRepository.existsById(nhanVien.getId())).thenReturn(true);
        assertFalse(nhanVienService.AddOrUpdateNhanVien(nhanVien), "Mật khẩu phải đủ ký tự");
        verify(nhanVienRepository, times(0)).save(nhanVien);
    }

    //Hiển thị nhân viên

    @Test
    public void testHienThiNhanVien_danhSachRong() {
        when(nhanVienRepository.findAll()).thenReturn(Collections.emptyList());
        List<NhanVien> result = nhanVienService.hienThiDanhSachNhanVien();
        assertTrue(result.isEmpty(), "Danh sách nhân viên trống");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoMa() {
        NhanVien nhanVien = new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true);
        when(nhanVienRepository.findByMa("NV01")).thenReturn(Optional.of(nhanVien));
        Optional<NhanVien> result = nhanVienService.timNhanVienTheoMa("NV01");
        assertNotNull(result, "Tìm thấy nhân viên với mã");
    }

    @Test
    public void testHienThiNhanVien_danhSachCoDuLieu() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true),
                new NhanVien(2, "Nguyễn Văn B", "NV02", "vanb", "456", false)
        );
        when(nhanVienRepository.findAll()).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.hienThiDanhSachNhanVien();
        assertEquals(2, result.size(), "Danh sách nhân viên có dữ liệu");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoMaKhongTonTai() {
        when(nhanVienRepository.findByMa("NV99")).thenReturn(Optional.empty());
        Optional<NhanVien> result = nhanVienService.timNhanVienTheoMa("NV99");
        assertTrue(result.isEmpty(), "Không tìm thấy nhân viên với mã");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTen() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true)
        );
        when(nhanVienRepository.findByTenContaining("Đức Anh")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Đức Anh");
        assertEquals(1, result.size(), "Tìm thấy nhân viên theo tên");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTrangThaiHoatDong() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true)
        );
        when(nhanVienRepository.findByTrangThai(true)).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTrangThai(true);
        assertEquals(1, result.size(), "Tìm thấy nhân viên đang hoạt động");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTrangThaiKhongHoatDong() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(2, "Nguyễn Văn B", "NV02", "vanb", "456", false)
        );
        when(nhanVienRepository.findByTrangThai(false)).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTrangThai(false);
        assertEquals(1, result.size(), "Tìm thấy nhân viên không hoạt động");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoMaNull() {
        when(nhanVienRepository.findByMa(null)).thenReturn(Optional.empty());
        Optional<NhanVien> result = nhanVienService.timNhanVienTheoMa(null);
        assertTrue(result.isEmpty(), "Không tìm thấy nhân viên với mã null");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenRong() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Cao Đức Anh", "NV01", "anh", "123", true),
                new NhanVien(2, "Nguyễn Văn B", "NV02", "vanb", "456", false)
        );
        when(nhanVienRepository.findByTenContaining("")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("");
        assertEquals(2, result.size(), "Danh sách nhân viên trả về đúng với tất cả các kết quả khi tìm kiếm tên rỗng");
    }

    @Test
    public void testHienThiNhanVien_khongTimThayNhanVienTheoTen() {
        when(nhanVienRepository.findByTenContaining("Hoang")).thenReturn(Collections.emptyList());
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Hoang");
        assertTrue(result.isEmpty(), "Không tìm thấy nhân viên theo tên" );
    }

    //Tìm kiếm nhân viên bonus
    @Test
    public void testHienThiNhanVien_timKiemTheoTenVoiKyTuSo() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Nguyễn 123", "NV06", "nguyen123", "303", true)
        );
        when(nhanVienRepository.findByTenContaining("Nguyễn 123")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Nguyễn 123");
        assertEquals(1, result.size(), "Tìm thấy nhân viên theo tên chứa ký tự số");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoMaVoiKyTuDaDau() {
        NhanVien nhanVien = new NhanVien(5, "Phan Thanh H", "NV05", "thanhh", "505", true);
        when(nhanVienRepository.findByMa("NV05")).thenReturn(Optional.of(nhanVien));
        Optional<NhanVien> result = nhanVienService.timNhanVienTheoMa("NV05");
        assertNotNull(result.get(), "Tìm thấy nhân viên với mã NV05");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenCoDauCach() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Trần Văn T", "NV07", "vanT", "404", false),
                new NhanVien(2, "Trần Văn U", "NV08", "vanU", "505", true)
        );
        when(nhanVienRepository.findByTenContaining("Trần Văn")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Trần Văn");
        assertEquals(2, result.size(), "Tìm thấy các nhân viên với tên có dấu cách");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenHoacMaVoiKyTuRong() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Nguyễn Thị H", "NV09", "nguyenh", "606", true)
        );
        when(nhanVienRepository.findByTenContaining("")).thenReturn(danhSach);
        when(nhanVienRepository.findByMa("")).thenReturn(Optional.empty());
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("");
        assertEquals(1, result.size(), "Tìm thấy nhân viên khi tìm kiếm tên với chuỗi rỗng");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenRongCoKyTuDacBiet() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Đặng Hoa*123", "NV10", "hoa123", "707", true)
        );
        when(nhanVienRepository.findByTenContaining("Đặng Hoa*123")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Đặng Hoa*123");
        assertEquals(1, result.size(), "Tìm thấy nhân viên theo tên chứa ký tự đặc biệt");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoMaHopLe() {
        NhanVien nhanVien = new NhanVien(6, "Lý Quốc H", "NV11", "quoch", "808", true);
        when(nhanVienRepository.findByMa("NV11")).thenReturn(Optional.of(nhanVien));
        Optional<NhanVien> result = nhanVienService.timNhanVienTheoMa("NV11");
        assertNotNull(result.get(), "Tìm thấy nhân viên theo mã hợp lệ");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenVoiKyTuNhapTay() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(1, "Ngô Minh H", "NV12", "minhh", "909", false)
        );
        when(nhanVienRepository.findByTenContaining("Ngô Minh H")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Ngô Minh H");
        assertEquals(1, result.size(), "Tìm thấy nhân viên theo tên với ký tự nhập tay");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTrangThaiCoNhanVien() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(7, "Vũ Tiến D", "NV13", "tiend", "1010", true)
        );
        when(nhanVienRepository.findByTrangThai(true)).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTrangThai(true);
        assertEquals(1, result.size(), "Tìm thấy nhân viên với trạng thái hoạt động");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenVoiKyTuDacBietTrongTen() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(8, "Bùi Minh @A", "NV14", "minha", "1111", true)
        );
        when(nhanVienRepository.findByTenContaining("Bùi Minh @A")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Bùi Minh @A");
        assertEquals(1, result.size(), "Tìm thấy nhân viên theo tên với ký tự đặc biệt trong tên");
    }

    @Test
    public void testHienThiNhanVien_timKiemTheoTenVoiTuongHopLe() {
        List<NhanVien> danhSach = Arrays.asList(
                new NhanVien(9, "Nguyễn Văn T", "NV15", "vant", "1212", false)
        );
        when(nhanVienRepository.findByTenContaining("Nguyễn Văn T")).thenReturn(danhSach);
        List<NhanVien> result = nhanVienService.timNhanVienTheoTen("Nguyễn Văn T");
        assertEquals(1, result.size(), "Tìm thấy nhân viên với tên hợp lệ");
    }
    // chức năng delete
    @Test
    public void testXoaNhanVien_xoaNhanVienThanhCong() {

        NhanVien nhanVien = new NhanVien(1, "Nguyen Van A", "NV01", "vanA", "123", true);
        when(nhanVienRepository.findById(1)).thenReturn(Optional.of(nhanVien));
        doNothing().when(nhanVienRepository).deleteById(1);

        nhanVienService.xoaNhanVien(1);


        verify(nhanVienRepository, times(1)).deleteById(1);
    }

    @Test
    public void testXoaNhanVien_xoaNhanVienKhongTonTai() {
        // Kiểm tra trường hợp xóa nhân viên không tồn tại
        when(nhanVienRepository.findById(2)).thenReturn(Optional.empty());

        // Kiểm tra xem có ngoại lệ được ném ra hay không
        assertThrows(EntityNotFoundException.class, () -> nhanVienService.xoaNhanVien(2), "Không thể xóa nhân viên không tồn tại");
    }

    @Test
    public void testXoaNhanVien_xoaNhanVienVoiMaRong() {
        // Kiểm tra trường hợp mã nhân viên là rỗng
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.xoaNhanVien(0), "Không thể xóa nhân viên với mã rỗng");
    }

    @Test
    public void testTimKiemNhanVien_tenRong() {
        when(nhanVienRepository.findAll()).thenReturn(Collections.emptyList());

        List<NhanVien> ketQua = nhanVienService.timNhanVienTheoTen("");

        assertTrue(ketQua.isEmpty());
    }

    @Test
    public void testTimKiemNhanVien_theoTrangThaiHoatDong() {
        NhanVien nhanVien1 = new NhanVien(1, "Nguyen Van A", "NV01", "vanA", "123", true);
        NhanVien nhanVien2 = new NhanVien(2, "Tran Van B", "NV02", "vanB", "124", false);
        List<NhanVien> danhSachNhanVien = Arrays.asList(nhanVien1, nhanVien2);

        when(nhanVienRepository.findByTrangThai(true)).thenReturn(Arrays.asList(nhanVien1));

        List<NhanVien> ketQua = nhanVienService.timNhanVienTheoTrangThai(true);

        assertEquals(1, ketQua.size());
        assertEquals("Nguyen Van A", ketQua.get(0).getTen());
    }


}