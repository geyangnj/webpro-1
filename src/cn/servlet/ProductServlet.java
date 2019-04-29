package cn.servlet;

import cn.model.Product;
import cn.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
// ProductServlet 类名
public class ProductServlet extends HttpServlet {

    // ProductServlet --> ProductService  --> ProductDao
    private ProductService productService = new ProductService();

    // 用来处理post请求  from表单传递参数(安全高,耗资源)
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        // request默认编码是IOS8859-1 不支持中文
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("doPost.................");
        // request: 客户端提交到服务器端的请求,所有的信息都存储到request中
        // response: 服务器端返回的给客户端的请求，称为response
        Product product = new Product();
        // 下面的过程就是获取前端form表单数据的过程
        product.setName(request.getParameter("name"));
        double price = Double.parseDouble(request.getParameter("price"));
        product.setRemark(request.getParameter("remark"));
        product.setPrice(price);
        productService.save(product);
        // 跳转到查询页面.search.jsp
        response.sendRedirect("/webpro/search.jsp");
    }
    // 用来处理get请求，通过地址栏传递参数(安全性低,省资源)
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 1: 获取客户端查询的关键字
        String keyword = request.getParameter("keyword");
        // 2: 调用业务逻辑  alt + enter可以自动导入包
        ArrayList<Product> proList = productService.queryByName(keyword);
        // jsp中可以采用request对象实现两个页面数据传递
        request.setAttribute("proList",proList);
        // 3: sendRedirect: 重定向,它不会共享request中存储的数据
//      // response.sendRedirect("/webpro/search.jsp");
        // 4: 转发: 可以共享两个页面之间的数据, 系统为转发默认添加了工程名
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/search.jsp");
        dispatcher.forward(request,response);
    }
}
