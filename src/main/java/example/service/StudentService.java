package example.service;

import example.dao.StudentDao;
import example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao)
    {
        this.studentDao=studentDao;
    }
    public List<Student> getAllStu()
    {
        return studentDao.selectAllStu();
    }

    public void AddStu(Student student)
    {
        studentDao.addStudent(student);
    }

}

