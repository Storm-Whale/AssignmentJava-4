package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.HoaDon;
import com.whale.buoi_n_1.entities.HoaDonChiTiet;
import com.whale.buoi_n_1.entities.KhachHang;
import com.whale.buoi_n_1.entities.SanPhamChiTiet;
import com.whale.buoi_n_1.repositories.HoaDonChiTiet_Repository;
import com.whale.buoi_n_1.repositories.HoaDon_Repository;
import com.whale.buoi_n_1.repositories.KhachHang_Repository;
import com.whale.buoi_n_1.repositories.SanPhamChiTiet_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DuongKieuAnh_Store_Servlet", value = {
        "/ban-hang/index",
        "/ban-hang/taoHoaDon",
        "/ban-hang/themMoiSanPham",
        "/ban-hang/chinhSuaHDCT",
        "/ban-hang/thanhToan",
        "/ban-hang/searchSDT"
})
public class KieuAnh_BanHang_Servlet extends HttpServlet {

    private SanPhamChiTiet_Repository spctRepository = new SanPhamChiTiet_Repository();
    private HoaDon_Repository hdRepository = new HoaDon_Repository();
    private KhachHang_Repository khRepository = new KhachHang_Repository();
    private HoaDonChiTiet_Repository hdctRepository = new HoaDonChiTiet_Repository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        if (uri.contains("index")) {
            this.index(req, resp);
        } else if (uri.contains("themMoiSanPham")) {
            this.themMoiSanPham(req, resp);
        } else if (uri.contains("chinhSuaHDCT")) {
            this.chinhSuaHDCT(req, resp);
        } else if (uri.contains("thanhToan")) {
            this.thanhToan(req, resp);
        } else if (uri.contains("searchSDT")) {
            this.searchSDT(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("taoHoaDon")) {
            this.taoHoaDon(req, resp);
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Begin : San Pham Chi Tiet
        List<SanPhamChiTiet> listSpct = spctRepository.findByTrangThai();
        req.setAttribute("listSpct", listSpct);
//        End : San Pham Chi Tiet

//        Begin : Hoa Don
        List<HoaDon> listHD = hdRepository.findByTrangThai();
        req.setAttribute("listHD", listHD);
//        End : Hoa Don

//        Begin : Hoa Don Chi Tiet
        if (req.getParameter("idHD") != null) {
            int idHD = Integer.parseInt(req.getParameter("idHD"));
            List<HoaDonChiTiet> listHDCT = hdctRepository.findByIdHD(idHD);
            HoaDon value = hdRepository.findById(idHD);
            req.setAttribute("hoaDonByID", value);
            req.setAttribute("listHDCT", listHDCT);
            req.getRequestDispatcher("/views/ban_hang/whale_BanHang.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/ban_hang/whale_BanHang.jsp").forward(req, resp);
        }
//        End : Hoa Don Chi Tiet
    }

    protected void taoHoaDon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HoaDon newHoaDon = new HoaDon();
        KhachHang khachHangHoaDon = new KhachHang();
        String sdt = req.getParameter("sdt");
        if (sdt.trim().length() == 0 || sdt == null) {
            khachHangHoaDon.setHoTen("Khach Van Lai");
            khachHangHoaDon.setNgayTao(new Date());
            khachHangHoaDon.setNgaySua(new Date());
            khRepository.createKhachHang(khachHangHoaDon);

            newHoaDon.setKhachHang(khRepository.findById(khachHangHoaDon.getId()));
            newHoaDon.setTrangThai("Inactive");
            newHoaDon.setNgayTao(new Date());
            newHoaDon.setNgaySua(new Date());
            hdRepository.createHoaDon(newHoaDon);
        } else {
            khachHangHoaDon = khRepository.findBySDT(sdt);
            newHoaDon.setKhachHang(khachHangHoaDon);
            newHoaDon.setDiaChi(khachHangHoaDon.getDiaChi());
            newHoaDon.setSoDienThoai(khachHangHoaDon.getSdt());
            newHoaDon.setTrangThai("Inactive");
            newHoaDon.setNgayTao(new Date());
            newHoaDon.setNgaySua(new Date());
            hdRepository.createHoaDon(newHoaDon);
        }
        resp.sendRedirect("/ban-hang/index");
    }

