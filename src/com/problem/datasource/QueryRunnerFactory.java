package com.problem.datasource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

public class QueryRunnerFactory {
	private static MyDataSource ms = new MyDataSource();
	public static QueryRunner newQueryRunner() {
		return new QueryRunner(ms);
	}
	
	public static <T> T getOne(Class<T> cls,ResultSet rs) throws Exception {
		if(rs.next()) {
			return getResult(cls, rs);
		}
		return null;
	}
	public static <T> List<T> getList(Class<T> cls,ResultSet rs) throws Exception {
		List<T> list = new ArrayList<T>();
		while(rs.next()) {
			list.add( getResult(cls, rs) );
		}
		return list;
	}
	
	private static <T> T getResult(Class<T> cls,ResultSet rs) throws Exception {
		T obj = cls.newInstance();// 反射创建对象。
		// ① 当前cls所在的类，定义了哪些属性。才能知道setter方法的名字。
		// ② 每个属性是什么类型的。才能知道要调用rs.getInt还是rs.getString ...
		Field[] fs = cls.getDeclaredFields();// 获取cls类的所有定义的属性。
		for (Field f : fs) {
			String fieldname = f.getName();// 属性的名字。
			Class<?> type = f.getType();// 属性的类型的class对象。 int -- int.class
										// String --String.class
			String setter = "set" + fieldname.substring(0, 1).toUpperCase()
					+ fieldname.substring(1, fieldname.length());
			Method settermethod = cls.getMethod(setter, type);
			Object value =  null;
			if (settermethod == null)
				continue;// 如果方法不存在则跳过。
		    if(type == String.class) {
				value =  rs.getString( fieldname );
			}else if ( type == int.class || type == Integer.class ) {
				value =  rs.getInt( fieldname );
			}else if ( type == long.class || type == Long.class) {
				value =  rs.getLong( fieldname );
			}else if(  type == float.class || type == Float.class) {
				value =  rs.getFloat( fieldname );
			}else if(type == Date.class) {
				value =  rs.getDate( fieldname );
				
			}else if(type == List.class ) {
				
			}else if(type == Map.class) {
				
			}else {
				
			}
			settermethod.invoke(obj, value);
		}
		return obj;
	}
}
