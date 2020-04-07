package example.dao;





import example.pojo.NeedHomework;
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
public class NeedHomeworkDao {
    private  DatabasePool databasePool;
    @Autowired
    public NeedHomeworkDao(DatabasePool databasePool)
    {
        this.databasePool=databasePool;
    }

    public void addNeedHomework(NeedHomework NeedHomework){
        Connection conn= null;
        try {
            conn = databasePool.getHikariDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into s_homework values (?,?,?,?,?)";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setLong(1,NeedHomework.getId());
            ps.setString(2,NeedHomework.getTitle());
            ps.setString(3,NeedHomework.getContent());
            ps.setTimestamp(4,NeedHomework.getCreateTime());
            ps.setTimestamp(5,NeedHomework.getUpdateTime());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NeedHomework> selectAllNeed()
    {



        String sqlString = "SELECT * FROM s_homework";

        Connection conn= null;
        try {
            conn = databasePool.getHikariDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<NeedHomework> list = new ArrayList<>();
        try
        {
            ps=conn.prepareStatement(sqlString);
            rs=ps.executeQuery(sqlString);
            // 获取执行结果
            while (rs.next()){
                NeedHomework sh = new NeedHomework ();

                sh.setId(rs.getLong("homework_id"));
                sh.setTitle(rs.getString("title"));
                sh.setContent(rs.getString("content"));
                sh.setCreateTime(rs.getTimestamp("create_time"));
                sh.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(sh);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return list;

    }
}
