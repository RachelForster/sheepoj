package com.sheep.sheepoj.service;

import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Problem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProblemService {
    Problem findById(Long problem_id);
    PageInfo<Problem> findByPage(int page_num, int page_size,String word,String tag);
}
