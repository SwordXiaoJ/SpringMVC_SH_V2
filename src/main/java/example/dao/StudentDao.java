package example.dao;



import example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@Repository
public class StudentDao {
    private DatabasePool databasePool;

    @Autowired
    public StudentDao(DatabasePool databasePool)
    {
        this.databasePool=databasePool;
    }


    public List<Student> selectAllStu()
    {
        String sqlString = "SELECT * FROM s_student";

        Connection conn= null;
        try {
            conn = databasePool.getHikariDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Student> list = new ArrayList<>();
        try
        {
            ps=conn.prepareStatement(sqlString);
            rs=ps.executeQuery(sqlString);
            // 获取执行结果
            while (rs.next()){
                Student stu = new Student();
                stu.setId(rs.getLong("student_id"));
                stu.setName(rs.getString("name"));
                stu.setCreateTime(rs.getTimestamp("create_time"));
                stu.setUpdateTime(rs.getTimestamp("update_time"));

                list.add(stu);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return list;

    }
    public void addStudent(Student student){



        Connection conn= null;
        try {
            conn = databasePool.getHikariDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into s_student values (?,?,?,?)";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setLong(1,student.getId());
            ps.setString(2,student.getName());
            ps.setTimestamp(3,student.getCreateTime());
            ps.setTimestamp(4,student.getUpdateTime());


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
