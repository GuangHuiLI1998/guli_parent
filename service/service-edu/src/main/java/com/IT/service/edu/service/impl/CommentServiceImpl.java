package com.IT.service.edu.service.impl;

import com.IT.service.edu.entity.Comment;
import com.IT.service.edu.mapper.CommentMapper;
import com.IT.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 小李
 * @since 2023-01-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
