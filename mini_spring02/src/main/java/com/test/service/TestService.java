package com.test.service;

import com.dyx.annotation.Autowired;
import com.dyx.annotation.Service;
import com.test.dao.TestDao;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-22:23
 * @Description:
 */

@Service
public class TestService {
    @Autowired
    TestDao testDao;

    public void echo(){
        System.out.println(testDao.echo());
    }
}
