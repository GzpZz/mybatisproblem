<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <base href="/problem/">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>问题记录系统</title>
  <link rel="stylesheet" href="./layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
   <%@ include file="header.jsp" %>
  <div class="layui-container">
     
   <form class="layui-form" action="user/login.action" method="post">
   <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input name="username" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">*</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">*</div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">登陆</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
 
  </div>
  
</div>
  

<script src="./layui/layui.js"></script>
<script>

layui.use(["element",'form'], function(){});
</script>
</body>
</html>