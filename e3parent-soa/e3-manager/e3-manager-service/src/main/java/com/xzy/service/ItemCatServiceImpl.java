package com.xzy.service;

import com.xzy.mapper.TbItemCatMapper;
import com.xzy.pojo.EasyUITreeNode;
import com.xzy.pojo.TbItemCat;
import com.xzy.pojo.TbItemCatExample;
import com.xzy.serviceInterface.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@org.springframework.stereotype.Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parentId查询商品
     //   System.out.println("###############################"+parentId+"##############################");
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
      //  System.out.println("###############################"+tbItemCats+"##############################");

        System.out.println(tbItemCatExample);
        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> list = new ArrayList<>();
        if(tbItemCats!=null){
            for (TbItemCat tbItemCat : tbItemCats) {
                EasyUITreeNode node = new EasyUITreeNode();
                node.setId(tbItemCat.getId());
                node.setText(tbItemCat.getName());
                node.setState(tbItemCat.getIsParent()?"closed":"open");
                list.add(node);
            }
        }
        //返回结果
        return list;
    }
}
