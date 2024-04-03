package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.DanhMuc;
import com.whale.buoi_n_1.repositories.DanhMuc_Repository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = {
        "/danh-muc/index", 
        "/danh-muc/create",
        "/danh-muc/edit",
        "/danh-muc/delete",
        "/danh-muc/store",
        "/danh-muc/update"
})
public class DanhMuc_Servlet extends HttpServlet {
    private DanhMuc_Repository repository = new DanhMuc_Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/danh-muc/index")) {
            this.index(request, response);
        } else if (uri.contains("/danh-muc/create")) {
            this.create(request, response);
        } else if (uri.contains("/danh-muc/edit")) {
            this.edit(request, response);
        } else if (uri.contains("/danh-muc/delete")) {
            this.delete(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("idDelete"));
        repository.deleteDanhMuc(repository.findById(id));
        response.sendRedirect("/danh-muc/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("idUpdate"));
        request.setAttribute("oldDanhMuc", repository.findById(id));
        request.getRequestDispatcher("/views/danh_muc/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/danh-muc/store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maDM = request.getParameter("maDM");
        String tenDM = request.getParameter("tenDM");
        String trangThai = request.getParameter("trangThai");
        int id = Integer.parseInt(request.getParameter("id"));
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(id);
        danhMuc.setMaDanhMuc(maDM);
        danhMuc.setTenDanhMuc(tenDM);
        danhMuc.setTrangThai(trangThai);
        danhMuc.setNgayTao(repository.findById(id).getNgayTao());
        danhMuc.setNgaySua(new Date());
        repository.updateDanhMuc(danhMuc);
        response.sendRedirect("/danh-muc/index");
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> listDanhMuc = repository.getAll();
        request.setAttribute("listDanhMuc", listDanhMuc);
        request.getRequestDispatcher("/views/danh_muc/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/danh_muc/create.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDM = request.getParameter("maDM");
        String tenDM = request.getParameter("tenDM");
        String trangThai = request.getParameter("trangThai");
        System.out.println(maDM + tenDM + trangThai);
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMaDanhMuc(maDM);
        danhMuc.setTenDanhMuc(tenDM);
        danhMuc.setTrangThai(trangThai);
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        repository.createDanhMuc(danhMuc);
        response.sendRedirect("/danh-muc/index");
    }
}
