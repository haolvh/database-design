function memberInsert(){
			//TODO
			//如何优化这些代码？
			var formData = new FormData();			
			var name = $("#name_is").val();
			var birthday = $("#birthday_is").val();
			var task = $("#task_is").val();
			var birthplace = $("#birthplace_is").val();
			var introduce = $("#introduce_is").val();	
			var QQ = $("#QQ_is").val();
			var img = $("#insertForm").val();
			var files = document.getElementById("fileId").files;
			
			formData.append("peopleName",name);
			formData.append("peopleTask",task);
			formData.append("peopleBirthday",birthday);
			formData.append("peopleBirthplace",birthplace);
			formData.append("peopleIntroduce",introduce);
			formData.append("peopleQQ",QQ);
			formData.append("peopleImage",files[0]);
			console.log(formData);
			var data = $('#insertForm').serializeArray();
			
		 	$.ajax({
				url: "http://localhost:8080/api/people/insert",
				data:formData,
				type:'POST',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				processData: false,
				contentType: false,
				success:function(req){
					console.log(req);
					alert("添加成功!");
					location.reload();
				},
				error:function(xhr,type,errorThrown){
					alert(typeof(errorThrown));
				}
			}); 
		}
		
function memberList(){
	
	$.ajax(
		{
			url : "http://localhost:8080/api/people/list",
			type: "post",
			dataType:'json',
			contentType: "application/json; charset=utf-8",
			data:{},
			
			success:function(data){
	            var length = eval(data["data"]).length;
	
	            for( var i = 0; i < length; i++){
	                var html = "<tr>";
					var arr = ["peopleName","peopleTask","peopleBirthday","peopleBirthplace","peopleIntroduce","peopleQQ"];
					
	                var img = data["data"][i]["peopleImage"];
	                html += "<td><img src="+ img +" alt='加载失败' width='40%'></td>";
					
					for(var j = 0; j < arr.length; j++){
						var textNode = data["data"][i][arr[j]];
						html += "<td>"+ textNode +"</td>";	
					}
					
					var id = data["data"][i]["peopleId"];//通过隐藏的id实现传参
					console.log(id);
					
					html += "<td>"+"<input type='button' name='' id='id' value='删除' onclick='peopleDelete("+id+")' />"+
					"<form method='get'><input type='button' name='' id='update' value='修改' style='background-color:skyblue' onclick='peopleUpdate("
					+JSON.stringify(data["data"][i])+")'/>"+"</td></form>";
	               
					html += "</tr>";
					
	                $("#member").append(html);
	            }
	
				console.log(data);
	
			},
			
			error:function(data){
				alert("加载失败");
			}
		}
	)
}

function peopleDelete(id) {
	var url = "http://localhost:8080/api/people/delete";
	var json = {
	"peopleId":id
    };
	console.log(json);
	$.ajax({
		url : url,
		data: JSON.stringify(json),
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		contentType: "application/json; charset=utf-8",
		success:function(data){
			console.log(data);
			alert("删除成功!");
			location.reload();//刷新页面
		},
		error:function(xhr,type,errorThrown){
			console.log("出了事");
		}
	});
}

function peopleUpdate(data){

	document.getElementById("insertForm").style.display = "none";
	document.getElementById("membersINsert").style.display="block";
	document.getElementById("id_up").value = data.peopleId;//.......
	document.getElementById("img_up").src = data.peopleImage;
	document.getElementById("name_up").value = data.peopleName;
	document.getElementById("task_up").value = data.peopleTask;
	document.getElementById("birthday_up").value = data.peopleBirthday;
	document.getElementById("birthplace_up").value = data.peopleBirthplace;
	document.getElementById("introduce_up").value = data.peopleIntroduce;
	document.getElementById("peopleQQ_up").value = data.peopleQQ;
	
}

function memberUpdate(){
	var url = "http://localhost:8080/api/people/update";
	var formData = new FormData();
	//var id = data.peopleId;
	var id = $("#id_up").val();//这就有id了
	var name = $("#name_up").val();
	var birthday = $("#birthday_up").val();
	var task = $("#task_up").val();
	var birthplace = $("#birthplace_up").val();
	var introduce = $("#introduce_up").val();	
	var QQ = $("#peopleQQ_up").val();
	var img = $("#insertForm").val();
	var files = document.getElementById("imgId").files;
	formData.append("peopleId",id);
	formData.append("peopleName",name);
	formData.append("peopleTask",task);
	formData.append("peopleBirthday",birthday);
	formData.append("peopleBirthplace",birthplace);
	formData.append("peopleIntroduce",introduce);
	formData.append("peopleQQ",QQ);
	formData.append("peopleImage",files[0]);
	console.log(formData);//files != null
	if(true){
		console.log(formData);
		$.ajax(
			{
				url : url,
				//dataType:FormData,
				data:formData,
				type:'POST',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				processData: false,
				contentType: false,
				success:function(data){	 
					alert("修改成功");
					location.reload();
					console.log(data);	
				},
				error:function(data){
					alert("修改失败!");
				}
			}
		) 
	}
}

	


