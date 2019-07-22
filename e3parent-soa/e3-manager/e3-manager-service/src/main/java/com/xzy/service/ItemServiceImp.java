package com.xzy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzy.mapper.TbItemMapper;
import com.xzy.pojo.EasyUIDataGridResult;
import com.xzy.pojo.TbItem;
import com.xzy.pojo.TbItemExample;
import com.xzy.serviceInterface.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zhouli
 * e-mail  ggwmmd@163.com
 * @since 2018/12/17 21:13
 */
@Service
public class ItemServiceImp implements ItemService {
    @Autowired
   private TbItemMapper itemMapper;

    @Override
    public TbItem selectById(Long id) {
       return this.itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItems);
        //取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);

        //取总记录数
        long total = pageInfo.getTotal();
        result.setTotal((int)total);
        return result;
    }
}
