package com.sheep.sheepoj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.dao.ProblemMapper;
import com.sheep.sheepoj.entity.Problem;
import com.sheep.sheepoj.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemMapper mapper;
    @Override
    public Problem findById(Long problem_id) {
        System.out.println(problem_id);
        return mapper.findById(problem_id);
    }

    @Override
    public PageInfo<Problem> findByPage(int page_num, int page_size,String word,String tag) {

        PageHelper.startPage(page_num,page_size);
        List<Problem> problemList;
        if(word==null&&tag==null)
            problemList=mapper.findAll();
        else if(tag==null)
            problemList=mapper.findByWord(word);
        else problemList=mapper.findByTag(tag);
        PageInfo<Problem> problemPageInfo=new PageInfo<>(problemList);
        return problemPageInfo;
    }
}
