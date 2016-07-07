package com.ebook.app.db;

import java.sql.*;

public class sql {
    private static Connection Conn = null;
    private static Statement ste = null;

    static {

        String driverName = "com.mysql.jdbc.Driver";

        String dbURL = "jdbc:mysql://127.0.0.1:3306/nebook";

        String userName = "root";

        String userPwd = "123456";

        try{
            Class.forName ( driverName );
           	//System.out.println("连接数据库成功");
            Conn = DriverManager.getConnection ( dbURL, userName, userPwd );
            //Log.d("sql","连接数据库成功");
            ste = Conn.createStatement ( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
        } catch ( Exception e ) {            
        	//System.out.print("连接失败");
            System.exit ( 0 );
        }
    }
    
    //执行sql 修改操作
    public static int executeUpdate ( String sql ) {
        int i = 0 ;
        try {
            i = ste.executeUpdate ( sql ) ;
        } catch ( Exception e ) {
            e.printStackTrace() ;
        }
        return i ;
    }
    
    //修改操作事务处理
    public static int runTransaction ( String updateCode[] ) {
        int ok = 0, i = 0;
        int row = updateCode.length;
        try {
        	Conn.setAutoCommit(false);
            for ( i = 0; i < row ; ++i ) {
                ok = ste.executeUpdate ( updateCode[i] );
                if ( ok == 0 ) {
                    break;
                }
            }
            if ( ok == 0 ) {
                Conn.rollback();
            } else {
                Conn.commit();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return i;
    }

    //计算结果集项目数
    public static int recCount ( ResultSet rrs ) {
        int i = 0;
        try {
            if ( rrs.getRow() != 0 )
                rrs.beforeFirst();

            while ( rrs.next() )
                i++;
            rrs.beforeFirst();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }//End try
        return i;
    }

    //执行查询操作
    public static ResultSet executeQuery ( String sql ) {
        ResultSet rs = null ;
        try {
            rs = ste.executeQuery ( sql ) ;
        } catch ( Exception e ) {
            e.printStackTrace() ;
        }
        return rs ;
    }

    //获取服务器时间
    public static long getPrimaryKey() {
        long pk = 0;

        try {
            ResultSet rs = executeQuery ( "select getdate()" );
            rs.next();
            pk = rs.getTimestamp ( 1 ).getTime();
        } catch ( Exception ex ) {
            System.out.println ( "getPrimaryKey (): false" );
        }
        return pk;
    }
    /*============================================================
    下面三个函数用于将结果集的数据加入下拉框
    列表框和表格
    =============================================================*/
    //从结果集中添加数据到下拉框
//    public static void initJComboBox ( JComboBox cb, String sqlCode ) {
//        cb.removeAllItems();
//        try {
//            ResultSet rs = executeQuery ( sqlCode );
//            int row = recCount ( rs );
//            //JOptionPane.showMessageDialog (null, "当前没有操作员", "错误", JOptionPane.ERROR_MESSAGE);
//            rs.beforeFirst ();
//            //从结果集中取出Item加入JComboBox中
//            for ( int i = 0; i < row; i++ ) {
//                rs.next();
//                cb.addItem ( rs.getString ( 1 ) );
//            }
//        } catch ( Exception ex ) {
//            System.out.println ( "initJComboBox (): false" );
//        }
//    }
//
//    //从结果集中添加数据到列表框
//    public static void initJList ( JList jt, String sqlCode ) {
//        try {
//            ResultSet rs = executeQuery ( sqlCode );
//            int row = recCount ( rs );
//            String list[] = new String[row];
//            //从结果集中取出数据存入数组中
//            for ( int i = 0; i < row; i++ ) {
//                rs.next();
//                list[i] = rs.getString ( 1 );
//            }//Endfor
//            jt.setListData ( list );	//初始化List
//        } catch ( Exception ex ) {
//            System.out.println ( "initJList(): false" );
//        }//Endtry
//    }
//
//    //将结果添加到表格
//    public static void initDTM ( DefaultTableModel fdtm, String sqlCode ) {
//        try {
//            ResultSet rs = executeQuery ( sqlCode );	//获得结果集
//            int row = recCount ( rs );				//获得结果集中有几行数据
//            ResultSetMetaData rsm = rs.getMetaData();	//获得列集
//            int col = rsm.getColumnCount();		//获得列的个数
//            String colName[] = new String[col];
//            //取结果集中的表头名称, 放在colName数组中
//            for ( int i = 0; i < col; i++ ) {
//                colName[i] = rsm.getColumnName ( i + 1 );
//            }
//            rs.beforeFirst();
//            String data[][] = new String[row][col];
//            //取结果集中的数据, 放在data数组中
//            for ( int i = 0; i < row; i++ ) {
//                rs.next();
//                for ( int j = 0; j < col; j++ ) {
//                    data[i][j] = rs.getString ( j + 1 );
//                    //System.out.println (data[i][j]);
//                }
//            }
//            fdtm.setDataVector ( data, colName );
//        } catch ( Exception ex ) {
//            System.out.println ( "SQL.initDTM (): false" );
//        }
//    }
    //测试用代码
//	public static void main(String [] args){
//		test a=new test();
//
//	}
}