    private void themMoiSanPham(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idSpct = Integer.parseInt(req.getParameter("idSpct"));
        int idHd = Integer.parseInt(req.getParameter("idHD"));
        int soLuongMua = 1;
        for (HoaDonChiTiet hdct : hdctRepository.findByIdHD(idHd)) {
            if (hdct.getSanPhamChiTiet().getId() == idSpct) {
                soLuongMua += hdct.getSoLuongMua();
                break;
            }
        }
        if (soLuongMua > 1) {
            HoaDonChiTiet hoaDonChiTiet = hdctRepository.findByIdSpct(idSpct, idHd);
            hoaDonChiTiet.setSoLuongMua(soLuongMua);
            hoaDonChiTiet.setTongTien(hoaDonChiTiet.getSanPhamChiTiet().getGiaBan() * soLuongMua);
            this.chinhSuaSoLuongSanPham("them", idSpct, 1);
            hdctRepository.updateHdct(hoaDonChiTiet);
        } else {
            SanPhamChiTiet spct = spctRepository.findById(idSpct);
            HoaDon hoaDon = hdRepository.findById(idHd);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setTrangThai("Active");
            hoaDonChiTiet.setSanPhamChiTiet(spct);
            hoaDonChiTiet.setSoLuongMua(soLuongMua);
            hoaDonChiTiet.setGiaBan(spct.getGiaBan());
            hoaDonChiTiet.setTongTien(spct.getGiaBan() * hoaDonChiTiet.getSoLuongMua());
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTiet.setNgaySua(new Date());
            hdctRepository.createHdct(hoaDonChiTiet);
            this.chinhSuaSoLuongSanPham("them", idSpct, 1);
        }
        resp.sendRedirect("/ban-hang/index?idHD=" + idHd);
    }

    private void chinhSuaHDCT(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action").trim();
        int idHD = Integer.parseInt(req.getParameter("idHD"));
        int idHdct = Integer.parseInt(req.getParameter("idSPCT"));
        HoaDonChiTiet hoaDonChiTiet = hdctRepository.findById(idHdct);
        int soLuongMua = hoaDonChiTiet.getSoLuongMua();
        double giaTien = hoaDonChiTiet.getGiaBan();
        double tongTien = hoaDonChiTiet.getTongTien();
        switch (action) {
            case "them": {
                hoaDonChiTiet.setSoLuongMua(soLuongMua + 1);
                hoaDonChiTiet.setTongTien(tongTien + giaTien);
                hdctRepository.updateHdct(hoaDonChiTiet);
                this.chinhSuaSoLuongSanPham(action, hoaDonChiTiet.getSanPhamChiTiet().getId(), 1);
                break;
            }
            case "giam": {
                hoaDonChiTiet.setSoLuongMua(soLuongMua - 1);
                hoaDonChiTiet.setTongTien(tongTien - giaTien);
                hdctRepository.updateHdct(hoaDonChiTiet);
                this.chinhSuaSoLuongSanPham(action, hoaDonChiTiet.getSanPhamChiTiet().getId(), 1);
                break;
            }
            case "xoa": {
                this.chinhSuaSoLuongSanPham(action, hoaDonChiTiet.getSanPhamChiTiet().getId(), hoaDonChiTiet.getSoLuongMua());
                hdctRepository.deleteHdct(hoaDonChiTiet);
                break;
            }
        }
        resp.sendRedirect("/ban-hang/index?idHD=" + idHD);
    }

    protected void thanhToan(HttpServletRequest req, HttpServletResponse response) throws IOException {
        int idHD = Integer.parseInt(req.getParameter("idHD"));
        HoaDon hd = hdRepository.findById(idHD);
        hd.setTrangThai("Active");
        hdRepository.updateHoaDon(hd);
        response.sendRedirect("/ban-hang/index");
    }

    protected void searchSDT(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String sdt = req.getParameter("sdt");
        HoaDon hdBySDT = hdRepository.findBySdt(sdt);
        req.setAttribute("hoaDonByID", hdBySDT);
        response.sendRedirect("/ban-hang/index?idHD" + hdBySDT.getId());
    }

    public void chinhSuaSoLuongSanPham(String action, int idSpct, int soLuongMua) {
        SanPhamChiTiet spct = spctRepository.findById(idSpct);
        int soLuongSanPhamTon = spct.getSoLuongTon();
        switch (action) {
            case "them": {
                soLuongSanPhamTon -= 1;
                spct.setSoLuongTon(soLuongSanPhamTon);
                spctRepository.updateSpct(spct);
                break;
            }
            case "giam": {
                soLuongSanPhamTon += 1;
                spct.setSoLuongTon(soLuongSanPhamTon);
                spctRepository.updateSpct(spct);
                break;
            }
            default: {
                soLuongSanPhamTon += soLuongMua;
                spct.setSoLuongTon(soLuongSanPhamTon);
                spctRepository.updateSpct(spct);
                break;
            }
        }
    }
}
