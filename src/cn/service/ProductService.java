package cn.service;

import cn.dao.ProductDao;
import cn.model.Product;

import java.util.ArrayList;

// ProductService ---> ProductDao -->  JdbcUtils ----> db数据库
public class ProductService {

    private ProductDao productDao = new ProductDao();

    // 编写一个方法调用Dao完成数据库的入库操作
    public void save(Product product){
        // 以后会添加业务逻辑代码,处理完毕之后再调用数据访问层
        productDao.save(product);
    }

    public ArrayList<Product> queryByName(String name) {
        //  把查询的关键字分词，就属于业务逻辑
        return productDao.queryByName(name);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
