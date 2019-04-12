package com.ze.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by ZE-C on 2017/12/9.
 */
public class SqliteClient {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:F:/oschina/intellijU/java/resource/devhelper.sqlite");
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table person (id integer, name string)");
        } catch (Exception e) {
            ;
        }
    }
}
