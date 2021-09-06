<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<% String path = request.getContextPath(); %>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/static/asserts/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/asserts/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/asserts/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/asserts/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/asserts/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改信息</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="formEdit" action="<%=path%>/admin/adminEditSubmit" method="post">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" style="width: 400px;" class="input-text" value="${admin.username}" placeholder="" id="username" name="username">
				<input type="hidden" class="input-text" value="${admin.userid}" placeholder="" id="userid" name="userid">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" style="width: 400px;" class="input-text" autocomplete="off" value="${admin.password}" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>身份：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select style="width: 390px;" class="select" size="1" name="role">
					<option value="学生">学生</option>
					<option value="教师">教师</option>
				</select>
			</div>
		</div>




	<div class="row cl">
			<div style="margin: 30px 0px 0 375px;" class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>


<button style="VISIBILITY: hidden" class="btn btn-primary btn-lg"  data-toggle="modal" data-target="#myModal2" id="dialog"></button>
    
   <!-- 模态框（Modal） -->
   <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					提示
				</h4>
			</div>
			<div class="modal-body">
				<p>${msg}</p>
			</div>
			<div class="modal-footer">
				<button type="button" onClick="layer_close();" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal -->
     </div>



<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="<%=path%>/static/asserts/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/asserts/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/asserts/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/static/asserts/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<style>
    label.error{
        position: absolute;
        right: 300px !important;
        top: 5px;
        color: #ef392b;
        font-size: 12px;
    }
</style>
</body>
</html>