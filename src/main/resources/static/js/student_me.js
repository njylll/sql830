$("#me-submit").click(function (){
    let uName=$("#user-name").val();
    let uId=$("#user-id").val();
    let uPass=$("#user-password").val();
    let sName=$("#user-real-name").val();
    let ok=false;
    if(uName.length<5)
    {
        layer.msg("昵称不得小于5个字符！");
        return;
    }
    if(isIntNum(uId))
    {
        layer.msg("非法ID");
        return;
    }
    if(uPass!="" && uPass.length<5)
    {
        layer.msg("密码不得小于5个字符！");
        return;
    }
    if((uId==null||uId=="")&&(uName!=null||uName!=""))
    {
        layer.msg("没绑定学号前无法绑定姓名");
        return;
    }

    $.ajax({
        url:"/newVersion/student/me",
        type:"post",
        data: {"username":uName,"studentId":uId,"password":uPass,"studentName":sName},
        success:function (data){
            layer.msg("修改成功，请重新登陆！2s自动跳转");
            //定义开始的秒数
            let second = 2;
            //利用getElementById的方法得到span对象
            function showtime() {
                second--;
                //判断秒数是否为0
                if (second<=0){
                    //为0的话利用location方法href跳转到百度页面
                    window.parent.location.href="/newVersion/login";
                }
            }
            //设置定时器 1秒  执行 一次方法
            setInterval(showtime,1000);
        },
        error:function (xhr, textStatus, errorThrown) {
            layer.msg("修改失败！可能是学号重复");
        }
    })
})
function isIntNum(val){
    let regPos = / ^\d+$/; // 非负整数
    return regPos.test(val);
}

//JS
layui.use(['upload','element', 'layer', 'util','form', 'layedit', 'laydate'], function(){
    var element = layui.element
        ,form = layui.form
        ,upload = layui.upload
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate
        ,util = layui.util
        ,$ = layui.jquery
    //自定义验证规则
    form.verify({
        uName: function(value){
            if(value.length < 3){
                return '用户名至少得3个字符啊';
            }
        }
        ,uPass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });
    //常规使用 - 普通图片上传
    let uploadInst = upload.render({
        elem: '#avatar-upload'
        ,url: '/newVersion/student/me/avatar' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });

            element.progress('demo', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功的一些操作
            //……
            $('#demoText').html(''); //置空上传失败的状态
        }
        //进度条
        ,progress: function(n, elem, e){
            element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
            if(n == 100){
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });
});