package cn.mkblog.www.mkbrowser.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 张本志
 * @date 2019/1/23 下午2:05
 */
public class FileUtils {

    private String SDCardRoot;

    public FileUtils() {
        //得到当前外部存储设备的目录
        SDCardRoot = Environment.getExternalStorageDirectory() + "/wbDownload/";
        if (!isFileExist(SDCardRoot)) {
            File file = new File(SDCardRoot);
            file.mkdir();
        }
    }

    //在SD卡上创建文件
    public File createFileInSDCard(String fileName) throws IOException {
        File file = new File(SDCardRoot + fileName);
        if (!isFileExist(SDCardRoot + fileName)) {
            file.createNewFile();
        }
        return file;
    }

    //判断文件是否存在
    public boolean isFileExist(String fileName) {
        File file = new File(SDCardRoot + fileName);
        return file.exists();
    }

    //将一个InoutStream里面的数据写入到SD卡中
    public File write2SDFromInput(String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            //创建文件
            file = createFileInSDCard(fileName);
            //写数据流
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];//每次存4K
            int temp;
            //写入数据
            while ((temp = input.read(buffer)) != -1) {
                output.write(buffer, 0, temp);
            }
            output.flush();
        } catch (Exception e) {
            System.out.println("写数据异常：" + e);
            file = null;
        } finally {
            try {
                output.close();
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
        return file;
    }

}
