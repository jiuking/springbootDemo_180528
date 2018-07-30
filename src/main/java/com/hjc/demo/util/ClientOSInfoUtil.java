package com.hjc.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Administrator
 * @date : 2018/6/13 0013 17:48
 * @description : 获取客户端信息
 */
public final class ClientOSInfoUtil {

    private static Pattern win7 = Pattern.compile(".*(Windows NT 6\\.1).*");

    private static Pattern winXP = Pattern.compile(".*(Windows NT 5\\.1|Windows XP).*");
    private static Pattern win2003 = Pattern.compile(".*(Windows NT 5\\.2).*");

    private static Pattern win2000 = Pattern.compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");

    private static Pattern mac = Pattern.compile(".*(Mac|apple|MacOS8).*");

    private static Pattern winNT = Pattern.compile(".*(WinNT|WindowsNT).*");

    private static Pattern linux = Pattern.compile(".*Linux.*");

    private static Pattern mac68k = Pattern.compile(".*68k|68000.*");

    private static Pattern win9x = Pattern.compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");


    private ClientOSInfoUtil() {

    }

    public static String getClientOS(String userAgent) {
        String cos = "unknow os";

        Matcher m = win7.matcher(userAgent);
        if (m.find()) {
            cos = "Windows7";
            return cos;
        }

        m = winXP.matcher(userAgent);
        if (m.find()) {
            cos = "WinXP";
            return cos;
        }

        m = win2003.matcher(userAgent);
        if (m.find()) {
            cos = "Win2003";
            return cos;
        }

        m = win2000.matcher(userAgent);
        if (m.find()) {
            cos = "Win2000";
            return cos;
        }

        m = mac.matcher(userAgent);
        if (m.find()) {
            cos = "MAC";
            return cos;
        }

        m = winNT.matcher(userAgent);
        if (m.find()) {
            cos = "WinNT";
            return cos;
        }

        m = linux.matcher(userAgent);
        if (m.find()) {
            cos = "Linux";
            return cos;
        }

        m = mac68k.matcher(userAgent);
        if (m.find()) {
            cos = "Mac68k";
            return cos;
        }

        m = win9x.matcher(userAgent);
        if (m.find()) {
            cos = "Win9x";
            return cos;
        }

        return cos;
    }

}
