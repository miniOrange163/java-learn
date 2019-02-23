package pers.lin.spring.cloud.service.jdbc.service;

import pers.lin.spring.cloud.service.jdbc.entity.RoleEntity;

/**
 * @author: 林嘉宝
 * @Date: 2019/2/23
 * @name:
 * @Description:
 */
public interface MysqlService {

    RoleEntity selectRole(String id);

}
