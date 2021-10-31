layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

});

function doCreate()
{
    let cId=$("#courseId").val();
    let cName=$("#courseName").val();
    let college=$("#collegeName").val();
    let creditH=$("#creditHour").val();
    let credit=$("#credit").val();
    let type=$("#courseType").val();
    if(cId==null||cId=="")
    {
        layer.msg("课程id不能为空");
        return;
    }
    if(cName==null||cName=="")
    {
        layer.msg("课程名不能为空");
        return;
    }
    $.ajax({
        url: "/newVersion/superTeacher/createModule",
        type: "post",
        data: {"courseId":cId,"courseName":cName,"collegeId":college, "creditHour":creditH,"credit":credit,"courseType":type},
        success:function (data){
            layer.msg("添加成功！");
            $("#reset-bt").click();
        },
        error:function (xhr, textStatus, errorThrown){
            let res= JSON.parse(xhr.responseText);
            layer.msg("添加失败，原因: "+res.msg);
        }
    })

}