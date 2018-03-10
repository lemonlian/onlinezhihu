$(function(){
    $("button").click(function(){
        if($("#userName").val()&&$("#userPassword").val()){    
            $.ajax({
                type:"POST",
                url:"/user/UserRegisterServlet",
                dataType:"json",
                data:{
                    userName : $("#userName").val(),
                    userPassWord : $("#userPassword").val()
                },
                success:function(json){
                    if(json.code){
                        //alert("aaa");
                        $(location).attr('href','demo.html');
                    }
                },
                error:function(jqXHR){
                    alert("发生错误");
                }
            });             
            } else alert("请填好表单再提交");
    });
});