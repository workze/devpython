package com.ze.home.gendao;

import com.alibaba.fastjson.JSONObject;
import com.ze.home.gendao.model.Column;
import com.ze.home.gendao.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataSource {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "root123";

    public static List<Table> getTables(){
        List<Table> tables = new ArrayList<>();
        Table table = new Table();
        table.setName("customer");
        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setDesc("people age");
        column.setName("age");
        column.setType("int");
        columns.add(column);
        table.setColumns(columns);
        tables.add(table);
        return tables;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
              Statement  stmt = conn.createStatement()){

            String sql = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            List<JSONObject> columns =new LinkedList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                JSONObject col = new JSONObject();
                col.put("name",metaData.getColumnName(i));
                col.put("type",metaData.getColumnType(i));
                col.put("label",metaData.getColumnLabel(i));
                col.put("className",metaData.getColumnClassName(i));
                col.put("size",metaData.getColumnDisplaySize(i));
                columns.add(col);
            }
            System.out.printf("");
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye!");

    }
}
