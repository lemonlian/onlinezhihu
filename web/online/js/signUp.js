$(function(){
    $("button").click(function(){
        if($("#userName").val()&&$("#userPassword").val()){    
            $.ajax({
                type:"POST",
                url:"/user/UserRegisterServlet",
                dataType:"json",
                data:{
                    userName : $("#userName").val(),
                    userPassword : $("#userPassword").val()
                },
                success:function(json){
                    if(json.code){
                        alert(json.msg);
                        // $.cookie('userId', 'json.data.userId');
                        // $.cookie('userName','json.data.userName');
                        // $.cookie('userPassword','json.data.userPassword');
                        // $(location).attr('href','login.html');
                    }
                },
                error:function(jqXHR){
                    alert("发生错误");
                }
            });
            } else alert("请填好表单再提交");
       
    });
});