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
<form action="/mau-sac/update?id=${oldMauSac.id}" method="post">
    <div>
        <div class="mb-3">
            <label for="maMS" class="form-label">Ma Mau</label>
            <input type="text" class="form-control" id="maMS" name="maMS" value="${oldMauSac.maMau}">
        </div>
        <div class="mb-3">
            <label for="tenMS" class="form-label">Ten Mau</label>
            <input type="text" class="form-control" id="tenMS" name="tenMS" value="${oldMauSac.tenMau}">
        </div>
        <div class="mb-3">
            <label class="form-label">Trang Thai</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio1"
                       value="Active" ${oldMauSac.trangThai == 'Active' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio1">Active</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="inlineRadio2"
                       value="Inactive" ${oldMauSac.trangThai == 'Inactive' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio2">Inactive</label>
            </div>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Submit</button>
</form>
</body>
</html>
