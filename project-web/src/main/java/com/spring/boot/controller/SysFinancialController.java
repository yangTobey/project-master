package com.spring.boot.controller;

import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**财务管理控制类
 * Created by xiaoyang on 2018/4/2.
 */
@RestController
@RequestMapping("/sysFinancial")
public class SysFinancialController {
    private static final Logger logger = Logger.getLogger(SysFinancialController.class);

    /**
     * 查询系统应收账款列表
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @return
     */
    @RequestMapping(value = "/getSysAccountsList", method = RequestMethod.GET)
    public R getSysCompanyList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map = null;
        return R.ok(map);
    }
}
