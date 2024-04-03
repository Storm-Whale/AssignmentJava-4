package com.whale.buoi_n_1.entities;

import com.whale.buoi_n_1.repositories.HoaDonChiTiet_Repository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    public Double tongTien(){
        Double tongTien = (double) 0;
        HoaDonChiTiet_Repository hdctRepository = new HoaDonChiTiet_Repository();
        List<HoaDonChiTiet> listHdCT = hdctRepository.findByIdHD(this.getId());
        for (HoaDonChiTiet hdct : listHdCT){
            tongTien += hdct.getTongTien();
        }
        return tongTien;
    }

}
