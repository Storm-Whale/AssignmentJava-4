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
    <form action="/san-pham/update?idUpdate=${oldSanPham.id}" method="post">
        <div class="mb-3">
            <label for="maSp" class="form-label">Ma San Pham</label>
            <input type="text" class="form-control" id="maSp" name="maSP" value="${oldSanPham.maSanPham}">
        </div>
        <div class="mb-3">
            <label for="tenSP" class="form-label">Ten San Pham</label>
            <input type="text" class="form-control" id="tenSP" name="tenSP" value="${oldSanPham.tenSanPham}">
        </div>
        <div class="mt-3">
            <label>Trang Thai</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio1" value="Available"
                ${oldSanPham.trangThai.equals('Active') ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio1">Active</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio2" value="Unavailable"
                ${oldSanPham.trangThai.equals('Inactive') ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio2">Inactive</label>
            </div>
        </div>
        <div class="mt-3">
            <select class="form-select" aria-label="Default select example" name="danhMuc">
                <option selected>Open this select menu</option>
                <c:forEach items="${listDanhMuc}" var="size">
                    <option value="${size.id}" ${oldSanPham.danhMuc.id == size.id ? "selected" : ""}>
                            ${size.tenDanhMuc}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="mt-3">
            <button class="btn btn-outline-primary">Add</button>
        </div>
    </form>
</div>
</body>
</html>
