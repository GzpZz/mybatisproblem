<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="/problem/">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>问题记录系统</title>
<link rel="stylesheet" href="./layui/css/layui.css">
<style>
#uploadimgscontainer img{
	width: 80px;
	height:80px;
	border-radius:5px;
	border:1px solid #333;
	margin:5px 5px;
}
#deletearea{
	width:600px;
	height:100px;
	border-radius:5px;
	border:1px solid red;
	margin:10px auto;
}
#deletearea:after{
	content: "删除区域"
}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		 <%@ include file="header.jsp" %>
		<!-- 内容主体区域 1024 -->
		<div class="layui-container">
			<div style="padding: 15px;">

				<!-- 
    问题描述==》textarea
   问题类型 ==》 select。
   图片描述 ===》精美的上传按钮。
 
     -->
				<form class="layui-form"  >
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">描述</label>
						<div class="layui-input-block">
							<textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">分类</label>
						<div class="layui-input-inline">
							<select name="type" lay-verify="required">
								<option value="java">java</option>
								<option value="Layui">Layui</option>
								<option value="Javascript">Javascript</option>
								<option value="javaIO">javaIO</option>
								<option value="java线程">java线程</option>
								<option value="javascript拖动">javascript拖动</option>
								<option value="javascript事件">javascript事件</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">附加图片</label>
						<div class="layui-input-block">
							 <button id="addimgbtn" class="layui-btn layui-btn-xs layui-icon layui-icon-add-1">&nbsp;</button>
							 <input type="file" id="fileinput"  style="display:none" />		
							 <div id="uploadimgscontainer" >
							 	
							 </div>	
							 <div id="deletearea">
							 
							 </div>			 
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="insertbtn">提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="./layui/layui.js"></script>
	<script>
		//Demo
		layui.use([ 'form',"jquery","layer" ], function() {
			  var form = layui.form;
			  var $ = layui.jquery;
			  var layer = layui.layer;
			 
			 //监听提交
			 form.on('submit(insertbtn)', function(data){
				 //在提交之间。将图片路径放入form表单中。
				 var qimg ="";
				 for(var i=0;i<imglist.length;i++){
					 qimg = qimg.concat(imglist[i]).concat("#");
				 }
				 data.field.qimg = qimg;
				 $.post("question/insert.action",data.field,function(rs){
					 layer.msg( rs.msg ,function(){
						 if(rs.code != 200){
							 location.href="login.html";
						 }
					 });
					
				 });
			     return false;
			 }); 
			 
			 $("#addimgbtn").click(function(e){
				 $("#fileinput").click();
				  return false;
			 });
			 
		
			 $("#fileinput").change(function(e){
				 //如果输入框内容为空。不触发。
				  if( e.target.value =="")return;
				  var formdata = new FormData();
				  formdata.append("file",e.target.files[0]);
				  fileUpload(formdata,function(rs){
					  if(rs.code ==200){
						  var imgs = rs.data;
						  imgs = imgs.split("#");
						  imgs.map(function(imgpath){
							   if(imgpath){
								   addImgs(imgpath);
								   e.target.value = "";//清空输入框内容。
							   }
						  });
					  }
				  });
			 });
			 
			 var imglist = [];//有几张图片就有几个元素。
			 //添加图片。
			 function addImgs(imgpath){
				 //1 在页面添加一个img标签。
				 var img = $("<img >");
				 img.attr("src",imgpath);// 后台返回的部分路径。不带http:// 。
				 img.attr("mysrc",imgpath);//把原始路径保存好。用于后续匹配。
				 img.appendTo("#uploadimgscontainer");
				 //2 添加到数组中。保存到数据库。
				 imglist.push(imgpath);
			 }
			 //移除图片。
			 function deleteImgs(imgdom){
				//1 页面dom元素移除。
				   $( imgdom ).remove();
				//2 imglist 移除。
				//如果是用 原始 src。拿到的是http开头的全部绝对路径。肯定匹配不成功。
				//var src = $( imgdom ).attr("src");
				var mysrc =  $( imgdom ).attr("mysrc");
				for(var i =0;i<imglist.length;i++){
					//
					if(imglist[i] == mysrc){
						imglist.splice(i,1);//删除一个。
					     break;
					}
				}
				console.info("剩下的",imglist);
				 
			 }
			 
			 function fileUpload(formdata,callback){
				 $.ajax({
	 				 type:"POST",
	 				 data:formdata,
	 				 url:"img/upload.action",
	 				 processData:false,
	 				 contentType:false,
	 				 success:function(rs){
	 					callback(rs);
	 				 }
	 			});
			 }
			 var status = 0;
			 //投放事件。
			 $("#deletearea").on("dragover",function(e){
				 console.info(e);
				 status = 1;
			 });
			 //拖动元素
			 $("#uploadimgscontainer").on("dragend",function(e){
					if( e.target.nodeName =="IMG" ){
						if(status ==1 ){
							 //一定是先进入目标区域。才停止的拖放。而不是未进入目标区域。
							 deleteImgs(e.target);
						}
						status = 0;
					}
			 });
			 $("#uploadimgscontainer").click(function(e){
				 //如果是一个图片。
				if( e.target.nodeName =="IMG" ){
					//展示一张大图。
					var src = e.target.src;
					layer.open({
						  title: '在线调试',
						  area:['800px', '500px'],
						  content: "<img src='"+src+"' >"
						});   
				}
			 });
			 
			 
		});
	</script>
</body>
</html>