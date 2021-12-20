<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript">

	var requestInfo = new Object();
	requestInfo.bulkRequestCount = "";
	requestInfo.deploymentName = "";
	requestInfo.inputs = new Array();
	requestInfo.backup  = "";
	requestInfo.backup_ip = "";
	requestInfo.cluster = "";
	requestInfo.datastore = "";
	requestInfo.diskSize = "";
	requestInfo.flavor = "";
	requestInfo.oa_ip = "";
	requestInfo.oa_network = "";
	requestInfo.vmName = "";
	requestInfo.projectId = "";
	requestInfo.reason = "";
	
	
	window.onload = function(){
		fetch('/catalogItem')
		.then((response) => response.json())
		.then((res) => {
			var html="<select class='removeborder'>";
			//console.log(res)
			//RequestInfo
			this.requestInfo.bulkRequestCount = res.bulkRequestLimit;
			this.requestInfo.projectId = res.projects[0].id;
			this.requestInfo.reason = null;
			
			//console.log(this.requestInfo.projectId)
			//project name
			var projects = new Array();
			projects = res.projects
			var project = new Array();
			
			// api데이터 추출을 위한 변수
			var option = null;
			var options = null;
			var optionList =  new Array();
			
			//form을 나타내기 위한 변수
			var project = document.getElementById("project");
			var deployment = document.getElementById("deployment");
			var oaip = document.getElementById("oaip");
			var use = document.getElementById("use");
			var spec = document.getElementById("spec");
			var hostname = document.getElementById("hostname");
			var cluster1 = document.getElementById("cluster1");
			var root = document.getElementById("root");
			var backup = document.getElementById("backup");
			var cluster2 = document.getElementById("datastore");
			var oanetwork = document.getElementById("oanetwork");
			
			//Project
			for(var i in projects){
			 		html+=" <option>"+projects[i].name+"</option>";	
			}
			html+="</select>";
			project.getElementsByClassName("col-md-3")[1].innerHTML=html;
			

			// properties
			var properties = res.schema.properties;							
			var propertiesKey = Object.keys(properties);
			
			//properties안에 데이터가 있는지?
			if(propertiesKey.length != 0){
				for( key1 in properties ) {	    
				    	 //console.log( 'key:' + key1 + ' / ' + 'value:' +JSON.stringify(properties[key1].default));
				    	 options = properties[key1];
				    	 for(key2 in options){
				    		 
				    		 // list데이터 추출
				    		 if(key2=='enum'){
				    		 	optionlist = options['enum'];
				    		 	
				    		 	html="<select class='removeborder'>";
				    		 	for(var i in optionlist){
				    		 		html+=" <option>"+optionlist[i]+"</option>";
				    		 	}
				    		 	
				    		 	html+="</select>";
				    		 	
				    		 	//Oa-network
				    			if(key1=='oa-network'){						
									oanetwork.style.display="";
									oanetwork.getElementsByClassName("col-md-3")[1].innerHTML=html;

								}
				    		 	//Use BackUP?
				    			if(key1=='backup'){						
				    				use.style.display="";
				    				use.getElementsByClassName("col-md-3")[1].innerHTML=html;
								}
				    		 	//Datastore
				    			if(key1=='datastore'){						
				    				datastore.style.display="";
				    				datastore.getElementsByClassName("col-md-3")[1].innerHTML=html;
								}
				    		 	//vm spec
				    			if(key1=='flavor'){						
				    				spec.style.display="";
				    				spec.getElementsByClassName("col-md-3")[1].innerHTML=html;
								}
				    		 	//Cluster
				    			if(key1=='cluster'){						
				    				cluster1.style.display="";
				    				cluster1.getElementsByClassName("col-md-3")[1].innerHTML=html;
								}			    			
				    	
				    		 // default 데이터 추출	
				    		 }else{				    			 
				    			option = options['default'];
				    			//console.log( 'option1: '+option);
				    			
				    			//OA IP Address
				    			if(key1=='oa-ip'){									
									oaip.style.display="";
									oaip.getElementsByClassName("removeborder")[0].value=option;
									
								}
				    			//Backup IP Address
				    			if(key1=='backup-ip'){
									backup.style.display="";
									backup.getElementsByClassName("removeborder")[0].value=option;
								}				    			
				    			//vm host name
				    			if(key1=='vmName'){									
									hostname.style.display="";
									
								}
				    			//root volume size
				    			if(key1=='diskSize'){
				    				root.style.display="";
				    				root.getElementsByClassName("removeborder")[0].value=option;
								}
				    		 }
				    	 }
				}
			}
		});
		
		//catalogItemVersion
		fetch('/catalogVersion')
			.then((response) => response.json())
			.then((res) => {
				
				var versionTag = document.getElementById("version");
				var content = new Array();
				content = res.content;
				var version = content[0].id;
				console.log(version);
				
    		 	var html="<select class='removeborder'>";
    		 	html+=" <option>"+version+"</option>";
    		 	html+="</select>";
    		 	
    		 	console.log(html);
    		 	
    		 	versionTag.innerHTML=html;
		
		});
		
	}
	function submitRequest(){
		
		var div = document.getElementsByClassName('row boxing');
		var bulkRequestCount = this.requestInfo.bulkRequestCount;
		var deploymentName = this.requestInfo.deploymentName;
		var projectId = this.requestInfo.projectId;
		var reason = null;
		
		for (var i = 0; i < div.length; i++)
		{
			//'' 표시된 input 태그만 
			if(div[i].style.display == ''){
				//console.log(div[i].id);
				if(div[i].id == 'deployment')
				{
					deploymentName = document.getElementById("deploymentName").value;
				}
				
				if(div[i].id == 'oaip'){
					var oa_ip = document.getElementById("oa_ip").value;
				}
				
				if(div[i].id == 'use'){
					var backup = document.getElementById("use").children[1].children[0].value;
				}
				
				if(div[i].id == 'spec'){
					var flavor = document.getElementById("spec").children[1].children[0].value;	
				}
								
				if(div[i].id == 'hostname'){
					var vmName = document.getElementById("vmName").value;
				}
				
				if(div[i].id == 'cluster1'){
					var cluster = document.getElementById("cluster1").children[1].children[0].value;
				}
				
				if(div[i].id == 'root'){
					var diskSize = document.getElementById("diskSize").value;
				}
				
				if(div[i].id == 'backup'){
					var backup_ip = document.getElementById("backupip").value;
				}
				
				if(div[i].id == 'datastore'){
					var datastore = document.getElementById("datastore").children[1].children[0].value;
				}
				
				if(div[i].id == 'oanetwork'){
					var oa_network = document.getElementById("oanetwork").children[1].children[0].value;
				}
				
			}
		}
		console.log(
				"bulkRequestCount : " + bulkRequestCount +
				"\n deploymentName : " + deploymentName + 
				"\n backup : " + backup +
				"\n backup_ip : " + backup_ip +
				"\n cluster : " + cluster +
				"\n datastore : " + datastore + 
				"\n diskSize : " + diskSize + 
				"\n flavor : " + flavor + 
				"\n oa_ip : " + oa_ip + 
				"\n oa_network : " + oa_network +
				"\n vmName : " + vmName + 
				"\n projectId : " + projectId + 
				"\n reason : " + reason
			);
		
		deploymentName = document.getElementById("deploymentName").value;
		
		var projectname = document.getElementById("project").children[1].children[0].value;
		var inputs = this.requestInfo.inputs;
		
		var inputs = {
				"backup" : backup,
				"backup-ip" : backup_ip,
				"cluster" : cluster,
				"datastore" : datastore,
				"diskSize" : diskSize,
				"flavor" : flavor,
				"oa-ip" : oa_ip,
				"oa-network" : oa_network,
				"vmName" : vmName,
				"projectname" : projectname
		}
		
		var data = {
				"bulkRequestCount" : bulkRequestCount,
				"deploymentName" : deploymentName,
				"projectId" : projectId,
				"reason" : reason,
				"inputs" : inputs,	
		}
		
		var datalength = JSON.stringify(data).length;
		
	
		var url = "/request?projectname="+projectname;
		//console.log("test : " + datalength)
		fetch('/request' , {
			method: 'POST',
			cache: 'no-cache',
			dataType:'json',
			body : JSON.stringify(data),
			headers: {
				'Content-Type' : 'application/json',
			},
		})
		.then((res) => res.json()).then((data) => {
			//console.log(data);			
		});
	}
	
