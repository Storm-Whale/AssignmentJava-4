package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.MauSac;
import com.whale.buoi_n_1.entities.SanPham;
import com.whale.buoi_n_1.entities.SanPhamChiTiet;
import com.whale.buoi_n_1.entities.Size;
import com.whale.buoi_n_1.repositories.MauSac_Repository;
import com.whale.buoi_n_1.repositories.SanPhamChiTiet_Repository;
import com.whale.buoi_n_1.repositories.SanPham_Repository;
import com.whale.buoi_n_1.repositories.Size_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = {
        "/san-pham-chi-tiet/index",
        "/san-pham-chi-tiet/create",
        "/san-pham-chi-tiet/store",
        "/san-pham-chi-tiet/update",
        "/san-pham-chi-tiet/edit",
        "/san-pham-chi-tiet/delete"
})
public class SanPhamChiTiet_Servlet extends HttpServlet {
    private SanPhamChiTiet_Repository res = new SanPhamChiTiet_Repository();
    private SanPham_Repository spRes = new SanPham_Repository();
    private MauSac_Repository msRes = new MauSac_Repository();
    private Size_Repository szRes = new Size_Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            //
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPhamChiTiet> spct = res.getAll();
        request.setAttribute("listSanPhamChiTiet", spct);
        request.getRequestDispatcher("/views/san_pham_chi_tiet/index.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        Double gia = Double.parseDouble(request.getParameter("gia"));
        Integer slg = Integer.parseInt(request.getParameter("slg"));
        String trangThai = request.getParameter("trangThai");
        Integer id_sp = Integer.parseInt(request.getParameter("sp"));
        Integer id_ms = Integer.parseInt(request.getParameter("ms"));
        Integer id_sz = Integer.parseInt(request.getParameter("sz"));
        SanPham sp = new SanPham();
        MauSac ms = new MauSac();
        Size sz = new Size();
        SanPhamChiTiet ct = new SanPhamChiTiet();
        ct.setId(id);
        sp.setId(id_sp);
        ct.setSanPham(sp);
        ms.setId(id_ms);
        ct.setMauSac(ms);
        sz.setId(id_sz);
        ct.setSize(sz);
        ct.setGiaBan(gia);
        ct.setSoLuongTon(slg);
        ct.setTrangThai(trangThai);
        ct.setNgaySua(new Date());
        ct.setNgayTao(res.findById(id).getNgayTao());

        res.updateSpct(ct);
        response.sendRedirect("/san-pham-chi-tiet/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("ma");
        Double gia = Double.parseDouble(request.getParameter("gia"));
        Integer slg = Integer.parseInt(request.getParameter("slg"));
        String trangThai = request.getParameter("trangThai");
        Integer id_sp = Integer.parseInt(request.getParameter("sp"));
        Integer id_ms = Integer.parseInt(request.getParameter("ms"));
        Integer id_sz = Integer.parseInt(request.getParameter("sz"));
        SanPham sp = spRes.findById(id_sp);
        MauSac ms = msRes.findById(id_ms);
        Size sz = new Size();
        SanPhamChiTiet ct = new SanPhamChiTiet();
//        sp.setId(id_sp);
        ct.setSanPham(sp);
        ms.setId(id_ms);
        ct.setMauSac(ms);
        sz.setId(id_sz);
        ct.setSize(sz);
        ct.setGiaBan(gia);
        ct.setSoLuongTon(slg);
        ct.setTrangThai(trangThai);
        ct.setNgaySua(new Date());
        ct.setNgayTao(new Date());

        res.createSpct(ct);
        response.sendRedirect("/san-pham-chi-tiet/index");

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        SanPhamChiTiet ct = res.findById(id);

        res.deleteSpct(ct);
        response.sendRedirect("/san-pham-chi-tiet/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        SanPhamChiTiet ct = res.findById(id);
        List<SanPham> lstSp = spRes.getAll();
        List<MauSac> lstMs = msRes.getAll();
        List<Size> lstSz = szRes.getAll();
        request.setAttribute("sp", lstSp);
        request.setAttribute("ms", lstMs);
        request.setAttribute("sz", lstSz);
        request.setAttribute("data", ct);
        request.getRequestDispatcher("/views/san_pham_chi_tiet/update.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPham> lstSp = spRes.getAll();
        List<MauSac> lstMs = msRes.getAll();
        List<Size> lstSz = szRes.getAll();
        request.setAttribute("sp", lstSp);
        request.setAttribute("ms", lstMs);
        request.setAttribute("sz", lstSz);
        request.getRequestDispatcher("/views/san_pham_chi_tiet/create.jsp").forward(request, response);
    }
}
