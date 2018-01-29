package queen.service;

import queen.common.entity.UserInfo;

import java.io.*;
import java.util.Properties;

public class ReadConfigServcie {

    public Properties getConfigProperties(){
        //String userdir = System.getProperty("user.dir");
        String clsspath = Thread.currentThread().getContextClassLoader().getResource("").toString()
                .replace("file:","")
                .replace("classes","");

        String configpaht = clsspath+"config.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configpaht));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  properties;
    }

    //获取文件中的账户密码信息
    //String filepath = "/home/pi/shareconfig";
    public UserInfo getUserinfo(){
        Properties properties = getConfigProperties();
        //File file = new File(filepath);
        UserInfo userInfo = new UserInfo();
        String userid = properties.getProperty("loginid");
        String password = properties.getProperty("password");
        userInfo.setUserId(userid);
        userInfo.setPassword(password);
        return userInfo;
    }

    //获取配置文件中的共享文件路径
    public String getSharePath(){
        String sharepath = null; //默认path
        Properties properties = getConfigProperties();
        sharepath = properties.getProperty("sharepath");
        return  sharepath;
    }

}
