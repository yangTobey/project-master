package com.spring.boot.controller;

import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.service.SysFileService;
import com.spring.boot.util.JsonUtils;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传控制类
 * Created by xiaoyang on 2018/1/25.
 */
@RestController
@RequestMapping("/fileManage")
public class FileManageController {
    private static final Logger logger = Logger.getLogger(FileManageController.class);

    @Autowired
    private SysFileService sysFileService;

    /**
     * 多文件上传，注：@RequestParam("files")的files需要对应前端input标签的name属性
     *
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public String uploads(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
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
                    //单个文件大小不能超过1024*8（8MB）
                    /*if(files[i].getSize()>8192){
                        return R.error(400, "单个文件不能超过8MB！");
                    }*/
                    //调用上传文件方法
                    Map<String, String> map = executeUpload(uploadDir, files[i], uploadUrl);
                    if (map.containsKey("success")) {
                        fileUrl += map.get("success") + ";";
                    }
                }
            }
            if (!UtilHelper.isEmpty(fileUrl)) {
                resultMap.put("url", fileUrl);
                return JsonUtils.obj2JsonString(R.ok().putData(200, resultMap, "上传成功！"));
            } else {
                return JsonUtils.obj2JsonString(R.error(500, "上传文件失败，请联系管理员！"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("文件上传出错：" + e.getMessage());
            return JsonUtils.obj2JsonString(R.error(500, "上传文件失败，服务器异常，请联系管理员！"));
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
        //将原有文件名称中的逗号和分号替换成空格，避免在保存新增时，传入的文件路径根据逗号或者分号切分时出现问题（英文和中文两种状态）
        String originalFilename=file.getOriginalFilename().replace(",","").replace(";","").replace("，","").replace("；","");
        //获取文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        //修改上传文件的文件名，避免出现重复文件时覆盖原有文件
        String newFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 15) + suffix;
        File saveFile = new File(uploadDir + newFileName);
        try {
            file.transferTo(saveFile);
            //返回上传文件的文件名和文件大小，文件大小转化为MB格式
            map.put("success", fileName+","+uploadUrl + newFileName + "," + UtilHelper.decimalNumber((int)file.getSize(),1024));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "上传失败！");
        }
        return map;
    }

    /**
     * 品质管理文件下载相关代码
     *
     * @param request
     * @param response
     * @param fileIds  需要下载的文件id集合
     * @return
     */
    @RequestMapping(value = "/downloadQualityFile", method = RequestMethod.GET)
    public R downloadQualityFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileIds") String fileIds) {
        //去掉最后那个逗号，在进行获取数据
        String[] fileInfoArray = fileIds.substring(0, fileIds.length() - 1).split(";");
        List<String> fileLists = new ArrayList<String>();
        for (String fileId : fileInfoArray) {
            SysQualityManageFile sysQualityManageFile = sysFileService.fileSysQualityManageFileById(Long.valueOf(fileId));
            if (sysQualityManageFile != null) {
                //获取文件路径
                String realPath = request.getSession().getServletContext().getRealPath("/") + sysQualityManageFile.getFileUrl();
                fileLists.add(realPath);
            }
        }
        //将多个需要下载的文件打包成zip格式压缩包进行下载
        downloadZip(fileLists, request, response);
        return null;
    }
    /**
     * 工程能耗文件下载相关代码
     *
     * @param request
     * @param response
     * @param fileIds  需要下载的文件id集合
     * @return
     */
    @RequestMapping(value = "/downloadProjectFile", method = RequestMethod.GET)
    public R downloadProjectFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileIds") String fileIds) {
        //去掉最后那个逗号，在进行获取数据
        String[] fileInfoArray = fileIds.substring(0, fileIds.length() - 1).split(";");
        List<String> fileLists = new ArrayList<String>();
        for (String fileId : fileInfoArray) {
            SysProjectEnergyFile sysProjectEnergyFile = sysFileService.fileSysProjectEnergyFileById(Long.valueOf(fileId));
            if (sysProjectEnergyFile != null) {
                //获取文件路径
                String realPath = request.getSession().getServletContext().getRealPath("/") + sysProjectEnergyFile.getFileUrl();
                fileLists.add(realPath);
            }
        }
        //将多个需要下载的文件打包成zip格式压缩包进行下载
        downloadZip(fileLists, request, response);
        return null;
    }
    /**
     * 合同档案文件下载相关代码
     *
     * @param request
     * @param response
     * @param fileIds  需要下载的文件id集合
     * @return
     */
    @RequestMapping(value = "/downloadSysContractFile", method = RequestMethod.GET)
    public R downloadSysContractFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileIds") String fileIds) {
        //去掉最后那个逗号，在进行获取数据
        String[] fileInfoArray = fileIds.substring(0, fileIds.length() - 1).split(";");
        List<String> fileLists = new ArrayList<String>();
        for (String fileId : fileInfoArray) {
            SysContractFile sysContractFile = sysFileService.fileSysContractFileById(Long.valueOf(fileId));
            if (sysContractFile != null) {
                //获取文件路径
                String realPath = request.getSession().getServletContext().getRealPath("/") + sysContractFile.getFileUrl();
                fileLists.add(realPath);
            }
        }
        //将多个需要下载的文件打包成zip格式压缩包进行下载
        downloadZip(fileLists, request, response);
        return null;
    }

    /**
     * 文件下载相关代码(单个文件下载，不需要做压缩处理，用于更新时，点击单个文件下载)
     * @param request
     * @param response
     * @param fileUrl 需要下载的文件路径
     * @return
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public R downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam("fileUrl") String fileUrl) {
        /*//去掉最后那个逗号，在进行获取数据
        String[]  fileInfoArray = fileIds.substring(0, fileIds.length() - 1).split(";");
        for (String fileId : fileInfoArray) {
            SysQualityManageFile sysQualityManageFile=sysFileService.fileSysQualityManageFileById(Long.valueOf(fileId));
            if(sysQualityManageFile!=null){
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }*/
        String fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1, fileUrl.length());
        try {
            outputFile(request,response,fileUrl,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出需要下载的文件(单个文件下载)
     *
     * @param request
     * @param response
     * @param realPath
     * @param fileName
     */
    public void outputFile(HttpServletRequest request, HttpServletResponse response, String realPath, String fileName) throws IOException {
        //String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            realPath = request.getSession().getServletContext().getRealPath("/") + realPath;
            File file = new File(realPath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                //response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 压缩并导出文件（多文件下载，将多个文件压缩成一个文件下载）
     *
     * @param createFilesPath 需要压缩的文件列表
     * @param request
     * @param response
     * @return
     */
    public boolean downloadZip(List<String> createFilesPath, HttpServletRequest request, HttpServletResponse response) {
        //上传存放目录地址
        // zipPath 压缩文件临时路径  路径最后不要有 /
        String zipPath = request.getSession().getServletContext().getRealPath("/") + "/zipDownLoad/";
        //zipName 压缩为文件名 **.zip，格式为：时间+uuid10位
        String zipName = UtilHelper.getNowDateTimeStr() + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + ".zip";
        byte[] buffer = new byte[1024];
        String strZipPath = zipPath + "/" + zipName;
        try {
            File tmpZip = new File(zipPath);
            //如果目录不存在，则创建文件夹
            if (!tmpZip.exists()) {
                tmpZip.mkdirs();
            }
            File tmpZipFile = new File(strZipPath);
            if (!tmpZipFile.exists()) {
                tmpZipFile.createNewFile();
            }
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            // 需要同时下载的两个文件result.txt ，source.txt
            File[] file1 = new File[createFilesPath.size()];
            for (int i = 0; i < createFilesPath.size(); i++) {
                file1[i] = new File(createFilesPath.get(i));
            }
            for (int i = 0; i < file1.length; i++) {
                //首先判断该文件在服务器是否存在，避免抛出异常，下载失败
                if (file1[i].exists()) {
                    FileInputStream fis = new FileInputStream(file1[i]);
                    out.putNextEntry(new ZipEntry(file1[i].getName()));
                    //设置压缩文件内的字符编码，不然会变成乱码
                    //out.setEncoding("UTF-8");
                    int len;
                    // 读入需要下载的文件的内容，打包到zip文件
                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.closeEntry();
                    fis.close();
                }
            }
            out.close();
            //以压缩文件导出
            this.downloadFile(zipPath, zipName, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 以压缩文件导出
     *
     * @param fileName
     * @param filePath
     * @param response
     */
    public void downloadFile(String filePath, String fileName, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        try {
            File file = new File(filePath, fileName);
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            //设置编码格式，避免中文乱码
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //下载后，将压缩包删除，避免占用服务器空间
            file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
