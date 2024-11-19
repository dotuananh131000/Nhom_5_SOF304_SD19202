<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
        }

        .navbar {
            background-color: #007bff !important;
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
        }
        .navbar-brand:hover, .nav-link:hover {
            color: #ffdd57 !important;
        }

        .list-group-item {
            font-weight: 500;
        }
        .list-group-item:hover {
            background-color: #dfe4ea;
            transition: background-color 0.3s ease;
        }

        .dropdown-item:hover {
            background-color: #007bff;
            color: #fff;
        }

        /* Main content */
        main {
            padding: 30px;
        }

        aside {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 10px;
        }

        /* Custom button styling */
        .btn-custom {
            background-color: #007bff;
            color: #fff;
            width: 200px;
            margin: 10px 0;
            transition: transform 0.3s ease, background-color 0.3s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-custom:hover {
            background-color: #0056b3;
            transform: translateY(-5px);
        }

        /* Carousel styling */
        .carousel-inner img {
            height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }
        .carousel {
            margin-top: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .carousel-control-prev-icon,
        .carousel-control-next-icon {
            background-color: #333;
            padding: 15px;
            border-radius: 50%;
        }

    </style>
    <title>ASM</title>
</head>

<body>
<div class="container-fluid" style="padding: 0">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <i class="bi bi-house"></i> Trang chủ
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="#"><i class="bi bi-envelope"></i> Liên hệ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-arrow-up-right-square-fill"></i> Góp ý</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-question"></i> Hỏi đáp</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                            <i class="bi bi-person-fill"></i> Tài khoản
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Đăng nhập</a></li>
                            <li><a class="dropdown-item" href="#">Đăng ký</a></li>
                            <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Hồ sơ cá nhân</a></li>
                            <li><a class="dropdown-item" href="#">Đổi mật khẩu</a></li>
                            <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Đơn đặt hàng</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#"><i class="bi bi-star-fill"></i> Tiếng Việt</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-currency-dollar"></i> English</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <main class="row">
        <aside class="col-3">
            <div class="list-group">
                <a href="#" class="list-group-item list-group-item-primary"><i class="bi bi-backpack-fill"></i> CHỨC NĂNG</a>
                <div class="dropdown">
                    <a class="list-group-item list-group-item-action list-group-item-light dropdown-toggle"
                       href="#" role="button" id="dropdownSanPham" data-bs-toggle="dropdown">Quản lý sản phẩm</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownSanPham">
                        <li><a class="dropdown-item" href="/sanpham/show">Sản phẩm</a></li>
                        <li><a class="dropdown-item" href="/sanphamchitiet/show">Sản phẩm chi tiết</a></li>
                        <li><a class="dropdown-item" href="/mausac/show">Màu sắc</a></li>
                        <li><a class="dropdown-item" href="/kichthuoc/show">Kích thước</a></li>
                    </ul>
                </div>
                <div class="dropdown">
                    <a class="list-group-item list-group-item-action list-group-item-light dropdown-toggle"
                       href="#" role="button" id="dropdownQuanLy" data-bs-toggle="dropdown">Quản lý hệ thống</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownQuanLy">
                        <li><a class="dropdown-item" href="/nhanvien/show">Nhân viên</a></li>
                        <li><a class="dropdown-item" href="/khachhang/show">Khách hàng</a></li>
                        <li><a class="dropdown-item" href="/hoadon/show">Hóa đơn</a></li>
                        <li><a class="dropdown-item" href="/hoadonchitiet/show">Hóa đơn chi tiết</a></li>
                    </ul>
                </div>
                <a href="/banhang/hien-thi" class="list-group-item list-group-item-action list-group-item-light">Quản lý bán hàng</a>
            </div>
        </aside>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


