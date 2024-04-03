package com.whale.buoi_n_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ctsp")
@Entity
public class SanPhamChiTiet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "id_sp")
    @ManyToOne
    private SanPham sanPham;
    @JoinColumn(name = "id_mau_sac")
    @ManyToOne
    private MauSac mauSac;
    @JoinColumn(name = "id_size")
    @ManyToOne
    private Size size;
    @Column(name = "gia_ban")
    private Double giaBan;
    @Column(name = "so_luong_ton")
    private int soLuongTon;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
}
