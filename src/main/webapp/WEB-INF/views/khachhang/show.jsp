<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Khách Hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <form action="/khachhang/search" method="get">
        <input type="text" name="maKH">
        <button type="submit">Search</button>
    </form>
    <br>
    <a href="/khachhang/add">Thêm khách hàng</a>
    <br>
    <h2 class="mb-4">Danh sách Khách Hàng</h2>

    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Số Điện Thoại</th>
            <th>Mã Khách Hàng</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list_KhachHang}" var="khachHang">
            <tr>
                <td>${khachHang.id}</td>
                <td>${khachHang.ten}</td>
                <td>${khachHang.sdt}</td>
                <td>${khachHang.maKH}</td>
                <td>${khachHang.trangThai ? "Đang hoạt động" : "Ngừng hoạt động"}</td>
                <td>
                    <a href="/khachhang/delete/${khachHang.id}" class="btn btn-danger btn-sm">Xóa</a>
                    <a href="/khachhang/detail?id=${khachHang.id}" class="btn btn-info btn-sm">Chi tiết</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/home" class="btn btn-secondary">Quay lại</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
