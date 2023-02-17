package com.IT.service.edu.controller.admin;


import com.IT.common.base.result.R;
import com.IT.service.edu.entity.Teacher;
import com.IT.service.edu.entity.vo.TeacherQueryVo;
import com.IT.service.edu.feign.OssFileService;
import com.IT.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 小李
 * @since 2023-01-12
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;


    @GetMapping("/message1")
    public String message1() {
        return "message1";
    }

    @GetMapping("/message2")
    public String message2() {
        return "message2";
    }

    @ApiOperation("测试并发")
    @RequestMapping(value = "test_concurrent",method = RequestMethod.GET)
    public R testConcurrent(){
        log.info("test_concurrent");
        return R.ok();
    }

    @ApiOperation("测试服务调用")
    @GetMapping("test")
    public R test(){
        ossFileService.test();
        return R.ok();
    }

    @ApiOperation("根据左关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "查询关键字", required = true)
            @PathVariable String key){

        List<Map<String, Object>> nameList = teacherService.selectNameListByKey(key);

        return R.ok().data("nameList", nameList);
    }


    @ApiOperation("新增讲师列表")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public R save(@ApiParam(value = "讲师对象",required = true) @RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return R.ok().message("成功");
        }else {
            return R.error().message("失败");
        }
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "讲师对象") @RequestBody Teacher teacher){
        boolean byId = teacherService.updateById(teacher);
        if (byId){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师列表")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "讲师ID",required = true)@PathVariable String id){

        Teacher byId = teacherService.getById(id);
        if (byId!=null){
            return R.ok().data("item",byId);
        }else {
            return R.error().message("数据不存在");
        }
    }


    @GetMapping("list/{page}/{limit}")
    @ApiOperation("分页讲师列表")
    public R listPage(@ApiParam(value = "当前页",required = true)@PathVariable long page
                    , @ApiParam(value = "每页记录条数",required = true)@PathVariable long limit
                    , @ApiParam(value = "查询条件") TeacherQueryVo teacherQueryVo){
        Page<Teacher> page1 = new Page<>(page, limit);
        IPage<Teacher> teacherPage = teacherService.selectPage(page1,teacherQueryVo);
        List<Teacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("total",total).data("rws",records);
    }

    @ApiOperation(value = "查询",notes = "不分页查询所有数据")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list).message("获取讲师成功");
    }

    /**
     * 根据讲师ID 逻辑删除讲师
     * @param id 讲师ID
     * @return: 删除成功或数据不存在！
     * @since 2022/11/24 2:06
     * @author Kim
     */
    @ApiOperation(value = "删除讲师", notes = "根据讲师ID删除，逻辑删除")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public R removeById(@ApiParam(value = "讲师ID", required = true)@PathVariable String id) {

        // 1.删除讲师头像
        teacherService.removeAvatarById(id);

        // 2、删除讲师
        boolean result = teacherService.removeById(id);
        if (result){
            return R.ok().message("删除成功！");
        }else {
            return R.error().message("数据不存在！");
        }
    }

    @ApiOperation(value = "根据id进行删除")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value = "讲师列表",required = true)
            @RequestBody List<String> idList){
        boolean removeByIds = teacherService.removeByIds(idList);
            if (removeByIds){
                return R.ok().message("删除成功");
            } else {
                return R.error().message("删除失败");
            }

    }

}

