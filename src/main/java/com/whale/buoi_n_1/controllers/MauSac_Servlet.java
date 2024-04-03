package com.whale.buoi_n_1.controllers;

import com.whale.buoi_n_1.entities.MauSac;
import com.whale.buoi_n_1.repositories.MauSac_Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(value = {
        "/mau-sac/index",
        "/mau-sac/create",
        "/mau-sac/edit",
        "/mau-sac/delete",
        "/mau-sac/store",
        "/mau-sac/update"
})
public class MauSac_Servlet extends HttpServlet {
    private MauSac_Repository repository = new MauSac_Repository();

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

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listMauSac", repository.getAll());
        request.getRequestDispatcher("/views/mau_sac/index.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idDelete"));
        repository.deleteMauSac(repository.findById(id));
        response.sendRedirect("/mau-sac/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUpdate"));
        request.setAttribute("oldMauSac", repository.findById(id));
        request.getRequestDispatcher("/views/mau_sac/update.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/mau_sac/create.jsp").forward(request, response);
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

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maMS = request.getParameter("maMS");
        String tenMS = request.getParameter("tenMS");
        String trangThai = request.getParameter("trangThai");
        int id = Integer.parseInt(request.getParameter("id"));
        MauSac mauSac = new MauSac();
        mauSac.setId(id);
        mauSac.setMaMau(maMS);
        mauSac.setTenMau(tenMS);
        mauSac.setTrangThai(trangThai);
        mauSac.setNgayTao(repository.findById(id).getNgayTao());
        mauSac.setNgaySua(new Date());
        repository.updateMauSac(mauSac);
        response.sendRedirect("/mau-sac/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maMS = request.getParameter("maMS");
        String tenMS = request.getParameter("tenMS");
        String trangThai = request.getParameter("trangThai");
        MauSac mauSac = new MauSac();
        mauSac.setMaMau(maMS);
        mauSac.setTenMau(tenMS);
        mauSac.setTrangThai(trangThai);
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        repository.createMauSac(mauSac);
        response.sendRedirect("/mau-sac/index");
    }

}
