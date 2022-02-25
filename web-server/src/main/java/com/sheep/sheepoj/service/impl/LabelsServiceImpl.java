package com.sheep.sheepoj.service.impl;

import com.sheep.sheepoj.dao.LabelsMapper;
import com.sheep.sheepoj.service.LabelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelsServiceImpl implements LabelsService {

    @Resource
    private LabelsMapper labelsMapper;
    @Override
    public List<String> findByProblemId(Long problemId) {
        return labelsMapper.findById(problemId);
    }
}
