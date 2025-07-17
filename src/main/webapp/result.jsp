<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kết quả Tải Tệp</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
<div class="container">
    <header>
        <h1><i class="fas fa-file-upload"></i> Quản Lý Tệp</h1>
        <p>Xem và quản lý các tệp đã được tải lên hệ thống</p>
    </header>

    <c:if test="${not empty fileName}">
        <div class="info-card">
            <h2><i class="fas fa-check-circle"></i> Tệp đã tải lên thành công</h2>
            <div class="file-info">
                <strong><i class="fas fa-file"></i> Tên tệp:</strong>
                <span>${fileName}</span>
            </div>
            <div class="file-info">
                <strong><i class="fas fa-weight"></i> Kích thước:</strong>
                <span>${fileSize} bytes</span>
            </div>
        </div>
    </c:if>

    <h2><i class="fas fa-list"></i> Danh sách tệp đã tải lên</h2>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th><i class="fas fa-file-alt"></i> Tên tệp</th>
                <th><i class="fas fa-database"></i> Kích thước</th>
                <th><i class="fas fa-cogs"></i> Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applicationScope.uploadedFiles}" var="file">
                <tr>
                    <td>${file.name}</td>
                    <td>${file.size} bytes</td>
                    <td>
                        <a href="/tomcatDemo_war_exploded/view?name=${file.name}" class="btn btn-view" target="_blank">
                            <i class="fas fa-eye"></i> Xem
                        </a>
                        <a href="/tomcatDemo_war_exploded/delete?name=${file.name}" class="btn btn-delete"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa tệp này?');">
                            <i class="fas fa-trash"></i> Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-20">
        <a href="upload.html" class="btn"><i class="fas fa-plus"></i> Tải tệp khác</a>
    </div>
</div>
</body>
</html>