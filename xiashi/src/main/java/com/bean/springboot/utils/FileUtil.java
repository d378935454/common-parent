package com.bean.springboot.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by ppctest02 on 2017/4/26.
 */
public class FileUtil {

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
}
