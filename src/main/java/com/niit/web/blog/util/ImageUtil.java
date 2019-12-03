package com.niit.web.blog.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author tj
 * @ClassName ImageUtil
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage getImage(int width,int height,String s){
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics= (Graphics2D) img.getGraphics();
//        背景
        graphics.setBackground(Color.GRAY);
//        位置大小
        graphics.fillRect(0,0,width,height);
        graphics.setPaint(Color.CYAN);
//        字体大小
        Font font = new Font("Seric",Font.BOLD,18);

        graphics.setFont(font);
        graphics.drawString(s,width/6,height/3);
        return img;
    }

    public static void main (String[] args) throws IOException {
//        在缓冲区生成图片，用随机生成的字符串
        BufferedImage img = ImageUtil.getImage(120,35,DataUtil.getVerification(4));

        File file = new File("D:/code.jpg");

        ImageIO.write(img,"jpg",file);

    }
}
