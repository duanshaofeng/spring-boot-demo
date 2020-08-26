package io.ymq.example.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 描述: 单元测试
 *
 * @author: yanpenglei
 * @create: 2018/2/5 13:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongodbApplication.class)
public class SpringBootMongodbApplicationTests {

    @Autowired
    private DemoDao demoDao;

    @Test
    public void saveDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(5L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);

        demoEntity = new DemoEntity();
        demoEntity.setId(6L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(7L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(8L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(9L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(10L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(11L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(12L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
        demoDao.saveDemo(demoEntity);
        demoEntity = new DemoEntity();
        demoEntity.setId(13L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.saveDemo(demoEntity);
    }

    @Test
    public void removeDemoTest() {
        demoDao.removeDemo(2L);
    }

    @Test
    public void updateDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(2L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        demoDao.updateDemo(demoEntity);
    }

    @Test
    public void findDemoByIdTest() {

        DemoEntity demoEntity = demoDao.findDemoById(2L);

        System.out.println(JSONObject.toJSONString(demoEntity));
    }


    @Test
    public void findDemoTest() {

        List<DemoEntity> demoEntity = demoDao.findDemo(2L);

        System.out.println(JSONObject.toJSONString(demoEntity));
    }

    @Test
    public void countDemoTest() {

        long demoEntity = demoDao.countDemo();

        System.out.println(JSONObject.toJSONString(demoEntity));
    }

    @Test
    public void findDemoByPageTest() {

        Page<DemoEntity> demoByPage = demoDao.findDemoByPage(1, 5);
        System.out.println(JSONObject.toJSONString(demoByPage));
    }


    @Test
    public void saveFileTest() {

        GridFSInputFile file  = demoDao.saveFile("D:\\123.jpg");
        System.out.println(file.getId());
    }
    @Test
    public void findfilebyidTest() {

        GridFSDBFile findfilebyid = demoDao.findfilebyid("5f44cb62ca566242bce89649");
        System.out.println(JSONObject.toJSONString(findfilebyid));
    }
}
