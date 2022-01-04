package saif.rest.restapi.Services;

import saif.rest.restapi.DAO.Students;
import saif.rest.restapi.DataBase.ConnectionPool;

import at.favre.lib.crypto.bcrypt.BCrypt;
import saif.rest.restapi.Log.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class StudentsService implements CRUD{


    @Override
    public boolean add(Object object)  {
        try{
            if(checkUser(((Students) object).getName()) == 0) {
                Connection con = ConnectionPool.getConnection();
                String insertQuery = "INSERT INTO uni.users (uuid,email,password) VALUES (?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1,(((Students) object).getId()));
                ps.setString(2,((Students) object).getName());
                ps.setString(3,((Students) object).getPassword());
                ps.execute();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
            return false;
        }
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

    public Students getUser(String userName) throws Exception{
        saif.rest.restapi.DAO.Students student = null;
        Connection con = ConnectionPool.getConnection();
        String selectQuery = "SELECT uuid,email,password FROM uni.users WHERE email = ? ";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,userName);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            student = new  Students();
            student.setId(rs.getString("uuid"));
            student.setName(rs.getString("email"));
            student.setPassword(rs.getString("password"));
        }
        return student;
    }

    private int checkUser(String email) throws Exception{
        int result = 0;
        Connection con = ConnectionPool.getConnection();
        String selectQuery = "SELECT EXISTS(SELECT email FROM uni.users WHERE email = ?) as truth;";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,email);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
           result = rs.getInt("truth");
        }
        System.out.println(result);
        return result;
    }

    public boolean checkPass(String reqPass, String dbPass){
        return BCrypt.verifyer().verify(reqPass.toCharArray(), dbPass).verified;
    }
}
