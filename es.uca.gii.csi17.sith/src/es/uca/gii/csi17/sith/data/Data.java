package es.uca.gii.csi17.sith.data;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.ResultSet;

import es.uca.gii.csi17.sith.util.Config;

public class Data {
    public static String getPropertiesUrl() { return "./db.properties"; }
    public static Connection Connection() throws Exception {
        try {
            Properties properties = Config.Properties(getPropertiesUrl());
            return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
       }
       catch (Exception ee) { throw ee; }
	}
    
    public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildcards){
   	
    	if(bAddQuotes && !bAddWildcards) {
    		s = s.replace("'", "''");
    		s = new String("'" + s + "'");
    		
    	}
    	
    	if(bAddWildcards && !bAddQuotes) {
    		s = s.replace("'", "''");
    		s = new String("%" + s + "%");
    		
    	}
    	
    	if(bAddWildcards && bAddQuotes) {
    		s = s.replace("'", "''");
    		s = new String("'%" + s + "%'");
    		
    	}

    	return s;
    }
    
    public static int Boolean2Sql(boolean a) {
    	if(a)
    		return 1;
    	else
    		return 0;
    }
    
    public static void LoadDriver() 
        throws InstantiationException, IllegalAccessException, 
        ClassNotFoundException, IOException {
            Class.forName(Config.Properties(Data.getPropertiesUrl()
            ).getProperty("jdbc.driverClassName")).newInstance();
    }
    
    public static int LastId(Connection con) throws SQLException
    {
    	ResultSet rs = null;
    	int iLastId=-1;
    	try
    	{
    		rs = con.createStatement().executeQuery("select last_insert_id();");
    		rs.next();
    		System.out.println("last id(): " + rs.getInt(1));
    		iLastId = rs.getInt(1);
    	}
	 	
		finally {
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
    	
    	return iLastId;
    }
}
