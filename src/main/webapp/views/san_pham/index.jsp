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
    <a class="btn btn-outline-primary" href="/san-pham/create">Create</a>
    <table class="table">
        <thead>
        <tr class="text-center">
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Ma Danh Muc</th>
            <th scope="col">Ten San Pham</th>
            <th scope="col">Trang Thai</th>
            <th scope="col">Ngay Tao</th>
            <th scope="col">Ngay Sua</th>
            <th scope="col">Ten Danh Muc</th>
            <th scope="col" colspan="2">Thao Tac</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listSanPham}" var="sp" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${sp.id}</td>
                <td>${sp.maSanPham}</td>
                <td>${sp.tenSanPham}</td>
                <td>${sp.trangThai}</td>
                <td>${sp.ngayTao}</td>
                <td>${sp.ngaySua}</td>
                <td>${sp.danhMuc.tenDanhMuc}</td>
                <td>
                    <a class="btn btn-outline-warning" href="/san-pham/edit?idUpdate=${sp.id}">Update</a>
                </td>
                <td>
                    <a class="btn btn-outline-danger" href="/san-pham/delete?idDelete=${sp.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
