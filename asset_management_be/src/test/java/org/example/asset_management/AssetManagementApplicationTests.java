package org.example.asset_management;

import org.example.asset_management.dao.AssetClassMapper;
import org.example.asset_management.dao.UserMapper;
import org.example.asset_management.service.impl.IndexServiceImpl;
import org.example.asset_management.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetManagementApplicationTests {
    @Autowired
//    private LoginServiceImpl loginService;
//    private UserMapper userMapper;
//    private IndexServiceImpl indexService;
    private AssetClassMapper assetClassMapper;
    @Test
    void contextLoads() {
        // TODO: 2023/3/22 测试代码
        System.out.println("测试："+assetClassMapper.getAssetClassByClassCode("13"));

    }


}
