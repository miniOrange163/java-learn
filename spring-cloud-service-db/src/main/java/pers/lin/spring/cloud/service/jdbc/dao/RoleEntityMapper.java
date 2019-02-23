package pers.lin.spring.cloud.service.jdbc.dao;

import pers.lin.spring.cloud.service.jdbc.entity.RoleEntity;

public interface RoleEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}