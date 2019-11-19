package com.niit.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * 数据生成工具
 * @author tj
 * @ClassName DataUtil
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/


public class DataUtil {
    /**
     * 获得电话号码
     * @return
     */
   public static String getMobile(){
       StringBuilder stringBuilder = new StringBuilder("139");
       Random random = new Random();
       for (int i=0;i<8;i++){
           int num = random.nextInt(10);
           stringBuilder.append(num);
       }
       return stringBuilder.toString();
   }

    /**
     * 随机生成密码
     * @return
     */
   public static String getPassword(){
       StringBuilder password= new StringBuilder();
       Random random = new Random();
       for(int i=0; i<6; i++){
           int num= random.nextInt(10);
           password.append(num);
       }
       return DigestUtils.md2Hex(password.toString());
   }

    /**
     * 随机获得性别
     * @return
     */
   public static String getGender(){
       String[] genders = new String[]{"男","女"};
       Random random = new Random();
//       根据数据索引获取值
       int index =random.nextInt(2);
       return genders[index];
   }

    /**
     * 随机获取生日日期
     * @return
     */
   public static LocalDate getBirthday(){
       LocalDate now = LocalDate.now();
       Random random = new Random();
       int bound = random.nextInt(6666);
//       当前日期的前bound天
       return now.minusDays(bound);
   }

    /**
     * 随机生成关注量
     * @return
     */
   public  static int getLikeAccount(){
       Random random = new Random();
       int account = random.nextInt(500);
       return account;
   }

    /**
     * 随机生成评论人数
     * @return
     */
   public static int getCommentAccount(){
       Random random = new Random();
       int account = random.nextInt(500);
       return account;
   }
   public static int getForwordAccount(){
       Random random = new Random();
       int account = random.nextInt(200);
       return account;
   }

   public static long getAuthorid(){
       Random random = new Random();
       int account = random.nextInt(73);
       return account;
   }

}
