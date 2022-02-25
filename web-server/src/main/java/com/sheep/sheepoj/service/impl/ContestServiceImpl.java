package com.sheep.sheepoj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.dao.ContestMapper;
import com.sheep.sheepoj.entity.Contest;
import com.sheep.sheepoj.entity.Problem;
import com.sheep.sheepoj.service.ContestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {

    @Resource
    private ContestMapper contestMapper;


    @Override
    public PageInfo<Contest> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Contest> contestList=contestMapper.findAll("start");
        PageInfo<Contest> pageInfo=new PageInfo<>(contestList);
        return  pageInfo;
    }

    @Override
    public Contest findById(Long id) {
        return contestMapper.findById(id);
    }

    @Override
    public List<Problem> findProblemList(Long id) {
        return contestMapper.findProblemList(id);
    }
}
