<!doctype html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chi tiết Sản Phẩm Chi Tiết</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="form-container">
        <h2 class="text-center text-primary">Chi tiết Sản Phẩm Chi Tiết</h2>
        <form>
            <div class="form-group">
                <label for="id">ID:</label>
                <input type="number" class="form-control" id="id" name="id" value="${sanPhamChiTiet.id}" readonly>
            </div>
            <div class="form-group">
                <label for="maSPCT">Mã SPCT:</label>
                <input type="text" class="form-control" id="maSPCT" name="maSPCT" value="${sanPhamChiTiet.maSPCT}" readonly>
            </div>
            <div class="form-group">
                <label for="idMauSac">Màu Sắc:</label>
                <input type="text" class="form-control" id="idMauSac" name="idMauSac" value="${sanPhamChiTiet.mauSac.ten}" readonly>
            </div>
            <div class="form-group">
                <label for="idKichThuoc">Kích Thước:</label>
                <input type="text" class="form-control" id="idKichThuoc" name="idKichThuoc" value="${sanPhamChiTiet.kichThuoc.ten}" readonly>
            </div>
            <div class="form-group">
                <label for="idSanPham">Sản Phẩm:</label>
                <input type="text" class="form-control" id="idSanPham" name="idSanPham" value="${sanPhamChiTiet.sanPham.ten}" readonly>
            </div>
            <div class="form-group">
                <label for="soLuong">Số Lượng:</label>
                <input type="number" class="form-control" id="soLuong" name="soLuong" value="${sanPhamChiTiet.soLuong}" readonly>
            </div>
            <div class="form-group">
                <label for="donGia">Giá:</label>
                <input type="number" class="form-control" id="donGia" name="donGia" value="${sanPhamChiTiet.donGia}" step="0.01" readonly>
            </div>
            <div class="form-group">
                <label>Trạng thái:</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="trangThai" id="hoanThanh" value="true" ${sanPhamChiTiet.trangThai ? "checked" : ""} disabled>
                    <label class="form-check-label" for="hoanThanh">Còn hàng</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="trangThai" id="hetHang" value="false" ${sanPhamChiTiet.trangThai ? "" : "checked"} disabled>
                    <label class="form-check-label" for="hetHang">Hết hàng</label>
                </div>
            </div>
            <a href="/sanphamchitiet/show" class="btn btn-secondary">Quay lại</a>
        </form>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
