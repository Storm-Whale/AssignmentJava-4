package com.whale.buoi_n_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mau_sac")
@Entity
public class MauSac {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ma_mau")
    private String maMau;
    @Column(name = "ten_mau")
    private String tenMau;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Override
    public String toString() {
        return "MauSac{" +
                "id=" + id +
                ", maMau=" + maMau +
                ", tenMau=" + tenMau +
                ", trangThai=" + trangThai +
                ", ngaySua=" + ngaySua +
                ", ngayTao=" + ngayTao +
                '}';
    }
}
