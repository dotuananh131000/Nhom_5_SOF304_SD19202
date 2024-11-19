<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách hóa đơn</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="mt-5" style="width: 90%; margin: 0 auto">
    <a href="/home" class="btn btn-secondary btn-sm mx-4">
        <i class="fas fa-home"></i> Home
    </a>

    <br><br>

    <form class="d-flex mb-3" role="search" action="/banhang/searchByidHoaDon" method="get">
        <input class="form-control me-2 w-25 mx-3" type="text" placeholder="Nhập ID:" aria-label="Search" name="idHoaDon">
        <button class="btn btn-outline-success" type="submit">
            <i class="fas fa-search"></i> Tìm kiếm
        </button>
    </form>

    <h2 class="mb-4 text-center">
        <i class="fas fa-file-invoice"></i> DANH SÁCH HÓA ĐƠN MUA HÀNG
    </h2>
    <div class="table-responsive" style="max-height: 500px; overflow:auto;">
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Mã NV</th>
                <th>Tên KH</th>
                <th>Ngày mua</th>
                <th>ID SPCT</th>
                <th>Tên SP</th>
                <th>MS</th>
                <th>KT</th>
                <th>SL</th>
                <th>Đơn giá</th>
                <th>Trạng thái</th>
                <th>Hoạt động</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty list_HoaDon}">
                <tr>
                    <td colspan="12" class="text-center">Không tìm thấy hóa đơn nào!</td>
                </tr>
            </c:if>
            <c:forEach var="hdct" items="${list_HoaDon}">
                <tr>
                    <td>${hdct.hoaDon.id}</td>
                    <td>${hdct.hoaDon.nhanVien.maNV}</td>
                    <td>${hdct.hoaDon.khachHang.ten}</td>
                    <td>${hdct.hoaDon.ngayMuaHang}</td>
                    <td>${hdct.sanPhamChiTiet.id}</td>
                    <td>${hdct.sanPhamChiTiet.sanPham.ten}</td>
                    <td>${hdct.sanPhamChiTiet.mauSac.ten}</td>
                    <td>${hdct.sanPhamChiTiet.kichThuoc.ten}</td>
                    <td>${hdct.soLuong}</td>
                    <td>${hdct.donGia}</td>
                    <td>
                        <span class="badge bg-${hdct.hoaDon.trangThai ? 'success' : 'danger'}">
                                ${hdct.hoaDon.trangThai ? "Đã thanh toán" : "Chưa thanh toán"}
                        </span>
                    </td>
                    <td>
                        <a href="/banhang/detail?id=${hdct.hoaDon.id}" class="btn btn-info btn-sm">
                            <i class="fas fa-info-circle"></i> Chi tiết
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <a href="/banhang/add" class="btn btn-primary">
        <i class="fas fa-plus"></i> Thêm hóa đơn mới
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
