package com.ebook.app.until;

import java.text.*;
import android.util.Log;

public class until {

    private until () {
    }

    //功能   ：判断日期字符串是否合法函数
	public static boolean isDate ( String date ) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss" );
        String isdate = date;
        if ( date.length() == 10 )				//如果只有日期，函数自动加上00:00:00
            isdate = date + " 00:00:00";
        try {
            sdf.parse ( isdate );
            return true;
        } catch ( Exception ex ) {
            //Log.e( "until","isDate(): The DATE format is error ." );
            return false;
        }
    }

    //测试字符串是否由数字(0-9)组成
    public static boolean isNum ( String in ) {
        return isNum ( in, 0, 0, 0 );
    }

    //功能   ：测试字符串表示的数值及范围，且字符串只能是数字组成
    public static boolean isNum ( String in, int length, double min, double max ) {
        String num = in;
        int point  = 0;						//'.'的个数
        int len = num.length ();
        if ( length > 0 ) {
            if ( len > length || len == 0 ) {	//判断字符串长度,不合法空返回false
            	//Log.e( "until", "isNum(): Length error." );
                return false;
            }
        }
        else if ( len == 0 ) {	//判断字符串是否为空,空返回false
        	//Log.e( "until", "isNum(): String is NULL" );
            return false;
        }
        for ( int i = len - 1; i >= 0; i-- ) {		    //判断字符串只能是数字
            char ac = num.charAt ( i );
            if ( ac == '.' && point == 0 &&  i != 0 ) {	//如果是'.'字符，且是第一次出现，且不是只有一个点
                if ( i > len - 4 ) {			        //判断小数位只能是两位
                    point++;
                    continue;
                }
            }
            if ( ac < '0' || ac > '9' ) {
            	//Log.e( "until", "isNum(): Character isn't ( '0' - '9' )" );
                return false;
            }
        }
        if ( length != 0 ) {
            double s = Double.parseDouble ( num );//现在len为字符串表示的数值
            if ( s < min  || s > max ) {		  //限制范围min-max之间
            	//Log.e( "until", "isNum(): Amount limit error. " );
                return false;
            }
        }
        return true;
    }
    //计算入住天数，1小时后开始计费，6个小时内算半天，以上算1天
    public static double gettime ( long in, long out ) {
        double d = out - in, ans = 0;

        d = d / 1000 / 360;

        while ( d >= 24 ) {
            ans = ans + 1;
            d-=24;
        }
        if ( ans > 6 ) {
            ans = ans + 1;
        }
        if ( ans > 1 ) {
            ans = ans + 0.5;
        }
        return ans;
    }
}
