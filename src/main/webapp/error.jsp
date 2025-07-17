<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
<div class="error-container">
    <h1><i class="fas fa-exclamation-triangle"></i> Có lỗi xảy ra</h1>
    <div class="error-message">
        <i class="fas fa-info-circle"></i> ${errorMessage}
    </div>
    <a href="upload.html" class="btn"><i class="fas fa-redo"></i> Thử lại</a>
</div>
</body>
</html>