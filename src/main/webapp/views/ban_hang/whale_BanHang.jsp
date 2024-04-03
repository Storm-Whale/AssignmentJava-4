<%--
  Created by IntelliJ IDEA.
  User: m0ng3
  Date: 3/31/2024
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="/views/js/jsMain.js"></script>
</head>
<body>
<div class="mt-3 container ">
    <div class="row">
        <div class="col-7">
            <h2>Danh sách hoá đơn</h2>
            <table class="table">
                <thead>
                <tr>
                    <td>STT</td>
                    <td>ID</td>
                    <td>Ten khach hang</td>
                    <td>Ngay tao</td>
                    <td>Tong tien</td>
                    <td>Trang Thai</td>
                    <td>Chuc nang</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHD}" var="hd" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${hd.id}</td>
                        <td>${hd.khachHang.hoTen}</td>
                        <td>${hd.ngayTao}</td>
                        <td>${hd.tongTien()}</td>
                        <td>${hd.trangThai == 'Active' ? 'Da Thanh Toan' : 'Chua Thanh Toan'}</td>
                        <td>
                            <a class="btn btn-outline-primary" href="/ban-hang/index?idHD=${hd.id}">Chon</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--    --------------------------------------------------------------------------------------------------------------%>
            <%--    Danh Sach Hoa Don Chi Tiet--%>
            <%--    --------------------------------------------------------------------------------------------------------------%>
            <h2>Danh sách hoá đơn chi tiết</h2>
            <table class="table text-center">
                <thead>
                <tr>
                    <td>STT</td>
                    <td>ID</td>
                    <td>Ten san pham</td>
                    <td>So luong</td>
                    <td>Gia ban</td>
                    <td>Tong tien</td>
                    <td colspan="3">Chuc nang</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHDCT}" var="hdct" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${hdct.id}</td>
                        <td>${hdct.sanPhamChiTiet.sanPham.tenSanPham}</td>
                        <td>${hdct.soLuongMua}</td>
                        <td>${hdct.giaBan}</td>
                        <td>${hdct.tongTien}</td>
                        <td>
                            <a class="btn btn-primary"
                               href="/ban-hang/chinhSuaHDCT?idSPCT=${hdct.id}&action=them&idHD=${hdct.hoaDon.id}">+</a>
                            <a class="btn btn-primary"
                               href="/ban-hang/chinhSuaHDCT?idSPCT=${hdct.id}&action=giam&idHD=${hdct.hoaDon.id}">-</a>
                            <a class="btn btn-danger"
                               href="/ban-hang/chinhSuaHDCT?idSPCT=${hdct.id}&action=xoa&idHD=${hdct.hoaDon.id}">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-5">
            <%--    --------------------------------------------------------------------------------------------------------------%>
            <%--    Tao Hoa Don--%>
            <%--    --------------------------------------------------------------------------------------------------------------%>
            <h2>Tạo hoá đơn</h2>
            <div>
            </div>
            <form action="/ban-hang/taoHoaDon" method="post">
                <div>
                    <label class="mb-3 col-3">Số điện thoại</label>
                    <input type="text" class="col-7" value="${hoaDonByID.soDienThoai}" name="sdt">
                </div>
                <a class="btn btn-primary mb-3" href="/ban-hang/searchSDT">Search</a>
                <div class="row">
                    <div class="mb-3">
                        <label class="col-3">Ten Khach hang</label>
                        <input type="text" class="col-7" readonly value="${hoaDonByID.khachHang.hoTen}"
                               name="tenKhachHang">
                    </div>
                    <div class="mb-3">
                        <label class="col-3">ID Hoa don</label>
                        <input type="text" class="col-7" readonly value="${hoaDonByID.id}">
                    </div>
                    <div class="mb-3">
                        <label class="col-3">Tong tien</label>
                        <input type="text" class="col-7" readonly value="${hoaDonByID.tongTien()}">
                    </div>
                    <div>
                        <button class="btn btn-primary">Tạo hoá đơn</button>
                        <a id="thanhToan" class="btn btn-success" href="/ban-hang/thanhToan?idHD=${hoaDonByID.id}"
                           onclick="handleClick()">Thanh Toan</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%--    --------------------------------------------------------------------------------------------------------------%>
    <%--    Danh Sach San Pham Chi Tiet--%>
    <%--    --------------------------------------------------------------------------------------------------------------%>
    <div>
        <h2>Danh sách chi tiết sản phẩm</h2>
        <%--        <div class="mt-3 mb-3 row">--%>
        <%--            <div class="col-10 offset-1">--%>
        <%--                <form action="#" method="get">--%>
        <%--                    <label for="timKiemSpct">Tim Kiem</label>--%>
        <%--                    <div class="row">--%>
        <%--                        <div class="col-9">--%>
        <%--                            <input class="form-control" type="text" id="timKiemSpct" name="keywordSpct">--%>
        <%--                        </div>--%>
        <%--                        <div class="col-3">--%>
        <%--                            <button class="btn btn-primary">Tim Kiem</button>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </form>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID CTSP</td>
                <td>Ten san pham</td>
                <td>Mau sac</td>
                <td>Size</td>
                <td>Gia ban</td>
                <td>So luong ton</td>
                <td>Trang Thai</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listSpct}" var="spct" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${spct.id}</td>
                    <td>${spct.sanPham.tenSanPham}</td>
                    <td>${spct.mauSac.tenMau}</td>
                    <td>${spct.size.tenSize}</td>
                    <td>${spct.giaBan}</td>
                    <td>${spct.soLuongTon}</td>
                    <td>${spct.trangThai}</td>
                    <td>
                        <a class="btn btn-primary"
                           href="/ban-hang/themMoiSanPham?idSpct=${spct.id}&idHD=${hoaDonByID.id}">Chon</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
