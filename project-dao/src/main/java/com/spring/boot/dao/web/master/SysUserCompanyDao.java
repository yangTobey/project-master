package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserCompanyDao {
    /**
     * 获取用户权限下可以操作的公司
     *
     * @return
     */
    List<SysUserCompany> sysUserCompany(Long userId);

    /**
     * 新增信息
     *
     * @param sysUserCompany
     * @return
     */
    int saveSysUserCompany(SysUserCompany sysUserCompany);

    /**
     * 根据id删除信息
     *
     * @param userId
     * @return
     */
    int deleteSysUserCompany(Long userId);

    /**
     * 根据用户id和公司id，获取用户权限下公司信息
     *
     * @return
     */
    SysUserCompany sysUserCompanyAuthority(@Param("userId") Long userId, @Param("companyId") Long companyId);

}
