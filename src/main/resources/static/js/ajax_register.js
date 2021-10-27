$("#user").blur(function (){
    let user=$("#user").val();
    let psw=$("#psw").val();
    if(user.trim()!=="")
    {
        $("#user-check").text("");
    }
    if(user.trim()==="")
    {
        $("#user-check").css("color","red");
        $("#user-check").text("用户名不能为空");
        $("#register-btn").addClass("layui-btn-disabled");
    }
    else
    {
        $.ajax({
            url: "/newVersion/ajax_register",
            type: "post",
            data: {"originUser":user},
            success:function (data){
                $("#register-btn").attr("disabled",false);
                $("#user-check").css("color","green");
                $("#user-check").text("用户名可用");
                if(psw.trim()!=="")
                    $("#register-btn").removeClass("layui-btn-disabled");
            },
            error:function (xhr, textStatus, errorThrown){
                $("#register-btn").attr("disabled",true);
                $("#user-check").css("color","red");
                $("#user-check").text("用户名已存在");
                $("#register-btn").addClass("layui-btn-disabled");
            }
        })
    }

})
$("#psw").blur(function (){
    let psw=$("#psw").val();
    if(psw.trim()==="")
    {
        $("#psw-check").css("color","red");
        $("#psw-check").text("密码不能为空");
        $("#register-btn").addClass("layui-btn-disabled");
    }
    else
    {
        $("#psw-check").text("");
        $("#register-btn").removeClass("layui-btn-disabled");
    }
})

$("#iCode").blur(function (){
    let code=$("#iCode").val();
    $.ajax({
        url: "/newVersion/ajax_register_code",
        type: "post",
        data: {"iCode":code},
        success:function (data){
            $("#iCode-check").css("color","green");
            $("#iCode-check").text("邀请码可用");
        },
        error:function (xhr, textStatus, errorThrown){
            $("#iCode-check").css("color","red");
            let j=$.parseJSON(xhr.responseText)
            $("#iCode-check").text(j.msg);
        }
    })
})

function check()
{
    let user=$("#user").val();
    let psw=$("#psw").val();
    let ok=true;

    if(user.trim()==="")
    {
        $("#user-check").css("color","red");
        $("#user-check").text("用户名不能为空");
        $("#register-btn").addClass("layui-btn-disabled");
        ok=false;
    }
    if(psw.trim()==="")
    {
        $("#psw-check").css("color","red");
        $("#psw-check").text("密码不能为空");
        $("#register-btn").addClass("layui-btn-disabled");
        ok=false;
    }

    return ok;


}
