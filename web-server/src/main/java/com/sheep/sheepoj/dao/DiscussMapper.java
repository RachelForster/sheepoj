package com.sheep.sheepoj.dao;

import com.sheep.sheepoj.entity.Comment;
import com.sheep.sheepoj.entity.Discuss;

import java.util.List;

public interface DiscussMapper {

    Discuss findById(Long id);
    List<Discuss> findAll(String order);
    void putOne(Discuss discuss);
    List<Comment> findCommentsByDisscussId(Long id);
}
