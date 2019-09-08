<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.problem.entity.User" %>
<div class="layui-header">
			<div class="layui-logo">我的问题我做主</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">问题列表</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="insert.jsp">添加</a>
						</dd>
					</dl></li>
			</ul>
			<!--  登陆之后的效果 -->
			<%
			User user = (User)session.getAttribute("loginuser");
			if( user != null ){%>
				<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img">我
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="#">消息</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="user/logout.action">退了</a></li>
			</ul>  
				
			<%} else{%>
				<!--  没登陆的效果 --> 
				<ul class="layui-nav layui-layout-right" >
					<li class="layui-nav-item"><a href="login.jsp">登陆</a></li>
				</ul>
				
			<%}%>
			
			
			
		</div>