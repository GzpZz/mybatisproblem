<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<base href="/problem/">
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>问题记录系统</title>
<link rel="stylesheet" href="./layui/css/layui.css">
<style>
	.solved{
		background-color: #5FB878;
	}
	.unsolved{
		background-color:#d2d2d2
	}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<%@ include file="header.jsp" %>
		<!-- 内容主体区域 1024 -->
		<div class="layui-container">
			<div style="padding: 15px;">

				<form class="layui-form" >
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">分类</label>
							<div class="layui-input-inline" style="width: 100px;">
								<input   name="searchtype" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">内容</label>
							<div class="layui-input-inline" style="width: 100px;">
								<input   name="seachcontent" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						 <div class="layui-inline">
						    <label class="layui-form-label">状态</label>
						    <div class="layui-input-block">
						    	<input type="radio" name="status" value="-1" title="所有" checked>
						        <input type="radio" name="status" value="1" title="解决">
								<input type="radio" name="status" value="0" title="未解决" >
							 </div>
						  </div>
					</div>
					<div  class="layui-form-item">
						    <div class="layui-inline">
								<label class="layui-form-label">开始日期</label>
								<div class="layui-input-inline" style="width: 100px;">
									<input  id="searchdate" name="searchdate" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">结束日期</label>
								<div class="layui-input-inline" style="width: 100px;">
									<input  id="searchdate2" name="searchdate2" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							  <div class="layui-inline">
							 		<button    lay-submit lay-filter="searchquestionbtn" class="layui-btn">查询</button>
							 </div>
					</div>
				</form>
				<table id="questiontable" lay-filter="questionlisttable"></table>
				<script type="text/html" id="toolbar">
			  <div class="layui-btn-container">
			    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
			    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
			    <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
                <button class="layui-btn layui-btn-sm" lay-event="detail">详情</button>
                <button class="layui-btn layui-btn-sm" lay-event="updatestatus">更新状态</button>
			  </div>
			</script>
			</div>
		</div>
	</div>
	<script src="./layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', "table" ,"laydate","form","jquery","layer"], function() {
			var element = layui.element;
			var table = layui.table;
			var laydate = layui.laydate;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var tableinstance = null;
			  form.on('submit(searchquestionbtn)', function(data){
			    tableinstance.reload({where:data.field,page:{curr:1}});
			    return false;
			  });
			 //日期组件渲染
			  laydate.render({
			    elem: '#searchdate' //指定元素
			  });
			  laydate.render({
				    elem: '#searchdate2' //指定元素
				  });
			 //表格实例。
			 tableinstance = table.render({
				elem : '#questiontable',
				height : 500,
				toolbar : "#toolbar",
				url : 'question/getall.action' ,
				page : true,
				limit : 10,
				limits : [ 10,15,20,25,30,50 ],
				where:{status:-1},
				response : {
					statusCode : 200
				},
				cols : [ [ //表头
					{type : "checkbox",width : 40,fixed : 'left'}, 
					{field : 'status',title : '状态',width : 80,fixed : 'left',
						templet : function(d) {
							if (d.status == 0)
								return "<div class='unsolved'>未解决</div>";
							else
								return "<div class='solved'>已解决</div>";
						}
					}, 
					{field : 'content',title : '内容',width : 300}, 
					{field : 'type',title : '分类',width : 150}, 
					{field : 'qimg',title : '说明图片',width : 150}, 
					{field : 'answer',title : '答案',width : 200}, 
					{field : 'animg',title : '答案图片',width : 150}, 
					{field : 'creationtime',title : '创建时间',width : 200}, 
					{field : 'uid',title : '创建者',width : 120}
				] ]
			});

			//监听工具栏事件
			table.on('toolbar(questionlisttable)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				var rowdata = checkStatus.data;//获取选中行数据。是一个 [{},{}] 
				switch (obj.event) {
				case 'add':
					location.href="insert.jsp"
					break;
				case 'delete':
					 if(rowdata.length > 0){
						 var ids = "";
						 rowdata.map(function(rowd){
							 ids += (rowd.id+",");
						 });
						 layer.confirm('删除后不可回复，是否删除?', {icon: 3, title:'温馨提示'}, function(index){
							 //弹出确认框确定后再删除。
							 $.post("question/delete.action",{deleteids:ids},function(){
								 tableinstance.reload();
							 });
							  layer.close(index);
							});
						
					 }
					break;
				case 'update':
					if(rowdata.length ==1 ){
						sessionStorage.setItem("editrowdata",JSON.stringify(rowdata[0]));
						location.href = "edit.jsp";
					}
					break;
				case 'detail':
					if(rowdata.length ==1 ){
						sessionStorage.setItem("detailrowdata",JSON.stringify(rowdata[0]));
						location.href = "detail.jsp";
					}
					break;
				case "updatestatus" :
					if(rowdata.length ==1 ){
						var updatestatusvo ={};
						updatestatusvo.id = rowdata[0].id;
						updatestatusvo.status = rowdata[0].status;
						if( updatestatusvo.status ==1){
							updatestatusvo.status = 0;
						}else if( updatestatusvo.status == 0){
							updatestatusvo.status = 1;
						}
						$.post("question/updatestatus.action",updatestatusvo,function(rs){
							 tableinstance.reload();
						});
					}
					break;
				}
				;
			});
		});
	</script>
</body>
</html>