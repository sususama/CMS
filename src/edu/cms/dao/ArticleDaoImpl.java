package edu.xalead.cms.dao;

import edu.xalead.cms.entity.Article;
import edu.xalead.cms.tools.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public List<Article> findAllArticle(Connection coon) {
        List<Article> list=null;
        String sql="select * from t_article";
        PreparedStatement prst =null;
        ResultSet rs=null;
        try {
            prst= coon.prepareStatement(sql);
            rs=prst.executeQuery();
            list=DB.resultSet2ArticleList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
        return list;
    }

    @Override
    public void addArticle(Article article, Connection conn) {
        String sql="insert into t_article(aid,title,content,source,author,createTime,cid) values(?,?,?,?,?,?,?)";
        PreparedStatement prst=null;
        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,article.getAid());
            prst.setString(2,article.getTitle());
            prst.setString(3,article.getContent());
            prst.setString(4,article.getSource());
            prst.setString(5,article.getAuthor());
            prst.setDate(6,new Date(article.getCreateTime().getTime()));
            prst.setInt(7,article.getCid());
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
    }

    @Override
    public void deleteArticleByid(int aid, Connection conn) {
        String sql = "delete from t_article where aid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1,aid);

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
    }

    @Override
    public Article findArticleByid(int aid, Connection conn) {
        Article article = null;
        String sql = "select * from t_article where aid = ?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            if(rs.next()){
                article = new Article();
                article.setAid(rs.getInt("aid"));
                article.setAuthor(rs.getString("author"));
                article.setCid(rs.getInt("cid"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getDate("createTime"));
                article.setTitle(rs.getString("title"));
                article.setSource(rs.getString("source"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
        return article;
    }

    @Override
    public void updateArticle(Article article, Connection conn) {
        String sql = "update t_article set title = ? , content = ? , source = ? , " +
                "author = ? , createTime = ? , cid = ? where aid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement(sql);
            prst.setString(1,article.getTitle());
            prst.setString(2,article.getContent());
            prst.setString(3,article.getSource());
            prst.setString(4,article.getAuthor());
            prst.setDate(5,new Date(article.getCreateTime().getTime()));
            prst.setInt(6,article.getCid());
            prst.setInt(7,article.getAid());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
    }

    @Override
    public int findArticlecount(Connection conn) {
        int count = 0;
        String sql = "select count(*) from t_article";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
        return count;
    }

    @Override
    public List<Article> findPageArticle(int offset, int pagesize, Connection conn) {
        String sql = "select * from t_article limit ?,?";
        List<Article> list = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1,offset);
            prst.setInt(2,pagesize);
            rs = prst.executeQuery();
            while(rs.next()){
                if(list == null){
                    list = new ArrayList<>();
                }
                Article article = new Article();
                article.setAid(rs.getInt("aid"));
                article.setAuthor(rs.getString("author"));
                article.setCid(rs.getInt("cid"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getDate("createTime"));
                article.setTitle(rs.getString("title"));
                article.setSource(rs.getString("source"));
                list.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }

        return list;
    }
}
