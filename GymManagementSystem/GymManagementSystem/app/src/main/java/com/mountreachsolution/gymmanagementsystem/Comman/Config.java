package com.mountreachsolution.gymmanagementsystem.Comman;


public class Config {

        private static String OnlineAddress = "http://192.168.1.2:80/gym_management_system/Member/";
        public static String OnlineImageAddress="http://192.168.1.5:80/office_management_system/uploadimage/";
        public static String OnlineDocAddress="http://192.168.1.5:80/office_management_system/taskdoc/";

        public static String url_login = OnlineAddress+ "login_member.php";
        public static String url_get_my_details = OnlineAddress+ "getMyDetails.php";
        public static String urlGetMySchedule = OnlineAddress+ "getMySchedule.php";
        public static String urlGetMyDiet = OnlineAddress+ "getMyDiet.php";
        public static String urlGetMyPackage = OnlineAddress+ "getMyPackage.php";


}
