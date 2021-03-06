package saif.rest.restapi.Services;



import saif.rest.restapi.DataBase.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseService implements CRUD{


    @Override
    public boolean add(Object object) {
        return false;
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Object get(String id) throws Exception {

        saif.rest.restapi.DAO.Course course = null;
        Connection con = ConnectionPool.getConnection();
        String selectQuery = "select uuid,name,section,SUM(uni.stu_cou.`result`) as sum,avg(uni.stu_cou.`result`) as avg,min(uni.stu_cou.`result`) as min,max(uni.stu_cou.`result`) as max from uni.courses inner join uni.stu_cou on uni.courses.id  = uni.stu_cou.c_id where uni.courses.uuid  = ?";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            course = new saif.rest.restapi.DAO.Course();
            course.setId(rs.getString("uuid"));
            course.setName(rs.getString("name"));
            course.setSection(rs.getString("section"));
            course.setSum(rs.getDouble("sum"));
            course.setAvg(rs.getDouble("avg"));
            course.setMax(rs.getDouble("max"));
            course.setMin(rs.getDouble("min"));

        }
        return course;
    }

    public ArrayList<saif.rest.restapi.DAO.Course> getCourses(String userId) throws Exception{
        ArrayList<saif.rest.restapi.DAO.Course> courses = new ArrayList<>();

        Connection con  = ConnectionPool.getConnection();
        String selectQuery = "select uuid,name,section,result from uni.courses inner join uni.stu_cou on uni.courses.id = uni.stu_cou.c_id where uni.stu_cou.s_id = (select uni.students.id from uni.students inner join uni.users on uni.students.u_id = uni.users.id where uni.users.uuid = ? )";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,userId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            saif.rest.restapi.DAO.Course course = new saif.rest.restapi.DAO.Course();
            course.setId(rs.getString("uuid"));
            course.setName(rs.getString("name"));
            course.setSection(rs.getString("section"));
            course.setResult(rs.getInt("result"));
            courses.add(course);
        }
        return courses;
    }

}
