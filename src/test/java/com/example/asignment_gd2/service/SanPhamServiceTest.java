package com.example.asignment_gd2.service;

import com.example.asignment_gd2.model.SanPham;
import com.example.asignment_gd2.repository.SanPhamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SanPhamServiceTest {

    @Autowired
    private SanPhamRepository sanPhamRepository;


    private SanPhamService sanPhamService;



    @BeforeEach
    void setUp() {

        sanPhamService = new SanPhamService();
    }

    @AfterEach
    void tearDown() {

        sanPhamService = null;
    }



    @Test
    public void testGetAllSanPham_ListNotEmpty_ShouldReturnProducts() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        List<SanPham> products = sanPhamService.getAllSanPham();
        assertNotNull(products, "Danh sách sản phẩm không được null");
        assertEquals(1, products.size(), "Danh sách sản phẩm phải chứa 1 sản phẩm");
    }

    @Test
    public void testGetAllSanPham_EmptyList_ShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> sanPhamService.getAllSanPham(),
                "Danh sách sản phẩm trống");
    }

    @Test
    public void testDeleteSanPham_ValidId_ShouldRemoveProduct() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        sanPhamService.deleteSanPham(1);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.getSanPhamById(1),
                "Sản phẩm đã bị xóa không thể tìm thấy");
    }

    @Test
    public void testDeleteSanPham_InvalidId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.deleteSanPham(-1),
                "ID không hợp lệ");
    }

    @Test
    public void testDeleteSanPham_NonExistingProduct_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.deleteSanPham(1),
                "Sản phẩm không tồn tại");
    }

    @Test
    public void testGetSanPhamById_ValidId_ShouldReturnProduct() {
        SanPham sp = new SanPham(1, "SP001", "Áo sơ mi", true);
        sanPhamService.createSanPham(sp);
        SanPham foundProduct = sanPhamService.getSanPhamById(1);
        assertNotNull(foundProduct, "Sản phẩm không được null");
        assertEquals(1, foundProduct.getId(), "ID sản phẩm phải là 1");
    }

    @Test
    public void testGetSanPhamById_InvalidId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.getSanPhamById(0),
                "ID không hợp lệ");
    }

    @Test
    public void testGetSanPhamById_NonExistingProduct_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.getSanPhamById(1),
                "Không tìm thấy sản phẩm");
    }

    @Test
    public void testCreateSanPham_ValidProduct_ShouldAddProduct() {
        SanPham newProduct = new SanPham(1, "SP001", "Áo sơ mi", true);
        SanPham createdProduct = sanPhamService.createSanPham(newProduct);
        assertNotNull(createdProduct, "Sản phẩm tạo không được null");
        assertEquals("SP001", createdProduct.getMa(), "Mã sản phẩm phải là SP001");
        assertEquals("Áo sơ mi", createdProduct.getTen(), "Tên sản phẩm phải là Áo sơ mi");
    }

    @Test
    public void testCreateSanPham_InvalidProduct_ShouldThrowException() {
        SanPham invalidProduct = new SanPham(1, "", "", true);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(invalidProduct),
                "Thông tin sản phẩm không hợp lệ");
    }

    @Test
    public void testCreateSanPham_DuplicateProduct_ShouldAddToList() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        SanPham duplicateProduct = new SanPham(2, "SP002", "Áo sơ mi", true);
        SanPham addedProduct = sanPhamService.createSanPham(duplicateProduct);
        assertEquals("SP002", addedProduct.getMa(), "Mã sản phẩm của bản sao phải là SP002");
        assertEquals("Áo sơ mi", addedProduct.getTen(), "Tên sản phẩm phải trùng với bản gốc");
    }

    @Test
    public void testCreateSanPham_NullProduct_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> sanPhamService.createSanPham(null),
                "Không thể thêm sản phẩm null");
    }

    @Test
    public void testDeleteSanPham_ListEmpty_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.deleteSanPham(1),
                "Danh sách trống không thể xóa sản phẩm");
    }

    @Test
    public void testCreateSanPham_DuplicateCode_ShouldThrowException() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        SanPham duplicateCodeProduct = new SanPham(2, "SP001", "Áo thun", true);
        assertThrows(IllegalArgumentException.class,
                () -> sanPhamService.createSanPham(duplicateCodeProduct),
                "Không thể thêm sản phẩm có mã trùng lặp");
    }

    @Test
    public void testCreateSanPham_DuplicateName_ShouldThrowException() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        SanPham duplicateNameProduct = new SanPham(2, "SP002", "Áo sơ mi", true);
        assertThrows(IllegalArgumentException.class,
                () -> sanPhamService.createSanPham(duplicateNameProduct),
                "Không thể thêm sản phẩm có tên trùng lặp");
    }

    @Test
    public void testGetSanPhamById_MultipleProducts_ShouldReturnCorrectProduct() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        sanPhamService.createSanPham(new SanPham(2, "SP002", "Áo thun", true));
        SanPham foundProduct = sanPhamService.getSanPhamById(2);
        assertEquals("SP002", foundProduct.getMa(), "Phải trả về sản phẩm với mã SP002");
        assertEquals("Áo thun", foundProduct.getTen(), "Tên sản phẩm phải là Áo thun");
    }

    @Test
    public void testDeleteSanPham_MultipleProducts_ShouldOnlyRemoveCorrectProduct() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        sanPhamService.createSanPham(new SanPham(2, "SP002", "Áo thun", true));
        sanPhamService.deleteSanPham(1);

        List<SanPham> products = sanPhamService.getAllSanPham();
        assertEquals(1, products.size(), "Danh sách chỉ còn 1 sản phẩm sau khi xóa");
        assertEquals("SP002", products.get(0).getMa(), "Sản phẩm còn lại phải có mã SP002");
    }

    @Test
    public void testCreateSanPham_SpecialCharactersInName_ShouldAddProduct() {
        SanPham specialNameProduct = new SanPham(1, "SP001", "Áo sơ mi @#$%", true);
        SanPham createdProduct = sanPhamService.createSanPham(specialNameProduct);
        assertEquals("Áo sơ mi @#$%", createdProduct.getTen(), "Tên sản phẩm phải chứa ký tự đặc biệt");
    }

    @Test
    public void testCreateSanPham_LongName_ShouldAddProduct() {
        String longName = "Áo sơ mi dài tay chất liệu cao cấp sản xuất tại Việt Nam";
        SanPham longNameProduct = new SanPham(1, "SP001", longName, true);
        SanPham createdProduct = sanPhamService.createSanPham(longNameProduct);
        assertEquals(longName, createdProduct.getTen(), "Tên sản phẩm phải giữ nguyên độ dài");
    }


    @Test
    public void testDeleteSanPham_LastProduct_ShouldLeaveEmptyList() {
        sanPhamService.createSanPham(new SanPham(1, "SP001", "Áo sơ mi", true));
        sanPhamService.deleteSanPham(1);
        assertThrows(IllegalStateException.class, () -> sanPhamService.getAllSanPham(),
                "Danh sách sản phẩm phải trống sau khi xóa sản phẩm cuối cùng");
    }



}
