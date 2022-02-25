package com.sheep.sheepoj.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LabelsService {
    List<String> findByProblemId(Long problemId);
}
