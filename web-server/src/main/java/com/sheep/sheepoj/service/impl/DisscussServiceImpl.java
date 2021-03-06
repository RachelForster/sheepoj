package com.sheep.sheepoj.service.impl;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.dao.DiscussMapper;
import com.sheep.sheepoj.entity.Comment;
import com.sheep.sheepoj.entity.Discuss;
import com.sheep.sheepoj.service.DiscussService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DisscussServiceImpl implements DiscussService {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    DiscussMapper discussMapper;
    @Override
    public Discuss findById(Long id) {
        return discussMapper.findById(id);
    }

    @Override
    public PageInfo<Discuss> findByPage(Integer page,Integer limit,String order) {
        PageHelper.startPage(page,limit);
        if(order!=null&&order.isEmpty())
            order=null;
        List<Discuss> discusses=discussMapper.findAll(order);
        String userName= ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        for(Discuss discuss:discusses)
        {
            String key="discuss_like_"+discuss.getDiscussId();
            Long size=redisTemplate.opsForSet().size(key);
            discuss.setLikeNum(size);
            //System.out.println(size);
            if(redisTemplate.opsForHash().hasKey("user_like_discuss",userName+"_"+discuss.getDiscussId()))
            {
                discuss.setIsLiked(true);
            }
            else
                discuss.setIsLiked(false);
            //System.out.println(discuss.getIsLiked());
            redisTemplate.opsForSet().size(key);
        }
        return new PageInfo<Discuss> (discusses);
    }

    /**
     * @description ??????????????????
     * @param title: ??????
     * @param content:  ??????
     * @return void
     * @author Xueyang
     * @mood ??????????????????
     * @date 2022/2/25 10:23
     */
    @Override
    public void putOne(String title,String content) {

        Discuss discuss = new Discuss();
        discuss.setContent(content);
        discuss.setTitle(title);
        discuss.setLikeNum(0);
        discuss.setDiscussNum(0);
        discuss.setPublisherId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        discussMapper.putOne(discuss);
    }
    @Override
    public List<Comment> findCommentsByDisscussId(Long id) {
        return discussMapper.findCommentsByDisscussId(id);
    }

    /**
     * @description ????????????????????????????????????
     * @param id:  ?????????id
     * @return void
     * @author Xueyang
     * @mood ??????????????????
     * @date 2022/2/25 10:18
     */
    @Override
    public void putLike(Long id) {
        //????????????????????????Id
        String userName= ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //???????????????id ?????????????????????????????????set discuss_#{id},????????????,???????????????
        String key="discuss_like_"+id;
        redisTemplate.opsForSet().add(key,userName);
        redisTemplate.opsForHash().put("user_like_discuss",userName+"_"+id,"1");
        //System.out.println(redisTemplate.opsForSet().size(key));
    }
}
