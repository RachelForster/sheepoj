package com.sheep.sheepoj.service;

import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Contest;
import com.sheep.sheepoj.entity.Problem;

import java.util.List;

public interface ContestService{
    PageInfo<Contest> findByPage(Integer page, Integer limit);
    Contest findById(Long id);
    List<Problem> findProblemList(Long id);
}
