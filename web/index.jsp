<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我是标题</title>
</head>
<body>
<!-- html标签,编写一个form完成完成用户数据的收集 -->
<!-- post代表post请求,Servlet的doPost方法会自动调用 -->
<!-- action跳转的目标地址：ProductServlet地址在web.xml中有配置-->
<form action="/webpro/ProductServlet" method="post">
    <!-- 后台是通过name属性指定的值,来进行数据的获取 -->
    商品名:<input type="text" name="name"/><br/>
    商品价格:<input type="text" name="price"/><br/>
    商品备注:<input type="text" name="remark"/></br>
    <button type="submit">添加商品</button>
</form>
</body>
</html>
