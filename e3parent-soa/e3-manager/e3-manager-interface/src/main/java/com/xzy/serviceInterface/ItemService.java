package com.xzy.serviceInterface;

import com.xzy.pojo.EasyUIDataGridResult;
import com.xzy.pojo.TbItem;

public interface ItemService {
    TbItem selectById(Long id);

    EasyUIDataGridResult getItemList(int page ,int rows);
}
