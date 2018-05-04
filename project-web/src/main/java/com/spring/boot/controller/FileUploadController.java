package com.spring.boot.controller;

import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传控制类
 * Created by xiaoyang on 2018/1/25.
 */
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {
    private static final Logger logger = Logger.getLogger(FileUploadController.class);

    /**
     * 多文件上传，注：@RequestParam("files")的files需要对应前端input标签的name属性
     *
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public R uploads(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
        String user = request.getParameter("userId");
        System.out.println("user:" + user);
        Map<String, String> resultMap = new HashMap<String, String>();
        //服务器硬盘存放地址
        String uploadUrl = "/upload/";
        try {
            //上传存放目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + uploadUrl;
            File dir = new File(uploadDir);
            //如果目录不存在，则创建文件夹
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileUrl = "";
            for (int i = 0; i < files.length; i++) {
                if (files[i] != null && files[i].getSize() > 0) {
                    //调用上传文件方法
                    Map<String, String> map = executeUpload(uploadDir, files[i], uploadUrl);
                    if (map.containsKey("success")) {
                        fileUrl += map.get("success") + ";";
                    }
                }
            }
            if (!UtilHelper.isEmpty(fileUrl)) {
                resultMap.put("url", fileUrl);
                return R.ok().putData(200, resultMap, "上传成功！");
            } else {
                return R.error(500, "上传文件失败，请联系管理员！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("文件上传出错：" + e.getMessage());
            return R.error(500, "上传文件失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 执行文件上传步骤
     *
     * @param uploadDir 上传存放路径
     * @param file      文件流
     * @throws Exception
     */
    public Map<String, String> executeUpload(String uploadDir, MultipartFile file, String uploadUrl) {
        Map<String, String> map = new HashMap<String, String>();
        //获取文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        //修改上传文件的文件名，避免出现重复文件时覆盖原有文件
        String newFileName = fileName + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + suffix;
        File saveFile = new File(uploadDir + newFileName);
        try {
            file.transferTo(saveFile);
            //返回上传文件的文件名
            map.put("success", uploadUrl + newFileName + "," + file.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "上传失败！");
        }
        return map;
    }

}
