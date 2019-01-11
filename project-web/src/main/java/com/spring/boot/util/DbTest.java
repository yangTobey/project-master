package com.spring.boot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DbTest {
    public static Connection getConnection(){
        String driver="com.mysql.jdbc.Driver";  //获取mysql数据库的驱动类
        String url="jdbc:mysql://192.168.20.2/property"; //springboot-mybatis连接数据库（kucun是数据库名）
        String name="root";//连接mysql的用户名
        String pwd="root123";//连接mysql的密码
        try{
            Class.forName(driver);
            Connection conn=DriverManager.getConnection(url,name,pwd);//获取连接对象
            return conn;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(ps!=null){
                ps.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException
    {
        long sys1=System.currentTimeMillis();
        Connection cc=DbTest.getConnection();
        long sys2=System.currentTimeMillis();
        System.out.println(sys2-sys1);
        if(!cc.isClosed()){
            System.out.println("Succeeded connecting to the Database!");
        }

        Statement statement = cc.createStatement();
        long sys3=System.currentTimeMillis();
        String sql = "SELECT\n" +
                "\t\t\tcompany.cityName,company.longitude,company.latitude,re.community_id as communityId,re.`status`,\n" +
                "\t\t\tREPLACE (re.user_name,SUBSTR(re.user_name, 2),'**') as userName,company.id as cityId,co.`name` as communityName,\n" +
                "\t\t\tCASE re.level\n" +
                "\t\t\tWHEN 0 THEN\n" +
                "\t\t\t\t'报修'\n" +
                "\t\t\tWHEN 1 THEN\n" +
                "\t\t\t\t'投诉'\n" +
                "\t\t\tEND AS levelName\n" +
                "\t\tFROM\n" +
                "\t\t\t`repair` AS re\n" +
                "\t\tLEFT JOIN community AS co ON re.community_id = co.community_id\n" +
                "\t\tLEFT JOIN worker_branchCommunity AS wocom ON wocom.community = co.community_id\n" +
                "\t\tLEFT JOIN worker_branchCompany AS company ON company.id = wocom.branchCompanyId\n" +
                "\t\tWHERE re.`status`!=-1 and re.`status`!=3 and wocom.type=1 and company.`status`!=2\n" +
                "\t\tORDER BY re.commit_time DESC LIMIT 0, 30";
        ResultSet rs = statement.executeQuery(sql);
        long sys4=System.currentTimeMillis();
        while(rs.next()) {
            System.out.println("AA:"+(sys4-sys3));
        }
    }
}
