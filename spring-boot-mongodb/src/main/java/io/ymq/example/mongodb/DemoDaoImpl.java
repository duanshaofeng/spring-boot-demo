package io.ymq.example.mongodb;


import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 描述: Demo DAO 实现
 *
 * @author yanpenglei
 * @create 2018-02-03 16:57
 **/
@Component
public class DemoDaoImpl extends MongoUtil<DemoEntity> implements DemoDao  {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(DemoEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeDemo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(DemoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, DemoEntity.class);
    }

    @Override
    public List<DemoEntity> findDemo(Long id) {
        Query query = new Query();
        List<DemoEntity> demoEntities = this.find(query);
        return demoEntities;
    }

    @Override
    public DemoEntity findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        DemoEntity demoEntities = this.findOne(query);
        return demoEntities;
    }

    @Override
    public long countDemo() {
        return this.count(new Query());
    }

    @Override
    public Page<DemoEntity> findDemoByPage(int pagenum,int pageszie) {
        Page<DemoEntity> page = new Page<>();
        page.setPagenumber(pagenum);
        page.setPageSize(pageszie);
        Query query = new Query(Criteria.where("by").is("souyunku"));
        Page<DemoEntity> page1 = this.findPage(page, query);
        return page1;
    }

    @Override
    public GridFSInputFile saveFile(String file) {
        return this.save(getMulFileByPath(file));
    }

    @Override
    public GridFSDBFile findfilebyid(String id) {
        return this.getGridFSById(id);
    }


    private static MultipartFile getMulFileByPath(String picPath) {
        FileItem fileItem = createFileItem(picPath);
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    private static FileItem createFileItem(String filePath)
    {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem("","",true,"");
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try
        {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1)
            {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return item;
    }
}
