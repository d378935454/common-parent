package com.bean.springboot.utils;

import com.bean.springboot.controller.UtilsController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by ppctest02 on 2017/4/26.
 */
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
        throw new IllegalAccessError("Utility class");
    }
    /**
     *
     * @param pathname 路劲
     * @param file 文件
     * @throws IOException
     */
    public static void upFile(String pathname,MultipartFile file) throws IOException {
        // 创建文件目录
        File savedir = new File(pathname);
        // 如果目录不存在就创建
        if (!savedir.exists()) {
            savedir.mkdirs();
        }
        if(new File(savedir,file.getOriginalFilename()).exists()){
            return;
        }
        file.transferTo(new File(savedir,file.getOriginalFilename()));
    }
    /**
     * 文件上传
     *
     * @param basePath
     * @param multipartFile
     * @param dir
     * @return
     * @throws IOException
     */
    public static String upload(String basePath, MultipartFile multipartFile, String dir) throws IOException {
        String saveFilePath = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                //扩展名
                String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                //图片名称生成策略
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                //图片名称一部分
                String format = df.format(new Date());
                //随机三位数
                Random r = new Random();
                // n 1000   0-999   99
                for (int i = 0; i < 3; i++) {
                    format += r.nextInt(10);
                }
                //文件名称
                String fileName = dir + format + "." + ext;
                int hashCode = Math.abs(fileName.hashCode());
                int dir1 = hashCode & 0xff;
                int dir2 = (hashCode & 0xff) >> 4;
                //存放文件路径
                String uploadFilePath = basePath + File.separator + dir + File.separator + dir1 + File.separator + dir2;
                //访问文件夹路径
                saveFilePath = File.separator + dir + File.separator + dir1 + File.separator + dir2 + File.separator + fileName;
                //文件夹是否存在
                File filePath = new File(uploadFilePath);
                if (!filePath.exists() && !filePath.isDirectory()) {
                    filePath.mkdir();
                }
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(filePath, fileName));
                return saveFilePath;
            } catch (Exception e) {
                log.error("upload()", e);
            }
            return saveFilePath;
        }
        return saveFilePath;
    }
}
