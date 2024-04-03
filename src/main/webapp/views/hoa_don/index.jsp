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
    <a class="btn btn-outline-primary" href="/hoa-don/create">Create</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Ten Khach Hang</th>
            <th scope="col">Trang Thai</th>
            <th scope="col">Ngay Tao</th>
            <th scope="col">Ngay Sua</th>
            <th scope="col">Dia Chi</th>
            <th scope="col">So Dien Thoai</th>
            <th scope="col" colspan="2">Thao Tac</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listHoaDon}" var="hd" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${hd.id}</td>
                <td>${hd.khachHang.hoTen}</td>
                <td>${hd.trangThai}</td>
                <td>${hd.ngayTao}</td>
                <td>${hd.ngaySua}</td>
                <td>${hd.diaChi}</td>
                <td>${hd.soDienThoai}</td>
                <td><a href="/hoa-don/edit?idUpdate=${hd.id}">Update</a></td>
                <td><a href="/hoa-don/delete?idDelete=${hd.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
