package com.IT.service.edu.service.impl;

import com.IT.common.base.result.R;
import com.IT.service.edu.entity.Teacher;
import com.IT.service.edu.entity.vo.TeacherQueryVo;
import com.IT.service.edu.feign.OssFileService;
import com.IT.service.edu.mapper.TeacherMapper;
import com.IT.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 小李
 * @since 2023-01-12
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Autowired
    private OssFileService ossFileService;

    @Override
    public IPage<Teacher> selectPage(Page<Teacher> page1, TeacherQueryVo teacherQueryVo) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQueryVo == null){
            return baseMapper.selectPage(page1, queryWrapper);
        }

        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getJoinDateBegin();
        String end = teacherQueryVo.getJoinDateEnd();

        //讲师姓名不为空
        if (!StringUtils.isEmpty(name)) {
            //左%会使索引失效
            queryWrapper.likeRight("name", name);
        }
        //判断1是讲师2是主席   eq ==于
        if (level != null) {
            queryWrapper.eq("level", level);
        }

        //ge查询大于等于
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("join_date", begin);
        }
        //le小于等于
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("join_date", end);
        }

        return baseMapper.selectPage(page1, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameListByKey(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name", key);

        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);//返回值是Map列表
        return list;
    }

    @Override
    public boolean removeAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if (teacher !=null){
            String avatar = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)){
                //删除图片
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();

            }
        }
        return false;
    }


}
