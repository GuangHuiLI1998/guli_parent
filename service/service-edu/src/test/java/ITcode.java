import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/1/12 21:08
 */

public class ITcode {
    public static void main(String[] args) {
        //构建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+"/service/service_cms";
        gc.setOutputDir(projectPath + "/src/main/java");//生成文件的输出目录
        gc.setAuthor("小李");//作者
        gc.setOpen(false);//是否打开输出目录
        gc.setFileOverride(false);//是否覆盖已有的文件
        gc.setServiceName("%sService");//去除Service的I前缀
        gc.setIdType(IdType.ASSIGN_ID);//主键生成策略
        //ONLY_DATE 只使用 java.util.date 代替，SQL_PACK 使用 java.sql 包下的，TIME_PACK 使用 java.time 包下的 java8 新的时间类型
        gc.setDateType(DateType.TIME_PACK);//数据库时间类型 到 实体类时间类型 对应策略
        gc.setSwagger2(true);//开启swagger2模式
        mpg.setGlobalConfig(gc);

        //2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/video_cms?useSSl=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);//数据库类型
        mpg.setDataSource(dsc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("service_cms");//父包模块名
        pc.setParent("com.IT");//父包名,如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig sy = new StrategyConfig();
        sy.setInclude("cms_ad","cms_ad_type");//设置要映射的表，可以设置多张
        sy.setNaming(NamingStrategy.underline_to_camel);//从数据库表到文件的命名策略,下划线转驼峰命名
        sy.setColumnNaming(NamingStrategy.underline_to_camel);//列的命名策略
        sy.setEntityLombokModel(true);//开启lombok支持
        sy.setLogicDeleteFieldName("deleted");//设置逻辑删除字段
        sy.setVersionFieldName("version");//设置乐观锁
        sy.setRestControllerStyle(true);//开启controller的restful命名
        sy.setControllerMappingHyphenStyle(true);//开启controller中请求映射的连字符样式，如：localhost:8080/hello_id_1
        //设置自动填充
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create_time);
        tableFills.add(update_time);
        mpg.setStrategy(sy);

        //执行代码生成器
        mpg.execute();
    }


}
