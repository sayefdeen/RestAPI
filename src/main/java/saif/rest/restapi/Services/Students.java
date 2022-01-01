package saif.rest.restapi.Services;

import saif.rest.restapi.DataBase.ConnectionPool;

import at.favre.lib.crypto.bcrypt.BCrypt;
import saif.rest.restapi.DAO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Students implements CRUD{


    @Override
    public void add(Object object) throws Exception {
        Connection con = ConnectionPool.getConnection();
        String insertQuery = "INSERT INTO uni.users (uuid,email,password) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        ps.setString(1,((( saif.rest.restapi.DAO.Students) object).getId()));
        ps.setString(2,(( saif.rest.restapi.DAO.Students) object).getName());
        ps.setString(3,(( saif.rest.restapi.DAO.Students) object).getPassword());
        ps.execute();
        con.close();
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Object get(String id) {
        return new Object();
    }

    public  saif.rest.restapi.DAO.Students getUser(String userName) throws Exception{
        saif.rest.restapi.DAO.Students student = null;
        Connection con = ConnectionPool.getConnection();
        String selectQuery = "SELECT uuid,email,password FROM uni.users WHERE email = ? ";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,userName);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            student = new  saif.rest.restapi.DAO.Students();
            student.setId(rs.getString("uuid"));
            student.setName(rs.getString("email"));
            student.setPassword(rs.getString("password"));
        }
        return student;
    }

    public boolean checkPass(String reqPass, String dbPass){
        return BCrypt.verifyer().verify(reqPass.toCharArray(), dbPass).verified;
    }
}
