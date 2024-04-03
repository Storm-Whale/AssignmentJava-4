package com.whale.buoi_n_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hdct")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hoa_don")
    @ManyToOne
    private HoaDon hoaDon;
    @JoinColumn(name = "id_ctsp")
    @ManyToOne
    private SanPhamChiTiet sanPhamChiTiet;
    @Column(name = "so_luong_mua")
    private Integer soLuongMua;
    @Column(name = "gia_ban")
    private Double giaBan;
    @Column(name = "tong_tien")
    private Double tongTien;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = " ngay_tao")
    private Date ngayTao;
    @Column(name = " ngay_sua")
    private Date ngaySua;

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "id=" + id +
                ", hoaDon=" + hoaDon +
                ", sanPhamChiTiet=" + sanPhamChiTiet +
                ", soLuongMua=" + soLuongMua +
                ", giaBan=" + giaBan +
                ", tongTien=" + tongTien +
                ", trangThai='" + trangThai + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                '}';
    }
}
