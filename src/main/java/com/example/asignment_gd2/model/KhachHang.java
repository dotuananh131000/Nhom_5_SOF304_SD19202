package com.example.asignment_gd2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KhachHang")
@Builder

public class KhachHang {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten")
    @NotBlank(message = "Vui lòng điền tên khách hàng")
    private String ten;

    @Column(name = "sdt")
    @NotBlank(message = "Vui lòng điền sdt khách hàng")
    private String sdt;

    @Column(name = "maKH")
    @NotBlank(message = "Vui lòng điền mã khách hàng: KH...")
    private String maKH;

    @Column(name = "trangThai")
    private boolean trangThai;

}
