<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品查询页面</title>
</head>
<body>
    <!-- method="get" 会跳转doGet方法中-->
    <form action="/webpro/ProductServlet" method="get">
        查询关键字:<input type="text" name="keyword" />
        <button type="submit">给我搜</button>
    </form>
    <!-- 如果要编写java代码则需要代码声明 -->
    <%=request.getAttribute("proList")%>
</body>
</html>
