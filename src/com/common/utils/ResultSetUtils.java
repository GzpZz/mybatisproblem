package com.common.utils;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetUtils {
	public static <T> T handlerResultSet(ResultSet rs,Class<T> cls) {
		T instance = null;
		
		try {
			instance = cls.newInstance();
			ResultSetMetaData mt = rs.getMetaData();
			for(int i=1;i<= mt.getColumnCount();i++) {
				 String java_field_type = mt.getColumnClassName(i);
				 String field_name = mt.getColumnName(i);//列名、属性名
				 Method settermethod = findMethod(cls,field_name,java_field_type);
				 if( settermethod!= null ) {
					 Object value  = null;
					  if( isString( java_field_type ) || isDate( java_field_type )) {
						  value = rs.getString(field_name);
					  }else if(isInt( java_field_type )) {
						  value = rs.getInt(field_name);
					  }else if(isLong( java_field_type )) {
						  value = rs.getLong(field_name);
					  }else if(isFloat( java_field_type)) {
						  value = rs.getFloat(field_name);
					  }
					  settermethod.invoke(instance, value);
					  
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		
		return instance;
	}

	private static boolean isFloat(String java_field_type) {
		 
		return false;
	}

	private static boolean isLong(String java_field_type) {
		 
		return false;
	}

	private static boolean isDate(String java_field_type) {
		 
		return false;
	}

	private static boolean isInt(String java_field_type) {
	 
		return false;
	}

	private static boolean isString(String java_field_type) {
		 
		return false;
	}

	private static Method findMethod(Class cls, String field_name, String java_field_type) {
		String method = "set"+field_name;
		
		try {
			Method m = cls.getMethod(method, Integer.class);
			if( m != null)return m;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
}
