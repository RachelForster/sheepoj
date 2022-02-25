package com.sheep.sheepoj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Comment;
import com.sheep.sheepoj.entity.Discuss;

import java.util.List;

public interface DiscussService {
    Discuss findById(Long id);
    PageInfo<Discuss> findByPage(Integer page, Integer limit, String order);
    void putOne(String title,String content);
    List<Comment> findCommentsByDisscussId(Long id);
    void putLike(Long id);
}
