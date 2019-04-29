package cn.utils;
import java.sql.Connection;
import java.sql.DriverManager;
// 此类用来完成数据库连接操作
public class JdbcUtils {

    public static void main(String[] args) {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
    // 静态块, 当前类(class文件)被加载的JVM中才会被执行,且执行一次
    static{
        System.out.println("在静态块中加载数据库的SQL语句");
        // A --> B  ---> C
        try{
            // 如果有运行时错误,Java会提示try来捕获
            // 1: 加载数据库驱动文件
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            // 抛出此异常,谁调用此方法,异常就抛给谁
            throw new RuntimeException(e);
        }
    }

    // 3: 返回一个连接对象(Connection)
    public static Connection getConnection(){
        System.out.println("getConnection...............");
        // 加载驱动的Driver类(mysql驱动的入口)
        try {
            // 驱动管理类,通过此类设置账号,密码,url地址并且返回connection对象
            Connection conn =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo",
                    "root","root");
            return conn;
        } catch (Exception e) {
            // 显示错误信息
            throw new RuntimeException(e);
        }
    }
}
