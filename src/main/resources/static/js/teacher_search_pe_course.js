layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#tb'
        ,url:'/newVersion/teacher/pe.json'
        ,initSort: {field:'courseId', type:'asc'}
        ,cols: [[
            {field:'courseId', width:150, sort: true,title: "课程id"}
            ,{field:'courseName', width:120, sort: true,title:"课程名"}
            ,{field:'teacherName',sort: true,title:"教师"}
            ,{field:'teachingLocation',sort: true,title:"教室"}
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
                content: '/newVersion/teacher/detail/'+data.courseDetailId
            })
        }
    });

});