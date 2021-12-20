<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Deployment</title>
<style type="text/css">
	.title{
	    margin-bottom: 6px;
	    font-weight: 100;
	    color: #000;
	 	font-size: 27px;
	}

   .deploymentbox{
   		padding : 24px 18px 0px 18px;
   }
   .datagrid{
   		border-bottom-left-radius: 0;
	    border-bottom-right-radius: 0;
	    overflow: auto;
	    flex: 1 1 auto;
	    margin-top: 30px;
   		border-collapse: separate;
	    border: 1px solid #ccc;
	    border-radius: .125rem;
	    background-color: #fff;
	    color: #575757;
	   /*  max-width: 100%; */
	    width: 1400px;
   
   }
   .datagrid-row{
   		background-color: #fafafa;
	    border-top: none;
	    border-bottom: 1px solid #dedede;		
   		width: 100%;
   		height: 37px;
   		display: flex;
   		align-items: center;
   	
   }
   .datagrid-title{  	
   		flex:1;	
   		color: #575757;
   		font-size: 12px;
   		font-weight: 600;
   		border-left: 1px solid #dedede;
   		height: 24px;
   		line-height:24px;
   		
   		
   }
   .datagrid-row-data{
   		display: flex;
   		border-bottom: 1px solid #dedede;
   		width:1400px;
   		position: relative;
   
   }
   .datagrid-data{
   		color: #575757;
   		font-size: 13px;
   		flex:1;
   		height: 40px;
   		line-height:40px;
   		width: 100%;
   		
   	
   }
   .datagrid-button1{
   		background: url( "/img/deploymentlogo/deploymentbtn1.png" ) no-repeat;
        border: none;
        width: 20px;
        height: 20px;
        cursor: pointer;
        margin-top: 10px;

   
   }
   .datagrid-button2{
   		background: url( "/img/deploymentlogo/deploymentbtn2.png" ) no-repeat;
        border: none;
        width: 20px;
        height: 20px;
        cursor: pointer;
         margin-top: 10px;
   }
   .datagrid-data > span{
   		color: #0077b8;
   		font-size: 12px;
   
   }
   .error{
   		background: url( "/img/deploymentlogo/error.png" ) no-repeat;
        border: none;
        width: 20px;
        height: 20px;
        cursor: pointer;
        margin-top: 10px;
   
   }
    .error-msg{
	   	position: absolute;
	    inset: 0px auto auto 0px; 
	    width:360px;
	    height:110px; 
	    padding: 1rem;
	    max-height: 20rem;
	    border: 1px solid #999;
	    border-radius: .125rem;
	    background-color: #fff;
	    z-index: 1;
	    top: -10px;
	    left: 600px;
   }
   
   
</style>
<script type="text/javascript">
fetch('/deployments')
.then((res)=>res.json())
.then((data)=> {
	console.log(data);
	let contentArr = data.content;
	let jsonObjArr = new Array();
	
	for (i in contentArr) {
		let jsonObj = {}; 
		for(j in contentArr[i])
		{
			//key : value
			if(j == 'inputs')
			{
				for(k in contentArr[i][j])
				{
					//inputs key
					//console.log(k + " : " + contentArr[i][j][k])
					jsonObj[k] = contentArr[i][j][k];
				}
			}
			else{
				//console.log(j + " : " + contentArr[i][j])
				jsonObj[j] = contentArr[i][j];
			}
		}
		//console.log("-----------------------------------------")
		jsonObjArr[i] = jsonObj;
	}
	console.log(jsonObjArr)
	let html = "";
	let out = "";
	let outdata  = document.getElementById('outdata');
	
	//var projectname='<c:out value="{$projectname}"/>';
	//alert(projectname);
	for ( i in jsonObjArr){
		console.log("projectname"+jsonObjArr[i].projectname);
		html += "<div class='datagrid-row-data' role='row'>";
		html += 	"<div style='width: 38px; text-align: center;'>";
		html += 		"<input type='button' class='datagrid-button1'></button>";
		html += 	"</div>";
		html += 	"<div style='width: 40px; text-align: center;'>";
		html += 		"<input type='button' class='datagrid-button2'></button>";
		html += 	"</div>";	
		html += 	"<div class='datagrid-data' id='logo'>&nbsp;&nbsp;&nbsp;<img src='/img/deploymentlogo/deploymentlogo.png'>&nbsp;<span>" + jsonObjArr[i].name + "</span></div>";
		html += 	"<div class='datagrid-data'>&nbsp;&nbsp;&nbsp;</div>";
		html += 	"<div class='datagrid-data'>&nbsp;&nbsp;&nbsp;"+ jsonObjArr[i].ownedBy  + "</div>";
		html += 	"<div class='datagrid-data'>&nbsp;&nbsp;&nbsp;onware</div>";
		html += 	"<div class='datagrid-data'>";
		
		//status가 fail일때 만 이미지 추가	
		if(jsonObjArr[i].status =="CREATE_FAILED"){
			html+="<input type='button' class='error' id='error'><span class='status'>&nbsp;" + jsonObjArr[i].status + "</span></div>";
			
		}else{
			html +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='status'>&nbsp;" + jsonObjArr[i].status + "</span></div>";
			
		}
		
		html += 	"<div class='datagrid-data'>&nbsp;&nbsp;&nbsp;Never</div>";
		html += "</div>";
		html += "<div class='error-msg' id='errorMsg' style='display: none;'></div>";
		out = html;
	}
	outdata.innerHTML = out;
	
})

	function btn_click(){
			
			//var errorMsg = document.getElementsByClassName('error-msg')[0];
			//var errorBtn = document.getElementsByClassName('error')[0];
			var errorMsg= document.getElementById('errorMsg');
			var errorBtn= document.getElementById('error');
			
			if(errorMsg.style.display=='none'){				
				errorMsg.style.display=''
			}else if(errorMsg.style.display=='')
				errorMsg.style.display='none'
			
		}
</script>
<body>
	<!--header -->
	<c:import url="header.jsp"></c:import>
		
	<div class="deploymentbox">
		<h2 class="title">Deployments</h2>	
		<div class="datagrid" style="overflow-anchor: none;">
			<div class="datagrid-row" role="row">
				<div style="width: 38px;"></div>
				<div style="width: 40px; border-left: 1px solid #dedede;"></div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Name</div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Adress</div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Owner</div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Project</div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Status</div>
				<div class="datagrid-title">&nbsp;&nbsp;&nbsp;Expires on</div>
			</div>
		</div>
		<div id="outdata">
		
		</div>
	</div>
</body>
</html>