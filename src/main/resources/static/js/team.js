function teamInsert(){
	document.getElementById("teamUpdatediv").style.display="none";
	var formData = new FormData();
	var teamname = $("#teamname_is").val();
	var teamIntroduce = $("#teamIntroduce_is").val();
	var managerName = $("#managerName_is").val();
	var managerBirthday = $("#managerBirthday_is").val();
	var managerBirthplace = $("#managerBirthplace_is").val();
	var managerIntroduce = $("#managerIntroduce_is").val();
	var QQ = $("#QQ_is").val();
	//var img = $("#insertForm").val();
	var files = document.getElementById("img_is").files;
	formData.append("teamName", teamname);
	formData.append("teamIntroduce", teamIntroduce);
	formData.append("managerName", managerName);
	formData.append("managerBirthday",managerBirthday);
	formData.append("managerBirthplace", managerBirthplace);
	formData.append("managerIntroduce",managerIntroduce);
	formData.append("managerQQ", QQ );
	formData.append("managerImage", files[0]);
	
	
	$.ajax({
		url: "http://localhost:8080/api/team/insertBallTeam",
		data:formData,
		type:'POST',//HTTP请求类型
		timeout:10000,//超时时间设置为10秒；
		processData: false,
		contentType: false,
		success:function(req){
			console.log(req);
			location.reload();
		},
		error:function(xhr,type,errorThrown){
			alert(typeof(errorThrown));
		}
	}); 
	
}
function teamDelete(id){
	var url = "http://localhost:8080/api/team/deleteActivity"
	var json = {
	"teamId":id
	};
	$.ajax({
		url : url,
		data: JSON.stringify(json),
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		contentType: "application/json; charset=utf-8",
		success:function(data){
			console.log(data);
			alert("删除成功!");
			location.reload();
		},
		error:function(xhr,type,errorThrown){
			console.log("1");
		}
	});
}
function Tteam(){
		var url = "http://localhost:8080/api/team/list";
		$.ajax({
			url : url,
			type: "post",
			dataType:'json',
			contentType: "application/json; charset=utf-8",
			data:{},
			success:function(data){
				 var length = eval(data["data"]).length;
				  for( var i = 0; i<length; i++){
					  var teamName = data["data"][i]["teamName"];
					  var id = data["data"][i]["teamId"];
					  var html="<tr>";
					  html += "<td>"+ teamName +"</td>";
					  var textNode = data["data"][i]["teamIntroduce"];
					  html += "<td>"+ textNode +"</td>";
					  html += "<td>"+"<input type='button' value='查看队长' style='width:80px;background-color:lightgreen' onclick='teamerList(\""+teamName+"\")'/> "
					  +"<input type='button' name='' id='id' value='删除' style='width:80px;' onclick='teamDelete("+id+")' />"+
					  "<input type='button' style='width:80px;background-color:skyblue' value='修改球队' onclick='teamUpdate("+JSON.stringify(data["data"][i])+")'/> "+"</td>";
					  html += "</tr>";
					  $("#teamList").append(html);
				  }
				  }
			}
		)
}
	
	function teamerList(teamName){
		var url = "http://localhost:8080/api/team/manager";
		var json = {
		"teamName":teamName
		};
	$.ajax({
		url : url,
		data: JSON.stringify(json),
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		contentType: "application/json; charset=utf-8",
		success:function(data){
			var html = "<tr>";
			var textNode = data["data"]["managerImage"]["pictureUrl"];
			html += "<td><img src="+textNode+"  alt='加载失败' width='40%'></td>";
			var textNode = data["data"]["manager"]["peopleName"];
			html += "<td>"+ textNode +"</td>";
			var textNode = data["data"]["manager"]["peopleTask"];
			html += "<td>"+ textNode +"</td>";
			var textNode = data["data"]["manager"]["peopleBirthday"];
			html += "<td>"+ textNode +"</td>";
			var textNode = data["data"]["manager"]["peopleBirthplace"];
			html += "<td>"+ textNode +"</td>";
			var textNode = data["data"]["manager"]["peopleIntroduce"];
			html += "<td>"+ textNode +"</td>";
			var textNode = data["data"]["manager"]["peopleQQ"];
			html += "<td>"+ textNode +"</td>";
			$("#teamer").append(html);
		},
		error:function(xhr,type,errorThrown){
			console.log("1");
		}
	});
		
	}
	
