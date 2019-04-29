package cn.servlet;

import cn.model.Product;
import cn.service.ProductService;

import java.io.IOException;
import javax.servlet.http.*;
// ProductServlet 类名
public class ProductServlet extends HttpServlet {

    // ProductServlet --> ProductService  --> ProductDao
    private ProductService productService = new ProductService();

    // 用来处理post请求  from表单传递参数(安全高,耗资源)
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        System.out.println("doPost.................");
        // request: 客户端提交到服务器端的请求,所有的信息都存储到request中
        // response: 服务器端返回的给客户端的请求，称为response
        Product product = new Product();
        // 下面的过程就是获取前端form表单数据的过程
        product.setName(request.getParameter("aa"));
        double price = Double.parseDouble(request.getParameter("price"));
        product.setRemark(request.getParameter("remark"));
        product.setPrice(price);
        productService.save(product);
    }
    // 用来处理get请求，通过地址栏传递参数(安全性低,省资源)
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
