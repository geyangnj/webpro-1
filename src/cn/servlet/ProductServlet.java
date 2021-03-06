package cn.servlet;

import cn.model.Product;
import cn.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
// ProductServlet 类名
// ProductServlet 是单例模式(只有一个对象), 在单例模式情况下要注意线程安全问题
public class ProductServlet extends HttpServlet {
    // 构造方法,没有返回值
    public ProductServlet(){
        System.out.print("ProductServlet()........");
    }

    // ProductServlet --> ProductService  --> ProductDao
    private ProductService productService = new ProductService();
    // 在单例模式下,不能把变量设置全局,否则会出现线程安全问题
//    private String keyword = null;

    // 用来处理post请求  from表单传递参数(安全高,耗资源)
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        // request默认编码是IOS8859-1 不支持中文
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("doPost.................");
        // 获取type属性的值,根据值来执行不同的代码模块
        String type = request.getParameter("type");
        if(type.equals("save")){
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
        }else if(type.equals("query")){
            // 1: 获取客户端查询的关键字,客户把自己的关键字存储到自己的session中
            HttpSession session = request.getSession();
            System.out.println("打印当前session ID" + session.getId());
            String keyword = request.getParameter("keyword");
            session.setAttribute("keyword",keyword);
            // 2: 调用业务逻辑  alt + enter可以自动导入包
            ArrayList<Product> proList = productService.queryByName(keyword);
            // jsp中可以采用request对象实现两个页面数据传递
            request.setAttribute("proList",proList);
            // 3: sendRedirect: 重定向,它不会共享request中存储的数据
    //      response.sendRedirect("/webpro/search.jsp");
            // 4: 转发: 可以共享两个页面之间的数据, 系统为转发默认添加了工程名
            RequestDispatcher dispatcher =  request.getRequestDispatcher("/search.jsp");
            dispatcher.forward(request,response);
        }else if(type.equals("delete")){
            // 1: 获取前端数据
            int id = Integer.parseInt(request.getParameter("id"));
            // 2: 调用删除方法,完成删除
            productService.delete(id);
            HttpSession session = request.getSession();
            System.out.println("打印当前session ID" + session.getId());
            String keyword = (String)session.getAttribute("keyword");
            // 3: 根据之前的查询关键字进行查询
            ArrayList<Product> proList = productService.queryByName(keyword);
            // jsp中可以采用request对象实现两个页面数据传递
            request.setAttribute("proList",proList);
            // 由于数据需要共享,因此只能使用转发功能
            RequestDispatcher dispatcher =  request.getRequestDispatcher("/search.jsp");
            dispatcher.forward(request,response);
        }
    }
    // 用来处理get请求，通过地址栏传递参数(安全性低,省资源)
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // 所有请求(get,post) 都会自动跳转到doPost方法
       this.doPost(request,response);
    }
}
