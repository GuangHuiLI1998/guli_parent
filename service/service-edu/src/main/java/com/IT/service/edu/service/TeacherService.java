package com.IT.service.edu.service;

import com.IT.service.edu.entity.Teacher;
import com.IT.service.edu.entity.vo.TeacherQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 小李
 * @since 2023-01-12
 */
public interface TeacherService extends IService<Teacher> {


    IPage<Teacher> selectPage(Page<Teacher> page1, TeacherQueryVo teacherQueryVo);

    List<Map<String, Object>> selectNameListByKey(String key);

    boolean removeAvatarById(String id);
}
