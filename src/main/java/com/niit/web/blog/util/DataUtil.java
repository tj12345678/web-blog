package com.niit.web.blog.util;

import com.niit.web.blog.entity.Region;
import com.niit.web.blog.factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private static Logger logger = LoggerFactory.getLogger(DataUtil.class);
    private static final int MOBILE_COUNT = 8;
    private static final int PASSWORD_COUNT = 6;

    /**
     * 随机生成手机号
     *
     * @return
     */
    public static String getMobile() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder("139");
        for (int i = 0; i < MOBILE_COUNT; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    /**
     * 生成密码并用MD5加密
     *
     * @return
     */
    public static String getPassword() {
//        Random random = new Random();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < PASSWORD_COUNT; i++) {
//            stringBuilder.append(random.nextInt(9));
//        }
        return DigestUtils.md5Hex("111");
    }

    /**
     * 随机生成性别
     *
     * @return
     */
    public static String getGender() {
        Random random = new Random();
        String[] data = new String[]{"男", "女"};
        int index = random.nextInt(2);
        return data[index];
    }

    /**
     * 随机生成生日
     *
     * @return
     */
    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    /**
     * 随机生成地址
     *
     * @return
     */
    public static String getAddress() {
        Random random = new Random();
        String address = null;
        try {
            List<Region> regionList = DaoFactory.getRegionDaoInstance().selectAll();
            Region region = regionList.get(random.nextInt(regionList.size()));
            address = region.getMergeName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 生成时间
     *
     * @return
     */
    public static LocalDateTime getCreateTime() {
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        int bound = random.nextInt(999);
        return now.minusHours(bound);
    }
    public static LocalDateTime getNowTime(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    /**
     * @return
     */
    public static Long getUserId() {
        Random random = new Random();
        long bound = random.nextInt(21);
        return bound;
    }

    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5Hex("111"));
        System.out.println(getCreateTime());
        System.out.println(getNowTime());
    }
}
