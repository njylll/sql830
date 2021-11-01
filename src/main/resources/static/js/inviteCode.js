$("#code-bt").click(function (){
    let tCode=$("#tCode").val();
    let sCode=$("#sCode").val();
    $.ajax({
        url: "/newVersion/superTeacher/generateCode",
        type: "post",
        data: {"tNum":tCode,"sNum":sCode},
        success:function (data){
            data=JSON.parse(data);
            let res="<br><br><center>教师邀请码：";
            let count=0
            for(let i of data.tCode)
            {
                count++;
                res+=(i+",");
                if(count%5==0)
                    res+="<br>";
            }
            res+="<br>super教师邀请码：";
            count=0;
            for(let i of data.stCode)
            {
                count++;
                res+=(i+",");
                if(count%5==0)
                    res+="<br>";
            }
            res+="<center>";
            console.log(data);
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                maxmin: true,
                shadeClose: true,
                area: ['500px', '200px'],
                fixed: false, //不固定
                skin: 'yourclass',
                content: res
            });
        },
        error:function (xhr, textStatus, errorThrown){
            let res= JSON.parse(xhr.responseText);
            layer.msg("生成失败，原因: "+res.msg);
        }
    })
})