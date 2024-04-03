package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.DanhMuc;
import com.whale.buoi_n_1.entities.Size;
import com.whale.buoi_n_1.repositories.DanhMuc_Repository;
import com.whale.buoi_n_1.repositories.Size_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(value = {
        "/size/index",
        "/size/create",
        "/size/edit",
        "/size/delete",
        "/size/store",
        "/size/update"
})
public class Size_Servlet extends HttpServlet {
    private Size_Repository repository = new Size_Repository();

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
        request.setAttribute("listSize", repository.getAll());
        request.getRequestDispatcher("/views/size/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/size/create.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        repository.deleteSize(repository.findById(id));
        response.sendRedirect("/size/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUpdate"));
        request.setAttribute("oldSize", repository.findById(id));
        request.getRequestDispatcher("/views/size/update.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDM = request.getParameter("maSize");
        String tenDM = request.getParameter("tenSize");
        String trangThai = request.getParameter("trangThai");
        System.out.println(maDM + tenDM + trangThai);
        Size size = new Size();
        size.setMaSize(maDM);
        size.setTenSize(tenDM);
        size.setTrangThai(trangThai);
        size.setNgayTao(new Date());
        size.setNgaySua(new Date());
        repository.createSize(size);
        response.sendRedirect("/size/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maDM = request.getParameter("maSize");
        String tenDM = request.getParameter("tenSize");
        String trangThai = request.getParameter("trangThai");
        int id = Integer.parseInt(request.getParameter("id"));
        Size size = new Size();
        size.setId(id);
        size.setMaSize(maDM);
        size.setTenSize(tenDM);
        size.setTrangThai(trangThai);
        size.setNgayTao(repository.findById(id).getNgayTao());
        size.setNgaySua(new Date());
        repository.updateSize(size);
        response.sendRedirect("/size/index");
    }
}
