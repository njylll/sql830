layui.use(['table','form', 'layedit', 'laydate'], function(){
    var table = layui.table
        ,form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
//下拉框选择
form.on('select(courseName)', function(data){
    console.log(data);
    $.ajax({
        url: "/newVersion/superTeacher/searchCourseDetailId",
        type: "post",
        data: {"courseName":data.value},
        success:function (data){
            console.log(data);
            let json=JSON.parse(data)
            $("#courseDetailId option").remove();
            for(let i of json)
            {
                $("#courseDetailId").append("<option value=\'"+ i +"\'>"+ i +"</option>");
            }
            form.render('select');
        },
        error:function (xhr, textStatus, errorThrown){
            layer.msg("哦不，查找详情失败了")
        }
    })
});
});
function doCreateTime()
{
    let courseName=$("#courseName").val()
    let courseDetailId=$("#courseDetailId").val()
    let startWeek=$("#startWeek").val()
    let endWeek=$("#endWeek").val()
    let startSection=$("#sectionStart").val()
    let endSection=$("#sectionEnd").val()
    let dayTime=$("#dayTime").val()
    if(courseDetailId==null||courseDetailId=="")
    {
        layer.msg("详情id不能为空");
        return;
    }
    if(dayTime>7||dayTime<1)
    {
        layer.msg("日次必须1-7");
        return;
    }

    $.ajax({
        url: "/newVersion/superTeacher/createCourseTime",
        type: "post",
        data: {"courseDetailId":courseDetailId,"dayTime":dayTime,"startWeek":startWeek
            ,"endWeek":endWeek,"sectionStart":startSection,"sectionEnd":endSection},
        success:function (data){
            layer.msg("添加成功");
        },
        error:function (xhr, textStatus, errorThrown){
            layer.msg("添加失败");
        }
    })
}
