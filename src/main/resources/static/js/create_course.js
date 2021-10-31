layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#startYear'
    });
    laydate.render({
        elem: '#endYear'
    });

});
function submit_pcc(){
    let cId=$("#courseId").val();
    let start=$("#startYear").val();
    let end=$("#endYear").val();
    let term=$("#term").val();
    let teacherName=$("#teacherName").val();
    let location=$("#location").val();
    if(cId==null||cId=="")
    {
        layer.msg("课程名不能为空");
        return;
    }
    $.ajax({
        url: "/newVersion/superTeacher/pcc",
        type: "post",
        data: {"courseId":cId,"start":start,"end":end,"term":term,"teacherName":teacherName,"location":location},
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