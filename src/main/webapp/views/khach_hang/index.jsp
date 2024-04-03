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
    <a class="btn btn-outline-primary" href="/khach-hang/create">Create</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Ho Ten</th>
            <th scope="col">Dia Chi</th>
            <th scope="col">So Dien Thoai</th>
            <th scope="col">Trang Thai</th>
            <th scope="col">Ngay Tao</th>
            <th scope="col">Ngay Sua</th>
            <th scope="col" colspan="2">Thao Tac</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listKhachHang}" var="kh" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${kh.id}</td>
                <td>${kh.hoTen}</td>
                <td>${kh.diaChi}</td>
                <td>${kh.sdt}</td>
                <td>${kh.trangThai}</td>
                <td>${kh.ngayTao}</td>
                <td>${kh.ngaySua}</td>
                <td><a href="/khach-hang/edit?idUpdate=${kh.id}">Update</a></td>
                <td><a href="/khach-hang/delete?idDelete=${kh.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
