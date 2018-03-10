$(function(){
    $.ajax({
        type:"GET",
                url:"/topic/GetAllTopicDetailedServlet",
                dataType:"json",
                success:function(json){
                    if(json.code){
                       // alert("aaa");
                       // var data = json.data;
                        let str='';
                        for(let i=0;i<json.data.length;i++){
                            str+=`<tr>
							<td>${json.data[i].topicTitle}</td>
							<td>
								${json.data[i].topicContent}
							</td>
							<td>
								${json.data[i].topicPublishTime}
							</td>
						</tr>`
                       }
                       $(".theTopic").append(str);

                        
                        // $(".theTopic").each(function(i){
                        //     $(this).find(".topicTitle").append(json.data.topicTitle);
                        //     $(this).find(".topicContent").append(json.data.topicContent);
                        //     $(this).find(".topicPublishTime").append(json.data.topicPublishTime);
                        // });
                    }
                },
                error:function(jqXHR){
                    alert("发生错误");
                }
    });
    $(".topicPost").click(function(){
        $.ajax({
            type:"POST",
            url:"/topic/addTopicServlet",
            dataType:"json",
            data:{
                boardId : 1,
                topicTitle : $(".ttopicTitle").val(),
                topicContent : $(".ttopicContent").val()

            },
            success:function(json){
                if(json.code){
                    alert(json.msg);                    
                }
            },
            error:function(jqXHR){
                alert("发生错误");
            }
        });          
    });
    
});