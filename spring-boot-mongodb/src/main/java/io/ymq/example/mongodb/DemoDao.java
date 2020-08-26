package io.ymq.example.mongodb;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
import java.util.List;

/**
 * 描述: 提供增删改查 MongoDB 接口
 *
 * @author yanpenglei
 * @create 2018-02-03 16:56
 **/
public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    List<DemoEntity> findDemo(Long id);

    DemoEntity findDemoById(Long id);

    long countDemo();

    Page<DemoEntity> findDemoByPage(int pagenum,int pageszie);

    GridFSInputFile saveFile(String file);

    GridFSDBFile findfilebyid(String id);
}
