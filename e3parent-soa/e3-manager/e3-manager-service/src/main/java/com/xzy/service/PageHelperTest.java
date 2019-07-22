package com.xzy.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzy.mapper.TbItemMapper;
import com.xzy.pojo.TbItem;
import com.xzy.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

    public class PageHelperTest {
        @Test
    public void testPageHelper()throws Exception{
//        初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationConText*.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
//        从容器中获取Mapper对象
//         使用pagehelper中的startPage方法
        PageHelper.startPage(1,30);
//         执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
            PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);

            System.out.println(pageInfo.getTotal());
            System.out.println(pageInfo.getPageNum());
    }
}