function teamUpdate(data){
	var url = "http://localhost:8080/api/team/manager";
	var json = {
	"teamName":data.teamName
	};
	document.getElementById("teamUpdatediv").style.display="block";
	document.getElementById("teamInsertdiv").style.display="none";
	document.getElementById("teamId").value = data.teamId;
	document.getElementById("teamname_up").value = data.teamName;
	document.getElementById("teamIntroduce_up").value = data.teamIntroduce;
	$.ajax({
		url : url,
		data: JSON.stringify(json),
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		contentType: "application/json; charset=utf-8",
		success:function(data){
			//document.getElementById("teamUpdatediv").style.display="block";
			//document.getElementById("teamInsertdiv").style.display="none";
			//document.getElementById("teamId").value = data.teamId;
			//document.getElementById("teamname_up").value = data.teamName;
			//document.getElementById("teamIntroduce_up").value = data.teamIntroduce;
			document.getElementById("managerName_up").value = data["data"]["manager"]["peopleName"];
			document.getElementById("img_h").src = data["data"]["managerImage"]["pictureUrl"];
			document.getElementById("managerBirthday_up").value = data["data"]["manager"]["peopleBirthday"];
			document.getElementById("managerBirthplace_up").value = data["data"]["manager"]["peopleBirthplace"];
			document.getElementById("managerIntroduce_up").value = data["data"]["manager"]["peopleIntroduce"];
			document.getElementById("QQ_up").value = data["data"]["manager"]["peopleQQ"];
		},
		error:function(xhr,type,errorThrown){
			console.log("1");
		}
	});
	/* document.getElementById("teamUpdatediv").style.display="block";
	document.getElementById("teamInsertdiv").style.display="none";
	document.getElementById("teamId").value = data.teamId;
	document.getElementById("teamname_up").value = data.teamName;
	document.getElementById("teamIntroduce_up").value = data.teamIntroduce;
	document.getElementById("managerName_up").value = data.managerName;
	document.getElementById("img_h").src = data.managerImage;
	document.getElementById("managerBirthday_up").value = data.managerBirthday;
	document.getElementById("managerBirthplace_up").value = data.managerBirthplace;
	document.getElementById("managerIntroduce_up").value = data.managerIntroduce;
	document.getElementById("QQ_up").value = data.managerQQ; */
}

function teamAllUpdate(){
	var url = "http://localhost:8080/api/team/updateBallTeam";
	var formData = new FormData();
	var teamId = $("#teamId").val();
	var teamName = $("#teamname_up").val();
	var teamIntroduce = $("#teamIntroduce_up").val();
	var managerName = $("#managerName_up").val();
	var managerBirthday = $("#managerBirthplace_up").val();
	var managerBirthplace = $("#managerBirthplace_up").val();
	var managerIntroduce = $("#managerIntroduce_up").val();
	var managerQQ = $("#QQ_up").val();
	var files = document.getElementById("img_up").files;
	formData.append("teamId",teamId);
	formData.append("teamName",teamName);
	formData.append("teamIntroduce",teamIntroduce);
	formData.append("managerName",managerName);
	formData.append("managerBirthday ",managerBirthday );
	formData.append("managerBirthplace", managerBirthplace);
	formData.append("managerIntroduce",managerIntroduce);
	formData.append("managerQQ",managerQQ);
	formData.append("managerImage",files[0]);
	if(files !=null){
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
					alert("修改失败了");
				}
			}
		) 
	}
}
function showTeamInsert(){
	document.getElementById("teamInsertdiv").style.display="block";
	document.getElementById("teamUpdatediv").style.display="none";
}