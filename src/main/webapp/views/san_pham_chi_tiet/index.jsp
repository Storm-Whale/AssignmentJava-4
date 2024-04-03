<%--
  Created by IntelliJ IDEA.
  User: m0ng3
  Date: 3/25/2024
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a class="btn btn-outline-primary" href="/san-pham-chi-tiet/create">Create</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">TenSanPham</th>
            <th scope="col">TenMau</th>
            <th scope="col">TenSize</th>
            <th scope="col">GiaBan</th>
            <th scope="col">SoLuongTon</th>
            <th scope="col">Trang Thai</th>
            <th scope="col">Ngay Tao</th>
            <th scope="col">Ngay Sua</th>
            <th scope="col">TenDanhMuc</th>
            <th colspan="2" scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listSanPhamChiTiet}" var="spct" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${spct.id}</td>
                <td>${spct.sanPham.tenSanPham}</td>
                <td>${spct.mauSac.tenMau}</td>
                <td>${spct.size.tenSize}</td>
                <td>${spct.giaBan}</td>
                <td>${spct.soLuongTon}</td>
                <td>${spct.trangThai}</td>
                <td>${spct.ngayTao}</td>
                <td>${spct.ngaySua}</td>
                <td>${spct.sanPham.danhMuc.tenDanhMuc}</td>
                <td><a class="btn btn-primary" href="/san-pham-chi-tiet/edit?id=${spct.id}">Cập nhật</a></td>
                <td><a class="btn btn-danger" href="/san-pham-chi-tiet/delete?id=${spct.id}">Xóa</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="mt-3 btn btn-primary" href="/san-pham/index">Back</a>
</div>
</body>
</html>
