package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.DanhMuc;
import com.whale.buoi_n_1.entities.SanPham;
import com.whale.buoi_n_1.repositories.DanhMuc_Repository;
import com.whale.buoi_n_1.repositories.SanPham_Repository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = {
        "/san-pham/index",
        "/san-pham/create",
        "/san-pham/store",
        "/san-pham/update",
        "/san-pham/edit",
        "/san-pham/delete"
})
public class SanPham_Servlet extends HttpServlet {
    private SanPham_Repository spRepository = new SanPham_Repository();
    private DanhMuc_Repository dmRepository = new DanhMuc_Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/san-pham/index")) {
            this.index(request, response);
        } else if (uri.contains("/san-pham/create")) {
            this.create(request, response);
        } else if (uri.contains("/san-pham/edit")) {
            this.edit(request, response);
        } else {
            this.delete(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/san-pham/store")) {
            this.store(request, response);
        } else if (uri.contains("/san-pham/update")) {
            this.update(request, response);
        } else {

        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listSanPham", spRepository.getAll());
        request.getRequestDispatcher("/views/san_pham/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> danhMucList = dmRepository.getAll();
        request.setAttribute("listDanhMuc", danhMucList);
        request.getRequestDispatcher("/views/san_pham/create.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSP = request.getParameter("maSP");
        String tenSP = request.getParameter("tenSP");
        String trangThai = request.getParameter("trangThai");
        DanhMuc danhMuc = dmRepository.findById((Integer.parseInt(request.getParameter("danhMuc"))));
        System.out.println(danhMuc.toString());
        SanPham sanPham = new SanPham();
        sanPham.setMaSanPham(maSP);
        sanPham.setTenSanPham(tenSP);
        sanPham.setDanhMuc(danhMuc);
        sanPham.setNgaySua(new Date());
        sanPham.setNgayTao(new Date());
        sanPham.setTrangThai(trangThai);
        spRepository.createSp(sanPham);
        response.sendRedirect("/san-pham/index");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> danhMucList = dmRepository.getAll();
        request.setAttribute("listDanhMuc", danhMucList);

        int id = Integer.parseInt(request.getParameter("idUpdate"));
        request.setAttribute("oldSanPham", spRepository.findById(id));

        request.getRequestDispatcher("/views/san_pham/update.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        SanPham sp = spRepository.findById(id);
        System.out.println(sp.toString());
                spRepository.deleteSP(sp);
        response.sendRedirect("/san-pham/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSp = request.getParameter("maSP");
        String tenSP = request.getParameter("tenSP");
        String trangThai = request.getParameter("trangThai");
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(Integer.parseInt(request.getParameter("danhMuc")));
        int id = Integer.parseInt(request.getParameter("idUpdate"));
        SanPham sanPham = new SanPham();
        sanPham.setId(id);
        sanPham.setMaSanPham(maSp);
        sanPham.setTenSanPham(tenSP);
        sanPham.setTrangThai(trangThai);
        sanPham.setDanhMuc(danhMuc);
        sanPham.setNgayTao(new Date());
        sanPham.setNgaySua(new Date());
        spRepository.updateSP(sanPham);
        System.out.println("0--------------------------------------0");
        System.out.println(sanPham.toString());
        response.sendRedirect("/san-pham/index");
    }
}
