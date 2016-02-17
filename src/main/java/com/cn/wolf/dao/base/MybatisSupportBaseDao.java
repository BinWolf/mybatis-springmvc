package com.cn.wolf.dao.base;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wolf on 16/2/16.
 */
public class MybatisSupportBaseDao extends SqlSessionDaoSupport {
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    /*@Autowired
    protected SqlSessionManager sqlSessionManager;*/

    /**
     * 物理分页实现，使用pagehelper开源工程
     * @param statement
     * @param parameter
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean selectPagination(String statement,Object parameter,int pageNum,int pageSize){
        SqlSession sqlSession = getSqlSession();
        try{
            PageHelper.startPage(pageNum, pageSize);
            List<Object> list = sqlSession.selectList(statement, parameter);
            PageBean t =  new PageBean(list);
            return t;
        }finally {
            sqlSession.close();
        }
    }
}
