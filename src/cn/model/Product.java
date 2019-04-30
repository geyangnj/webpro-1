package cn.model;

import java.util.Date;

//  1：类 --> 表     对象 --> 记录    类的属性 --> 表字段
public class Product {

    private int id;
    private String name;
    private double price;
    private String remark;
    private Date date;

    public static void main(String[] args) {
        Product p1 = new Product();
        p1.setName("AA");
        p1.setName("BB");
        System.out.print(p1.getName());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
