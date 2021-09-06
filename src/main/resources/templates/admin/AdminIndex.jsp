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
<link rel="Bookmark" href="<%=path%>/static/asserts/favicon.ico" >
<link rel="Shortcut Icon" href="<%=path%>/static/asserts/favicon.ico" />
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
<title>学生列表</title>
</head>
<body>
<% HttpSession s = request.getSession();  %> 
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>用户管理 <span class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20" style="margin-bottom: 14px;">
		<span class="l"> <a class="btn btn-primary radius" onclick="add('添加','<%=path%>/admin/adminAdd')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
		<span class="r">共有数据：<strong>${total}</strong> 条</span> </div>
	<table class="table table-border table-bordered table-sort table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">用户列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th>ID</th>
				<th>姓名</th>
                <th>身份</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${adminList}" var="c" varStatus="status">
			<tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td>${c.username}</td>
				<td>${c.role}</td>
				<td class="td-manage">
					<a style="text-decoration:none" class="ml-5" onClick="edit('编辑',${c.id})" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
					<a title="删除" href="javascript:;" onclick="del(this,${c.id})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/static/asserts/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/static/asserts/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/asserts/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{"orderable":false,"aTargets":[0,1,2,4]}// 制定列不参与排序
		]
	});



/*-编辑*/
function edit(title,id){
	var name="<%=session.getAttribute("username")%>";
	if(name == null || name == ""){
		layer.confirm('你登录已失效，请前往登录！', {
			  btn: ['关闭'] //按钮
			  ,cancel: function(index, layero){
			    //取消操作，点击右上角的X
			  }
			}, function(){
				location.reload();
			});
	}else{
	    var index = layer.open({
		type: 2,
		title: title,
		content:"<%=path%>/admin/adminEdit/"+id,
		end: function () {
             location.reload();
          }
	     });
	     layer.full(index);
	}
}


/*-添加*/
function add(title,url){
	var name="<%=session.getAttribute("username")%>";
	if(name == null || name == ""){
		layer.confirm('你登录已失效，请前往登录！', {
			  btn: ['关闭'] //按钮
			  ,cancel: function(index, layero){
			    //取消操作，点击右上角的X
			  }
			}, function(){
				location.reload();
			});
	}else{
	    var index = layer.open({
		type: 2,
		title: title,
		content: url,
		end: function () {
            location.reload();
        }
	});
	layer.full(index);
  }
}

</script>
</body>
</html>