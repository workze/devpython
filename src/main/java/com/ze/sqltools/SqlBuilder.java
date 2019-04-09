package com.ze.sqltools;

import netscape.javascript.JSObject;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by ZE-C on 2017/9/3.
 */
public class SqlBuilder {

    private String table = "";
    private HashSet<String> whereKeys = null;
    private HashMap<String, Object> map = null;

    private String appendField = "";

    private HashSet<String> filters = null;

    private boolean allowNull = false;
    private String sqlType = "";

    private String selectWhat = "";
    private String whereWhat = "";

    private Connection connection = null;

    private void init() {
        whereKeys = new HashSet<String>();
        filters = new HashSet<String>();
    }

    private void initWithMap(Object object) throws IllegalAccessException {
        whereKeys = new HashSet<String>();
        filters = new HashSet<String>();
        map = parseFields(object);
    }

    public SqlBuilder() {

    }

    public SqlBuilder(Connection conn) {
        this.connection = conn;
    }

    private HashMap<String, Object> parseFields(Object obj) throws IllegalAccessException {
        map = new HashMap<String, Object>();
        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            map.put(f.getName(), f.get(obj));
        }
        return map;
    }

    public SqlBuilder select(String para, String... paras) {
        this.init();
        this.sqlType = "query";
        StringBuffer sb = new StringBuffer();
        sb.append(para);
        for (String p : paras) {
            sb.append(", ").append(p);
        }
        this.selectWhat = sb.toString();
        return this;
    }

    public SqlBuilder from(String table) {
        this.table = table;
        return this;
    }

    public SqlBuilder update(String table) throws IllegalAccessException {
        this.table = table;
        this.sqlType = "update";
        return this;
    }

    public SqlBuilder with(Object object) throws IllegalAccessException {
        this.initWithMap(object);
        return this;
    }

    public SqlBuilder insert(String table) throws IllegalAccessException {
        this.table = table;
        this.sqlType = "insert";
        return this;
    }

    public SqlBuilder into(String table) {
        this.table = table;
        return this;
    }

    public SqlBuilder delete() {
        this.init();
        this.sqlType = "delete";
        return this;
    }

    public SqlBuilder procedure() {
        this.sqlType = "procedure";
        return this;
    }

    public SqlBuilder table(String table) {
        this.table = table;
        return this;
    }

    public SqlBuilder filter(final String[] filters) {
//        for (String key : filters) {
//            this.filters.add(key);
//        }
        this.filters.addAll(Arrays.asList(filters));
        return this;
    }

    public SqlBuilder allowNull() {
        this.allowNull = true;
        return this;
    }

    public SqlBuilder clear() {
        this.map.clear();
        return this;
    }

    public SqlBuilder append(HashMap<String, Object> fields) {
        for (HashMap.Entry<String, Object> entry : fields.entrySet()) {
            this.map.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public SqlBuilder append(String key,Object value){
        this.map.put(key,value);
        return this;
    }

    public SqlBuilder where(String[] keys) {
        this.whereKeys = new HashSet<String>(Arrays.asList(keys));
        return this;
    }

    public SqlBuilder where(String conditions) {
        this.whereWhat = conditions;
        return this;
    }

    private String toQueryString() {
        StringBuffer sb = new StringBuffer();
        sb.append("select ").append(selectWhat).append(" from ").append(table);
        sb.append(" where ").append(whereWhat).append(";");
        return sb.toString();
    }

    private String toDeleteString() {
        StringBuffer sb = new StringBuffer();
        sb.append("delete from ").append(table);
        sb.append(" where ").append(whereWhat).append(";");
        return sb.toString();
    }

    private HashMap<String, Object> filterNull(HashMap<String, Object> map) {
        Iterator<HashMap.Entry<String, Object>> it = map.entrySet().iterator();
        if (!allowNull) {
            while (it.hasNext()){
                HashMap.Entry<String, Object> entry = it.next();
                if (entry.getValue() == null) {
                    it.remove();
                }
            }
        }
        return map;
    }

    private String formValue(Object obj){
        StringBuffer sb =new StringBuffer();
        if( obj == null ){
            return "null";
        }
        if( obj.getClass().getSimpleName().equals("String") ){
            return sb.append("'").append(obj).append("'").toString();
        }else{
            return sb.append(obj).toString();
        }
    }

    private String parseUpdateMap(HashMap<String, Object> map) {
        map = filterNull(map);
        // 带上null的判断 ,带上filter
        StringBuffer sb = new StringBuffer();
        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (!filters.contains(entry.getKey())) {
                sb.append(entry.getKey()).append("=").append( formValue(entry.getValue())).append(", ");
            }
        }
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }

    private String parseInsertMap(HashMap<String, Object> map) {
        map = filterNull(map);
        // 带上null的判断 ,带上filter
        StringBuffer sb1 = new StringBuffer(),sb2 = new StringBuffer();
        sb1.append("(");
        sb2.append("(");
        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (!filters.contains(entry.getKey())) {
                sb1.append(entry.getKey()).append(",");
                sb2.append(formValue(entry.getValue())).append(",");
            }
        }
        sb1.deleteCharAt(sb1.length()-1);
        sb2.deleteCharAt(sb2.length()-1);
        sb1.append(")");
        sb2.append(")");
        return sb1.append(" values ").append(sb2).toString();
    }

    private String toUpdateString() {
        StringBuffer sb = new StringBuffer();
        sb.append("update ").append(table).append(" set ");
        sb.append(parseUpdateMap(map)).append(" ");
        sb.append(appendField);
        sb.append(" where ").append(whereWhat).append(";");
        return sb.toString();
    }

    private String toInsertString(){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(table).append(" ");
        sb.append(parseInsertMap(map)).append(";");
        sb.append(appendField);
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.sqlType.equals("query")) {
            return toQueryString();
        } else if (this.sqlType.equals("delete")) {
            return toDeleteString();
        } else if (this.sqlType.equals("update")) {
            return toUpdateString();
        }else if(this.sqlType.equals("insert")){
            return toInsertString();
        }
        else {
            return "err sql type";
        }
    }

    public int executeUpdate() throws SQLException {
        String sql = this.toString();
        Statement statement = this.connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public ResultSet executeQuery() throws SQLException {
        String sql = this.toString();
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Book book = new Book();
        book.setName("java");
        book.setPrice(10);
        String selsql = new SqlBuilder().select("name").from("person").where("id=1").toString();
        String delsql = new SqlBuilder().delete().from("person").where("id=1").toString();
        String upsql = new SqlBuilder().update("person").with(book).where("id=1").toString();
        String inssql = new SqlBuilder().insert("person").with(book).allowNull().filter(new String[]{"author"})
                .append("resstatus",0).where("id=1").toString();
        System.out.println(selsql);
        System.out.println(delsql);
        System.out.println(upsql);
        System.out.println(inssql);
    }
}
