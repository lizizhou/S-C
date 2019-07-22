package com.xzy.controller;


import com.xzy.pojo.EasyUIDataGridResult;
import com.xzy.pojo.EasyUITreeNode;
import com.xzy.serviceInterface.ItemCatService;
import com.xzy.serviceInterface.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public String returnIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }

}
