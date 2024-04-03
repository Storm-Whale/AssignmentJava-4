<%--
  Created by IntelliJ IDEA.
  User: m0ng3
  Date: 3/25/2024
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<form action="/danh-muc/update?id=${oldDanhMuc.id}" method="post">
    <div>
        <div class="mb-3">
            <label for="maDM" class="form-label">Ma Danh Muc</label>
            <input type="text" class="form-control" id="maDM" name="maDM" value="${oldDanhMuc.maDanhMuc}">
        </div>
        <div class="mb-3">
            <label for="tenDM" class="form-label">Ten Danh Muc</label>
            <input type="text" class="form-control" id="tenDM" name="tenDM" value="${oldDanhMuc.tenDanhMuc}">
        </div>
        <div class="mb-3">
            <label class="form-label">Trang Thai</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio1"
                       value="Active" ${oldDanhMuc.trangThai == 'Active' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio1">Active</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio2"
                       value="Inactive" ${oldDanhMuc.trangThai == 'Inactive' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio2">Inactive</label>
            </div>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Submit</button>
</form>
</body>
</html>
