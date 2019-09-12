
package edu.xalead.cms.service;

import edu.xalead.cms.dao.ArticleDao;
import edu.xalead.cms.entity.Article;
import edu.xalead.cms.tools.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticleServiceImpl extends BaseService implements ArticleService {
    private ArticleDao articleDao;

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    @Override
    public List<Article> findPageArticlee(int offset, int pagesize) {
        List<Article> data=null;
        Connection conn=DB.getConn();
        try{
            data=articleDao.findPageArticle(offset,pagesize,conn);
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DB.close(conn);
        }
        return null;
    }

    @Override
    public int findArticleeCount() {
        int count = 0;
        Connection conn = DB.getConn();
        try {
//            dao
            count = articleDao.findArticlecount(conn);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(conn);
        }
        return count;
    }

    @Override
    public void updateArticlee(Article article) {
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            articleDao.updateArticle(article,coon);
            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
            }

        }
    }

    @Override
    public Article findArticleeByid(int cid) {
        Article article=null;
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            article= articleDao.findArticleByid(cid,coon);
            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
            }

        }
        return article;
    }

    @Override
    public void deleteBycid(int cid) {
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
            articleDao.deleteArticleByid(cid,coon);
            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
            }

        }
    }

    @Override
    public void addArticlee(Article article) {
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            articleDao.addArticle(article,coon);
            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
            }

        }
    }

    @Override
    public List<Article> findAllArticlee() {
        List<Article> list=null;
        Connection coon= DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            list= articleDao.findAllArticle(coon);

            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
                System.out.println();
            }
        }
        return list;
    }
}
