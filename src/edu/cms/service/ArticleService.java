package edu.xalead.cms.service;

import edu.xalead.cms.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> findPageArticlee(int offset, int pagesize);
    public int findArticleeCount();
    public  void  updateArticlee(Article article);
    public Article findArticleeByid(int cid);
    public void deleteBycid(int cid);
    public void addArticlee(Article article);
    public List<Article> findAllArticlee();
}
