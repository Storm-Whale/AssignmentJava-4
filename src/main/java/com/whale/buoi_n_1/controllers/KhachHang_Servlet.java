package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.KhachHang;
import com.whale.buoi_n_1.repositories.KhachHang_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "KhachHang_Servlet", value = {
        "/khach-hang/index",
        "/khach-hang/create",
        "/khach-hang/edit",
        "/khach-hang/delete",
        "/khach-hang/store",
        "/khach-hang/update"
})
public class KhachHang_Servlet extends HttpServlet {

    private KhachHang_Repository khRepository = new KhachHang_Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/khach-hang/index")) {
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
        List<KhachHang> listKhachHang = khRepository.findAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.getRequestDispatcher("/views/khach_hang/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/khach_hang/create.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUpdate"));
        KhachHang oldKhachHang = khRepository.findById(id);
        request.setAttribute("oldKhachHang", oldKhachHang);
        request.getRequestDispatcher("/views/khach_hang/update.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        KhachHang oldKhachHang = khRepository.findById(id);
        khRepository.deleteKhachHang(oldKhachHang);
        response.sendRedirect("/khach-hang/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        KhachHang newKhachHang = new KhachHang();
        newKhachHang.setHoTen(request.getParameter("hoTen"));
        newKhachHang.setDiaChi(request.getParameter("diaChi"));
        newKhachHang.setSdt(request.getParameter("sdt"));
        newKhachHang.setTrangThai(request.getParameter("trangThai"));
        newKhachHang.setNgayTao(new Date());
        newKhachHang.setNgaySua(new Date());
        khRepository.createKhachHang(newKhachHang);
        response.sendRedirect("/khach-hang/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        KhachHang newKhachHang = new KhachHang();
        newKhachHang.setId(Integer.parseInt(request.getParameter("idUpdate")));
        newKhachHang.setHoTen(request.getParameter("hoTen"));
        newKhachHang.setDiaChi(request.getParameter("diaChi"));
        newKhachHang.setSdt(request.getParameter("sdt"));
        newKhachHang.setTrangThai(request.getParameter("trangThai"));
        newKhachHang.setNgayTao(khRepository.findById(Integer.parseInt(request.getParameter("idUpdate"))).getNgayTao());
        newKhachHang.setNgaySua(new Date());
        khRepository.updateKhachHang(newKhachHang);
        response.sendRedirect("/khach-hang/index");
    }
}