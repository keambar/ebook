package com.example.ebook.until;

import java.text.*;
import android.util.Log;

public class until {

    private until () {
    }

    //����   ���ж������ַ��Ƿ�Ϸ�����
	public static boolean isDate ( String date ) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss" );
        String isdate = date;
        if ( date.length() == 10 )				//���ֻ�����ڣ������Զ�����00:00:00
            isdate = date + " 00:00:00";
        try {
            sdf.parse ( isdate );
            return true;
        } catch ( Exception ex ) {
            //Log.e( "until","isDate(): The DATE format is error ." );
            return false;
        }
    }

    //�����ַ��Ƿ�������(0-9)���
    public static boolean isNum ( String in ) {
        return isNum ( in, 0, 0, 0 );
    }

    //����   �������ַ��ʾ����ֵ����Χ�����ַ�ֻ�����������
    public static boolean isNum ( String in, int length, double min, double max ) {
        String num = in;
        int point  = 0;						//'.'�ĸ���
        int len = num.length ();
        if ( length > 0 ) {
            if ( len > length || len == 0 ) {	//�ж��ַ���,���Ϸ��շ���false
            	//Log.e( "until", "isNum(): Length error." );
                return false;
            }
        }
        else if ( len == 0 ) {	//�ж��ַ��Ƿ�Ϊ��,�շ���false
        	//Log.e( "until", "isNum(): String is NULL" );
            return false;
        }
        for ( int i = len - 1; i >= 0; i-- ) {		    //�ж��ַ�ֻ��������
            char ac = num.charAt ( i );
            if ( ac == '.' && point == 0 &&  i != 0 ) {	//�����'.'�ַ����ǵ�һ�γ��֣��Ҳ���ֻ��һ����
                if ( i > len - 4 ) {			        //�ж�С��λֻ������λ
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
            double s = Double.parseDouble ( num );//����lenΪ�ַ��ʾ����ֵ
            if ( s < min  || s > max ) {		  //���Ʒ�Χmin-max֮��
            	//Log.e( "until", "isNum(): Amount limit error. " );
                return false;
            }
        }
        return true;
    }
    //������ס����1Сʱ��ʼ�Ʒѣ�6��Сʱ������죬������1��
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
