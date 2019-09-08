package com.common.utils;

import java.sql.SQLException;

import com.problem.datasource.QueryRunnerFactory;
import com.problem.entity.User;

public class UserDaoImplTest extends QueryRunnerFactory{
	
	public void testQuery() throws SQLException {
		User rsu = this.newQueryRunner().query("select * from user", 
				rs->{
					return ResultSetUtils.handlerResultSet(rs, User.class);
				});
		System.out.println(rsu);
	}
	public static void main(String[] args) throws SQLException {
		new UserDaoImplTest().testQuery();
	}
}
