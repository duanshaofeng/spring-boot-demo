package io.ymq.example.mongodb;


import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by 86133 on 2020/8/24.
 */
public class MongoUtil<T> {



        private static final int DEFAULT_SKIP = 0;
        private static final int DEFAULT_LIMIT = 200;

        /**
         * spring mongodb　集成操作类
         */
        @Resource
        protected MongoTemplate mongoTemplate;

        protected String collection;

        /**
         * 通过条件查询实体(集合)
         *
         * @param query
         */
        public List<T> find(Query query) {
            return mongoTemplate.find(query, this.getEntityClass());
        }

        public List<T> find(Query query, String collectionName) {
            return mongoTemplate.find(query, this.getEntityClass(), collectionName);
        }
/*    public List<OrdersBo> findtest(Query query, String collectionName) {
        return mongoTemplate.find(query,OrdersBo.class,collectionName);
    }
*/

        /**
         * 通过一定的条件查询一个实体
         *
         * @param query
         * @return
         */
        public T findOne(Query query) {
            return mongoTemplate.findOne(query, this.getEntityClass());
        }

        public T findOne(Query query, String collectionName) {
            return mongoTemplate.findOne(query, this.getEntityClass(), collectionName);
        }

        /**
         * 通过条件查询更新数据
         *
         * @param query
         * @param update
         * @return
         */
        public void update(Query query, Update update) {
            mongoTemplate.findAndModify(query, update, this.getEntityClass());
        }

        public void update(Query query, Update update, String collectionName) {
            mongoTemplate.findAndModify(query, update, this.getEntityClass(), collectionName);
        }

        public void removeById(String id, String collectionName) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(id));
            mongoTemplate.remove(query, collectionName);
        }

        /**
         * 保存一个对象到mongodb
         *
         * @param entity
         * @return
         */
        public T save(T entity) {
            mongoTemplate.insert(entity);
            return entity;
        }

        public T save(T entity, String collectionName) {
            mongoTemplate.insert(entity, collectionName);
            return entity;
        }

        /**
         * 通过ID获取记录
         *
         * @param id
         * @return
         */
        public T findById(String id) {
            return mongoTemplate.findById(id, this.getEntityClass());
        }

        /**
         * 通过ID获取记录,并且指定了集合名(表的意思)
         *
         * @param id
         * @param collectionName 集合名
         * @return
         */
        public T findById(String id, String collectionName) {
            return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
        }

        /**
         * 分页查询
         *
         * @param page
         * @param query
         * @return
         */
        public Page<T> findPage(Page<T> page, Query query) {
            long count = this.count(query);
            page.setTotal(count);
            int pageNumber = page.getPagenumber();
            int pageSize = page.getPageSize();
            query.skip((pageNumber - 1) * pageSize).limit(pageSize);
            List<T> rows = this.find(query);
            page.setRows(rows);
            return page;
        }

        public Page<T> findPage(Page<T> page, Query query, String collectionName) {
            long count = this.count(query, collectionName);
            page.setTotal(count);
            int pageNumber = page.getPagenumber();
            int pageSize = page.getPageSize();
            query.skip((pageNumber - 1) * pageSize).limit(pageSize);
            List<T> rows = this.find(query, collectionName);
            page.setRows(rows);
            return page;
        }

        /**
         * 求数据总和
         *
         * @param query
         * @return
         */
        public long count(Query query) {
            return mongoTemplate.count(query, this.getEntityClass());
        }

        public long count(Query query, String collectionName) {
            return mongoTemplate.count(query, this.getEntityClass(), collectionName);
        }


        /**
         * 获取需要操作的实体类class
         *
         * @return
         */
        public Class<T> getEntityClass() {
            //Type superclass = this.getClass().getGenericSuperclass();
            //Type[] actualTypeArguments = ((ParameterizedType)superclass).getActualTypeArguments();
            // return (Class) actualTypeArguments[0];
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return tClass;
        }

        /**
         * 　　* @description: 插入一个文件
         * 　　* @param ${tags}
         * 　　* @return ${return_type}
         * 　　* @throws
         * 　　* @author lizz
         * 　　* @date 2019/7/17 19:03
         */
        public GridFSInputFile save(MultipartFile file) {

            GridFS gridFS = new GridFS(mongoTemplate.getDb());

            try {

                InputStream in = file.getInputStream();

                String name = file.getOriginalFilename();

                GridFSInputFile gridFSInputFile = gridFS.createFile(in);

                gridFSInputFile.setFilename(name);

                gridFSInputFile.setContentType(file.getContentType());

                gridFSInputFile.save();
                return gridFSInputFile;
            } catch (Exception e) {
            }

            return null;

        }

        /* /**
        　　* @description: 根据id获取文件
        　　* @param ${tags}
        　　* @return ${return_type}
        　　* @throws
        　　* @author lizz
        　　* @date 2019/7/18 21:40
        　　*/
        public GridFSDBFile getGridFSById(String id) {

            GridFS gridFS = new GridFS(mongoTemplate.getDb());

            return gridFS.findOne(new BasicDBObject("_id", new ObjectId(id)));

        }

        //聚合查询
        public <T> List<T> findAggregateList(Aggregation aggregation, String collectionName, Class<T> clazz) {
            AggregationResults<T> aggregate = mongoTemplate.aggregate(aggregation, collectionName, clazz);
            List<T> aggregateList = aggregate.getMappedResults();
            return aggregateList;
        }

        /**
         * 　　* @description: 重复key为条件时的query
         * 　　* @param ${tags}
         * 　　* @return ${return_type}
         * 　　* @throws
         * 　　* @author lizz
         * 　　* @date 2019/7/31 14:25
         */
        public Query CreateRepkeyQuery(List<Criteria> criterias) {
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria[] CriteriaArr = criterias.toArray(new Criteria[]{});
            criteria.andOperator(CriteriaArr);
            query.addCriteria(criteria);
            return query;

        }

}
