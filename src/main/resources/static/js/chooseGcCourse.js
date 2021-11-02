var chooseSet=new Set();
layui.use(['table','form', 'layedit', 'laydate'], function(){
    let table = layui.table
        ,form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    //刷新select
    $.ajax({
        url: "/newVersion/student/pccInfo.json",
        type: "post",
        data: {"courseType":"通识课"},
        success:function (data){
            let json=JSON.parse(data);
            let a1=$("#courseId");
            let a2=$("#courseName");
            let a3=$("#teacherName");
            let a4=$("#teachingLocation");
            for(let i of json.courseIdList)
            {
                a1.append("<option value='"+i.courseId+"'>"+i.courseId+"</option>");
            }
            for(let i of json.courseNameList)
            {
                a2.append("<option value='"+i.courseName+"'>"+i.courseName+"</option>");
            }
            for(let i of json.teacherName)
            {
                a3.append("<option value='"+i.teacherName+"'>"+i.teacherName+"</option>");
            }
            for(let i of json.teachingLocation)
            {
                a4.append("<option value='"+i.teachingLocation+"'>"+i.teachingLocation+"</option>");
            }
            form.render('select');
        },
        error:function (xhr, textStatus, errorThrown){
        }
    })
    //查询操作
    form.on('select', function(data){
        let courseId=$("#courseId").val();
        let courseName=$("#courseName").val();
        let teacherName=$("#teacherName").val();
        let teachingLocation=$("#teachingLocation").val();
        table.reload('tb', {
            url: "/newVersion/student/pcc",
            method: "post",
            where: {"courseId":courseId,"courseName":courseName,"teacherName":teacherName,
                "teachingLocation":teachingLocation,"courseType":"通识课"},
        });

    });

    table.render({
        elem: '#tb'
        ,url:'/newVersion/student/GCcourseModule.json'
        ,initSort: {field:'courseId', type:'asc'}
        ,cols: [[
            {type:'checkbox'}
            ,{field:'courseId', width:100, sort: true,title: "课程id"}
            ,{field:'courseName', width:120, sort: true,title:"课程名"}
            ,{field:'startSchoolYear',sort: true,title:"开始日期"}
            ,{field:'endSchoolYear',sort: true,title:"结束日期"}
            ,{field:'startTerm',sort: true,title:"学期"}
            ,{field:'teacherName',sort: true,title:"教师"}
            ,{field:'teachingLocation',sort: true,title:"教室"}
            ,{field:'creditHours',sort: true,title:"学时"}
            ,{field:'credit', sort: true ,title:"学分"}
        ]]
    });
    table.on('checkbox(tb)', function(obj){
        let data=obj.data;//选中行的相关数据
        let checked=obj.checked;//当前是否选中状态
        let type=obj.type//如果触发的是全选，则为：all，如果触发的是单选，则为：one
        //console.log(table.getData("tb"));
        //全不选
        if(type=="all" && !checked)
        {
            chooseSet.clear();
        }
        //全选
        else if(type=="all" && checked)
        {
            for(let d of table.getData("tb"))
            {
                chooseSet.add(d.courseDetailId)
            }
        }
        else if(type=="one" && checked)
        {
            chooseSet.add(data.courseDetailId);
        }
        else if(type=="one" && !checked)
        {
            chooseSet.delete(data.courseDetailId);
        }
        console.log(chooseSet)
    });

});
function submit_choice()
{
    let res={courseDetailId:[],courseType: "GC"};
    for(let i of chooseSet)
    {
        res.courseDetailId.push(i);
    }
    $.ajax({
        url: "/newVersion/student/chooseCourse",
        type: "post",
        data: res,
        success:function (data){
            layer.msg("添加成功！");
            setInterval(refresh,1000);
        },
        error:function (xhr, textStatus, errorThrown){
            let res= JSON.parse(xhr.responseText);
            layer.msg("添加失败，原因: "+res.msg);
        }
    })
}
let second = 1;
function refresh() {
    second--;
    //判断秒数是否为0
    if (second<=0){
        //为0的话利用location方法href跳转到百度页面
        location.reload();
    }
}
