function ajax_login() {
    $("#loginMsg").text("");
    $("#login-img").css("display","")
    let user = $("#user-name-label").val();
    let psw=$("#password-label").val();
    let role=getCheckBoxVal("role");
    $.ajax({
        url:"/newVersion/gologin",
        type:"post",
        data:{"username": user+'|'+role,"password": psw},
        success:function (data){
            $("#loginMsg")
                .html(data.msg);
            $("#to-panel").attr("href","www.baidu.com")
            document.getElementById("to-panel").click();
        },
        error:function (xhr, textStatus, errorThrown) {
            let res= JSON.parse(xhr.responseText)
            $("#loginMsg")
                .html(res.msg);
            $("#login-img").css("display","none")
        }
    })
}


function doRealRequest(){
    $("#to-panel").click();
}
function getCheckBoxVal(nameVal){
    let inputs=document.getElementsByName(nameVal);
    let checkVal=[];
    let k=0;//用来作checkVal数组的下标
    for(let i=0, len=inputs.length;i<len;i++){
        if(inputs[i].checked){
            checkVal[k]=inputs[i].value;
            k++;
        }
    }
    return checkVal;
}
// function checkUser(){
//     $("#user").blur(function (){
//         let user=$("#user").val();
//         $.ajax({
//             url: "/check/userCheck",
//             type: "post",
//             data: {"originUser":user},
//             success:function (){
//                 console.log("正确");
//                 $("#user-check").css("color","green");
//                 $("#user-check").text("正确");
//             },
//             error:function (){
//                 console.log("错误");
//                 $("#user-check").css("color","red");
//                 $("#user-check").text("该用户名不存在");
//             }
//
//         })
//     })
// }
//
// $(document).ready(function (){
//     checkUser();
// })
