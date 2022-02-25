package com.sheep.sheepoj.dao;

import com.sheep.sheepoj.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProblemMapper{
    List<Problem> findAll();
    Problem findById(Long problem_id);
    List<Problem> findByWord(String word);
    List<Problem> findByTag(String tag);
}
