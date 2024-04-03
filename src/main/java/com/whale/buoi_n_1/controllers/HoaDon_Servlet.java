package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.HoaDon;
import com.whale.buoi_n_1.entities.KhachHang;
import com.whale.buoi_n_1.repositories.HoaDon_Repository;
import com.whale.buoi_n_1.repositories.KhachHang_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "HoaDon_Servlet", value = {
        "/hoa-don/index",
        "/hoa-don/create",
        "/hoa-don/edit",
        "/hoa-don/delete",
        "/hoa-don/store",
        "/hoa-don/update"
})
public class HoaDon_Servlet extends HttpServlet {
    private HoaDon_Repository hdRepository = new HoaDon_Repository();
    private KhachHang_Repository khRepository = new KhachHang_Repository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            this.index(request, response);
        } else if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoaDon> listHoaDon = hdRepository.findAll();
        request.setAttribute("listHoaDon", listHoaDon);
        request.getRequestDispatcher("/views/hoa_don/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> listKhachHang = khRepository.findAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.getRequestDispatcher("/views/hoa_don/create.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUpdate"));
        HoaDon hoaDon = hdRepository.findById(id);
        List<KhachHang> listKhachHang = khRepository.findAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.setAttribute("oldHoaDon", hoaDon);
        request.getRequestDispatcher("/views/hoa_don/update.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        HoaDon oldHoaDon = hdRepository.findById(id);
        hdRepository.deleteHoaDon(oldHoaDon);
        response.sendRedirect("/hoa-don/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HoaDon newHoaDon = new HoaDon();
        KhachHang khachHangHoaDon = new KhachHang();
        String tenKhachHang = request.getParameter("khachHang");
        if (tenKhachHang.equals("KhachVangLai")) {
            khachHangHoaDon.setHoTen(tenKhachHang);
            khachHangHoaDon.setNgayTao(new Date());
            khachHangHoaDon.setNgaySua(new Date());
            khRepository.createKhachHang(khachHangHoaDon);

            newHoaDon.setKhachHang(khRepository.findById(khachHangHoaDon.getId()));
            newHoaDon.setTrangThai("Inactive");
            newHoaDon.setNgayTao(new Date());
            newHoaDon.setNgaySua(new Date());
            hdRepository.createHoaDon(newHoaDon);
        } else {
            khachHangHoaDon.setId(Integer.parseInt(request.getParameter("khachHang")));
            khachHangHoaDon.setHoTen(khRepository.findById(khachHangHoaDon.getId()).getHoTen());
            newHoaDon.setKhachHang(khachHangHoaDon);
            newHoaDon.setDiaChi(request.getParameter("diaChi"));
            newHoaDon.setSoDienThoai(request.getParameter("sdt"));
            newHoaDon.setTrangThai(request.getParameter("trangThai"));
            newHoaDon.setNgayTao(new Date());
            newHoaDon.setNgaySua(new Date());
            hdRepository.createHoaDon(newHoaDon);
        }
        response.sendRedirect("/hoa-don/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HoaDon newHoaDon = new HoaDon();
        KhachHang khachHangHoaDon = new KhachHang();
        newHoaDon.setId((Integer.parseInt(request.getParameter("idUpdate"))));
        khachHangHoaDon.setId(Integer.parseInt(request.getParameter("khachHang")));
        khachHangHoaDon.setHoTen(khRepository.findById(khachHangHoaDon.getId()).getHoTen());
        newHoaDon.setKhachHang(khachHangHoaDon);
        newHoaDon.setDiaChi(request.getParameter("diaChi"));
        newHoaDon.setSoDienThoai(request.getParameter("sdt"));
        newHoaDon.setTrangThai(request.getParameter("trangThai"));
        newHoaDon.setNgayTao(hdRepository.findById(Integer.parseInt(request.getParameter("idUpdate"))).getNgayTao());
        newHoaDon.setNgaySua(new Date());
        hdRepository.updateHoaDon(newHoaDon);
        response.sendRedirect("/hoa-don/index");
    }
}
