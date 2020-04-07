package example.service;

import example.dao.NeedHomeworkDao;
import example.pojo.NeedHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeedHomeworkService {

    private NeedHomeworkDao needHomeworkDao;
    @Autowired
    public NeedHomeworkService(NeedHomeworkDao needHomeworkDao)
    {
        this.needHomeworkDao=needHomeworkDao;
    }

    public List<NeedHomework> getAllNeed()
    {
        return needHomeworkDao.selectAllNeed();
    }

    public void AddNeed(NeedHomework needHomework)
    {
        needHomeworkDao.addNeedHomework(needHomework);
    }
}
