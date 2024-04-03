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
    <form action="/hoa-don/update?idUpdate=${oldHoaDon.id}" method="post">
        <div>
            <div class="mb-3">
                <label>Khach Hang</label>
                <select class="form-select" aria-label="Default select example" name="khachHang">
                    <option selected>Open this select menu</option>
                    <c:forEach items="${listKhachHang}" var="kh">
                        <option value="${kh.id}" ${oldHoaDon.khachHang.id == kh.id ? 'selected' : ''}>
                                ${kh.hoTen}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Trang Thai</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio1" value="Active"
                        ${oldHoaDon.trangThai == 'Active' ? 'checked' : ''}
                    >
                    <label class="form-check-label" for="inlineRadio1">Active</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio2" value="Inactive"
                        ${oldHoaDon.trangThai == 'Inactive' ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio2">Inactive</label>
                </div>
            </div>
            <div class="mb-3">
                <label for="diaChi" class="form-label">Dia Chi</label>
                <input type="text" class="form-control" id="diaChi" name="diaChi" value="${oldHoaDon.diaChi}">
            </div>
            <div class="mb-3">
                <label for="sdt" class="form-label">So Dien Thoai</label>
                <input type="text" class="form-control" id="sdt" name="sdt" value="${oldHoaDon.soDienThoai}">
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-outline-primary">Update</button>
        </div>
    </form>
</div>
</body>
</html>
