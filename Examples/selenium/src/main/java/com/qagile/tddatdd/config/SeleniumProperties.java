package com.qagile.tddatdd.config;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 20.03.12
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class SeleniumProperties {

    public static String host = "http://localhost:8080";
    public static String app = "/agilefant";
    
    // different default user, you may use
    public static String adminName = "admin";
    public static String adminPwd = "secret";

    private static String userName = "agilefant";
    private static String userPwd  = "agilefant";
    
    //Browser for the tests, default is FireFox
    public static DriverTypes browser = DriverTypes.FIREFOX;
    public static String pathToChromeDriver = "C:\\Users\\ful\\FUL_privat\\TDDExamples\\projects\\selenium\\lib\\chromedriver_win_19.0.1068.0\\chromedriver.exe";

}
