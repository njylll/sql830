layui.use(['table','form', 'layedit', 'laydate'], function(){
    var table = layui.table
        ,form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    table.render({
        elem: '#tb'
        ,url:'/newVersion/superTeacher/courseTime.json'
        ,initSort: {field:'courseName', type:'asc'}
        ,cols: [[
            {field:'courseName', sort: true,title: "课程名"}
            ,{field:'courseDetailId', sort: true,title:"课程详情id"}
            ,{field:'startWeek',edit: 'text',width:100,sort: true,title:"开始周"}
            ,{field:'endWeek',edit: 'text',width:100,sort: true,title:"结束周"}
            ,{field:'dayTime',edit: 'text',width:100,sort: true,title:"日次"}
            ,{field:'sectionStart',edit: 'text',width:100,sort: true,title:"开始节数"}
            ,{field:'sectionEnd',edit: 'text',width:100,sort: true,title:"结束节次"}
            ,{field:'startSchoolYear',edit: 'text',sort: true,title:"开始日期"}
            ,{field:'endSchoolYear',edit: 'text' ,sort: true ,title:"结束日期"}
            ,{field:'startTerm',edit: 'text', width:100,sort: true ,title:"学期"}
            ,{field:'courseCondition', sort: true ,title:"状态"}
            ,{fixed: 'right', title:'操作', toolbar: '#bt', width:100}
        ]]
    });
    //监听行工具事件
    table.on('tool(tb)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除吗？', function(index){
                $.ajax({
                    url: "/newVersion/superTeacher/deleteCourseTime",
                    type: "post",
                    data: {"courseTimeId":data.courseTimeId},
                    success:function (data){
                        layer.msg("删除成功");
                        obj.del();
                        layer.close(index);
                    },
                    error:function (xhr, textStatus, errorThrown){
                        layer.msg("删除失败");
                    }
                })
            });
        }
    });
    //监听单元格编辑
    table.on('edit(tb)', function(obj){
        var value = obj.value //得到修改后的值
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        $.ajax({
            url: "/newVersion/superTeacher/editCourseTime",
            type: "post",
            data: data,
            success:function (data){
                layer.msg("修改成功");
            },
            error:function (xhr, textStatus, errorThrown){
                layer.msg("修改失败");
            }
        })
    });
});
