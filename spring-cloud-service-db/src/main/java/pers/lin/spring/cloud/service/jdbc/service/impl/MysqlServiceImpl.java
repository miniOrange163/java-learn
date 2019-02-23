package pers.lin.spring.cloud.service.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lin.spring.cloud.service.jdbc.dao.RoleEntityMapper;
import pers.lin.spring.cloud.service.jdbc.entity.RoleEntity;
import pers.lin.spring.cloud.service.jdbc.service.MysqlService;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/2/23
 *  
 * @name: 
 *
 * @Description: 
 */
@Service
public class MysqlServiceImpl implements MysqlService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;


    @Override
    public RoleEntity selectRole(String id) {

        RoleEntity roleEntity = roleEntityMapper.selectByPrimaryKey(Long.parseLong(id));

        return roleEntity;
    }
}
