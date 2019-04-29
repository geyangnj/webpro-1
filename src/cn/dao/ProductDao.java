package cn.dao;

import cn.model.Product;
import cn.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 主要是用来完成对Product表的CRUD操作
public class ProductDao {

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        ArrayList<Product> proList = dao.queryByName("");
        for (int i = 0; i < proList.size(); i++) {
            Product pro = proList.get(i);
            System.out.println(pro.toString());
        }

    }



    // 编写一个方法,完成根据关键字查询商品信息的功能
    public ArrayList<Product> queryByName(String name) {
        // 用来存储Product对象的泛型集合
        ArrayList<Product> proList = new ArrayList<Product>();
        String sql = "select * from product where name like ?";
        // 1: 获取connection对象
        Connection conn = JdbcUtils.getConnection();
        try {
            // 2: 创建sql语句,并且配置查询参数
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            // 3: 获取查询结果,结果集都被封装到ResultSet对象中
            // 光标被置于第一行之前。next 方法将光标移动到下一行，如果有记录返回True
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                // 把当前记录转化为Product对象
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setRemark(rs.getString("remark"));
                product.setDate(rs.getDate("date"));
                // 把product对象存储到ArrayList集合中
                proList.add(product);
            }
            return proList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 编写一个方法用来实现数据插入功能
    public void update(Product product) {
        // sql = "delete from product where id = ? "
        String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
        // 1: 获取数据库的连接对象 (Connection当前Java项目与数据库进行连接)
        Connection con = JdbcUtils.getConnection();
        // 2: 通过connection对象生成PreparedStatement对象,此对象用来接收SQL语句
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            // 配置第1个?号指定参数
            pre.setString(1, product.getName());
            pre.setDouble(2, product.getPrice());
            pre.setString(3, product.getRemark());
            pre.setInt(4, product.getId());
            // 执行最后的SQL语句 update: delete,save,update
            pre.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        // sql = "delete from product where id = ? "
        String sql = "delete from product where id = ?";
        // 1: 获取数据库的连接对象 (Connection当前Java项目与数据库进行连接)
        Connection con = JdbcUtils.getConnection();
        // 2: 通过connection对象生成PreparedStatement对象,此对象用来接收SQL语句
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            // 配置第1个?号指定参数
            pre.setInt(1, id);
            // 执行最后的SQL语句 update: delete,save,update
            pre.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 编写一个方法用来实现数据插入功能
    public void save(Product product) {
        String sql = "insert into product (name,price,remark ) values (?,?,?)";
        // 1: 获取数据库的连接对象 (Connection当前Java项目与数据库进行连接)
        Connection con = JdbcUtils.getConnection();
        // 2: 通过connection对象生成PreparedStatement对象,此对象用来接收SQL语句
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            // 配置第1个?号指定参数
            pre.setString(1, product.getName());
            pre.setDouble(2, product.getPrice());
            pre.setString(3, product.getRemark());
            // 执行最后的SQL语句
            pre.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
