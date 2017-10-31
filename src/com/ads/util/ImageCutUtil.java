package com.ads.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageCutUtil {
	
	/**
	 * 对图片进行剪切
	 * @param srcFile 原始图片路径
	 * @param outFile 剪切后图片路径
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @return 是否成功
	 */
	public static boolean cutImage(String srcFile, String outFile, int x, int y,
            int width, int height) {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 如果源图片不存在
            if (!new File(srcFile).exists()) {
                return false;
            }
            // 读取图片文件
            is = new FileInputStream(srcFile);
            // 获取文件格式
            String ext = srcFile.substring(srcFile.lastIndexOf(".") + 1);
            // ImageReader声称能够解码指定格式
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);
            // 输入源中的图像将只按顺序读取
            reader.setInput(iis, true);
            // 描述如何对流进行解码
            ImageReadParam param = reader.getDefaultReadParam();
            // 图片裁剪区域
            Rectangle rect = new Rectangle(x, y, width, height);
            // 提供一个 BufferedImage，将其用作解码像素数据的目标
            param.setSourceRegion(rect);
            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象
            BufferedImage bi = reader.read(0, param);
            // 保存新图片
            File tempOutFile = new File(outFile);
            if (!tempOutFile.exists()) {
                tempOutFile.mkdirs();
            }
            ImageIO.write(bi, ext, new File(outFile));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (iis != null) {
                    iis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
