layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#tb'
        ,url:'/newVersion/student/pcc.json'
        ,initSort: {field:'courseId', type:'asc'}
        ,cols: [[
            {field:'courseId', width:150, sort: true,title: "课程id"}
            ,{field:'courseName', width:120, sort: true,title:"课程名"}
            ,{field:'teacherName',sort: true,title:"教师"}
            ,{field:'teachingLocation',sort: true,title:"教室"}
            ,{fixed: 'right', title:'操作', toolbar: '#bt', width:200}
        ]]
    });

    //监听行工具事件
    table.on('tool(tb)', function(obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'info') {
                layer.open({
                    type: 2,
                    area: ['400px', '200px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: '/newVersion/student/detail/'+data.courseDetailId
                })
        }
        else if(obj.event === 'del'){
            layer.confirm('真的要退选吗？', function(index){
                $.ajax({
                    url: "/newVersion/student/deleteStudentCourse",
                    type: "post",
                    data: {"courseDetailId":data.courseDetailId},
                    success:function (data){
                        layer.msg("退选成功");
                        obj.del();
                        layer.close(index);
                    },
                    error:function (xhr, textStatus, errorThrown){
                        layer.msg("退选失败");
                    }
                })
            });
        }
    });
});