</script>
<style>
body, html {
	color: #575757;
	font-family: Metropolis, "Avenir Next", "Helvetica Neue", Arial,
		sans-serif;
	letter-spacing: 0.5px;
}
.resource-usage {
	height: 121px;
	padding: 6px 24px;
	border-bottom: 1px solid #999;
}

.title {
	margin-bottom: 6px;
	font-weight: 100;
	color: #000;
	font-size: 27px;
}

.boxing {
	margin-bottom: 15px;
}

/* My Service */
.catalogItems {
	margin-top: 50px;
	margin-left: 135px;
	margin-bottom: 25px;
}

.card-container {
	display: grid;
	grid-template-columns: 300px 400px;
	grid-template-rows: 80px 80px;
	row-gap: 10px;
	column-gap: 20px;
}

.removeborder{
	border: 0 solid black;
	border-bottom: 1px solid #575757;
}

.removeborder:focus{
	outline: none;
}

.arrow::-webkit-outer-spin-button,
.arrow::-webkit-inner-spin-button {
	opacity: 1;
}

.requestBox{
	margin-top: 20px;
}


</style>
<body>
	<c:import url="header.jsp"></c:import>

	<div class="container requestBox">
		<form>
			<h2 class="title">New Request</h2>
			<br>
			<div class="row boxing">
				<div class="col-md-1">
					<img style="width: 36px; height: 36px;"
						src="/img/cataloglogo/service_brokericon.png"> <label>
						linux </label>
				</div>
				<div class="col-md-1">
					<label style="vertical-align: middle;">Version</label>
				</div>
				<div class="col-md-3" id="version">
	
				</div>
			</div>
			<div  id="project" class="row boxing">
           <div class="col-md-3">
              <label>Project</label>
           </div>
           <div class="col-md-3">
           </div>
        </div>
