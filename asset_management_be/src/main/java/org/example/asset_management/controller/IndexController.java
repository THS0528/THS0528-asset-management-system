package org.example.asset_management.controller;

import org.example.asset_management.service.impl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/16 23:22:13
 * @description: 主页控制层
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexServiceImpl indexService;
    @GetMapping("/getUser")
    public String getUser(int userId) {
        System.out.println("用户id："+userId);
        System.out.println(indexService.getUser(userId));
        return indexService.getUser(userId);
    }
}
