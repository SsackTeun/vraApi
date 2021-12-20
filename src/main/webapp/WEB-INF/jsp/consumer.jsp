<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<style>
	body, html{
		color: #575757;
    	font-family: Metropolis,"Avenir Next","Helvetica Neue",Arial,sans-serif;
	}
	
	/* card 영역  */
	.card-block {
		display: inline-block;
		width: 420px;
		height: 60px;
		
		/* .card-img , .service-name 수직 정렬 */
		display: flex;
		align-items: center;
		
		/* effect */
		box-shadow: 0 0.125rem 0 0 #ccc;
		border : 1px  solid #ccc;
		cursor : pointer;
		text-decoration: none;
	}
	
	.card-block:hover {
		/* effect */
		box-shadow: 0 0.125rem 0 0 #0094d2;
		border : 1px  solid #0094d2;
		cursor : pointer;
		text-decoration: none;
		transform: translateY(-2px);
		transition: border .2s ease,transform .2s ease;		
	}
	
	.card-img {
		width: 34px;
		height: 34px;
		float:left;
		margin-right: 7px;
		margin-left: 10px;
		display: inline-block;
	}
	
	.service-name {
		display: inline-block;
		
	}
	
	/* card box - container*/
	
	/* My Service */
	.title{
		margin-top: 50px;
		margin-left: 135px;
		margin-bottom: 25px;
	}
	
	.container{
		display: grid;	
		grid-template-columns: 450px 450px;
		grid-template-rows: 80px 80px;
		row-gap : 10px;
		column-gap: 20px;
	}
}
</style>
<script type="text/javascript">
	window.onload = function(){
		fetch('/orgid')
			.then((res)=> res.json())
			.then(data => console.log(data))
			
		fetch('/servicelist')
			.then((res)=> res.json())
			.then(data => {
				
				var arr = new Array();
				arr = data.userOrgInfo;
				
				for (i in arr){
					var service = arr[i].servicesDef;
					console.log(service);
					
					for(j in service)
					{
						var serviceRole = service[j].serviceRoles;
						var serviceDisplayName = service[j].serviceDisplayName;
						
						console.log("======\n" + serviceRole);
						console.log(serviceDisplayName);
						
						if(serviceDisplayName.trim() == document.getElementById('Service_Broker').innerHTML.trim() && serviceRole.length != 0)
						{
							document.getElementById('Service_Broker_Card').style.display=""
						}
						if(serviceDisplayName.trim() == document.getElementById('Cloud Assembly').innerHTML.trim() && serviceRole.length != 0)
						{
							document.getElementById('Cloud Assembly_Card').style.display=""
						}
						if(serviceDisplayName.trim() == document.getElementById('Code Stream').innerHTML.trim() && serviceRole.length != 0)
						{
							document.getElementById('Code Stream_Card').style.display=""
						}
						if(serviceDisplayName.trim() == document.getElementById('Orchestrator').innerHTML.trim() && serviceRole.length != 0)
						{
							document.getElementById('Orchestrator_Card').style.display=""
						}
						if(serviceDisplayName.trim() == document.getElementById('vRA Migration Assistant').innerHTML.trim() && serviceRole.length != 0)
						{
							document.getElementById('vRA Migration Assistant_Card').style.display=""
						}
					}
				}
			})
	}
</script>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="menu">
		<h2 class="title">My Service</h2>
		<div class="container">
			<div class="item" id = "Service_Broker_Card" style="display: none;">
				<a href="/catalog">
					<div class="card-block">
							<img src="/img/servicelogo/code-broker-full-color.png" class="card-img">
							<span class="service-name" id = "Service_Broker"> Service Broker </span>
					</div>
				</a>
			</div>
			
			<div class="item" id="Cloud Assembly_Card" style="display: none;">
				<div class="card-block">
					<img src="/img/servicelogo/cloud-assembly.png" class="card-img">
					<span class="service-name" id="Cloud Assembly"> Cloud Assembly </span>
				</div>
			</div>
			<div class="item" style="display: none;">
				<div class="card-block" id="Code Stream_Card">
					<img src="/img/servicelogo/code-stream.png" class="card-img">
					<span class="service-name" id="Code Stream"> Code Stream </span>
				</div>
			</div>
			<div class="item" style="display: none;">
				<div class="card-block" id="Orchestrator_Card">
					<img src="/img/servicelogo/vro-logo.png" class="card-img">
					<span class="service-name" id="Orchestrator"> Orchestrator </span>
				</div>
			</div>
			<div class="item" id="vRA Migration Assistant_Card" style="display: none;">
				<div class="card-block">
					<img src="/img/servicelogo/compass-line.png" class="card-img">
					<span class="service-name" id="vRA Migration Assistant"> vRA Migration Assistant </span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>