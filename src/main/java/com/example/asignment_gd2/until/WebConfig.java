package com.example.asignment_gd2.until;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){

        // Đăng ký Interceptor và chỉ áp dụng cho các URL bắt đầu bằng /kichthuoc và /mausac
        interceptorRegistry.addInterceptor(new Interceptor())
                .addPathPatterns("/kichthuoc/**", "/mausac/**", "/nhanvien/**", "/sanpham/**", "/khachhang/**", "/sanphamchitiet/**")
                .excludePathPatterns("/login", "/logout"); // Loại trừ các URL không cần kiểm tra đăng nhập
    }
}