​
        <div id="deployment" class="row boxing">
           <div class="col-md-3">
              <label>Deployment Name</label>
           </div>
           <div class="col-md-3">
              <input class="removeborder" id="deploymentName"/>
           </div>
        </div>
​
        <div id="oaip" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>OA IP Address</label>
           </div>
           <div class="col-md-3">
              <input class="removeborder" id="oa_ip">
           </div>
        </div>
​
        <div id="use" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>Use Backup?</label>
           </div>
           <div class="col-md-3">

           </div>
        </div>
​
        <div id="spec" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>vm spec</label>
           </div>
           <div class="col-md-3">

           </div>
        </div>
​
        <div id="hostname" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>vm host name</label>
           </div>
           <div class="col-md-3">
              <input class="removeborder" id="vmName">
           </div>
        </div>
​
        <div id="cluster1" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>Cluster</label>
           </div>
           <div class="col-md-3">

           </div>
        </div>
​
        <div id="root" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>root volume size</label>
           </div>
           <div class="col-md-3">
              <input type="number" class="removeborder arrow" id="diskSize">
           </div>
        </div>
​
        <div id="backup" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>Backup IP Address</label>
           </div>
           <div class="col-md-3">
              <input class="removeborder" id="backupip">
           </div>
        </div>
​
        <div id="datastore" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>Datastore</label>
           </div>
           <div class="col-md-3">

           </div>
        </div>
​
        <div id="oanetwork" class="row boxing" style="display:none;">
           <div class="col-md-3">
              <label>Oa-network</label>
           </div>
           <div class="col-md-3 oa-network">

           </div>
        </div>
        <div>
        	<button onclick="script:submitRequest()" type="button" class="btn btn-primary"> Submit </button>
        	<button type="button" class="btn btn-outline-primary"> Cancel </button>
        </div>
       </form>
     </div>
</body>
</html>