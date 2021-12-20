<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>Service Broker</title>
</head>
<script type="text/javascript">

window.onload = function(){
	fetch('/catalogInfo', {
	      headers : { 
	          'Content-Type': 'application/json',
	          'Accept': 'application/json'
	         }
	        })
	.then((response) => response.json())
	.then((res) => {
		console.log(res);
		
		//content
		var content = new Array();
		content = res.content;
		
		//empty
		var empty = res.empty;
		
		//first
		var first = res.first;
		
		//last
		var last = res.last;
		
		//number
		var number = res.number;
		
		//numberOfElements
		var numberOfElements = res.numberOfElements;
		
		//pageable
		var pageable = new Array();
		pageable = res.pageable;
		
		//size
		var size = res.size;
		
		//sort
		var sort = new Array();
		sort = res.sort;
		
		//totalElements
		var totalElements = res.totalElements;
		
		//totalPages
		var totalPages = res.totalPages;
		
		//html
		
		var html = "";
		var out = "";
		for (var i = 0; i < res.totalElements; i ++)
		{
			html = "<div class='catalog-items'>";
			html+= 			"<div class='catalog-data'>";
			html+=			    "<img src='/img/cataloglogo/templatelogo.png'>";
			html+= 				"<div class='catalogtitle'>";
			html+= 					"<span class='catalogname' id='catalogname'>" + content[i].name + "</span><br>";
			html+= 					"<span class='singleline'>" + content[i].type.name + "</span>";
			html+= 			"</div>";
			html+= 			"<div class='catalogbottom'>";
			html+= 				"<div class='projectname' >Projects : </div>";
			html+=				"<div class='projectname'>onware</div>";
			html+= 			"</div>";
			html+= 		"</div>";
			html+=		"<div class='request'>";
			html+=			"<a href='/requestpage?catalogId="+ content[i].id + "'>"
			html+= 				"<button class='requestbtn' id='requestbtn'> REQUEST </button>";
			html+= 			"</a>"
			html+= 		"</div>";
			html+= "</div>";
			out += html;
		}
		var catalogItem = document.getElementById('container');
		catalogItem.innerHTML = out;
		
	});
}
	
</script>
<style>
	body, html{
		color: #575757;
    	font-family: Metropolis,"Avenir Next","Helvetica Neue",Arial,sans-serif;
    	letter-spacing:0.5px;
	}
	.resource-usage{
		height: 121px;
		padding : 6px 24px;
		border-bottom: 1px solid #999;		
	}
	.title{
	    margin-bottom: 6px;
	    font-weight: 100;
	    color: #000;
	 	font-size: 27px;
	}
	.resource-usage-item{
		height: 65px;
		text-align: center;
		width: 100px;
		float: left;
		margin-right: 23px;
		margin-top: 7px;
	}
	.resource-usage-text{
		font-size: x-large;
    	margin-bottom: .25rem;
    	
	}
	.catalogbox{
		padding : 24px 0px 0px 24px;
		height : 700px;
		background: rgb(250,250,250);
		
	}
	.catalog-data{
		margin-top:50px;
		padding: 12px 18px;
		border: 1px solid #ccc;
		border-bottom: 1px solid #ededed;
		width: 274px;
		height: 217px;
		background: white;
		position:relative;
	
	}
	.request{
		width: 274px;
		height: 50px;
		padding: 12px;
		box-shadow: 0 0.125rem 0 0 #ccc;
   		border: 1px solid #ccc;
   		cursor: pointer;
   		background: white;
	
	}
	.catalogname{
		font-size: 18px;
		margin-left: 5px;
		
	}
	.singleline{
		font-size: 14px;
	}
	.catalogtitle{
		float: right;
	}
	
	.requestbtn{
	    letter-spacing: .073em;
	    font-size: 12px;
	    font-weight: 500;
		border-color: transparent;
	    background-color: transparent;
	    color: #0077b8;
	}
	.catalogbottom{
		position:absolute; 
		bottom:0;
	}
	.projectname{
		float: left;
		width: 113px;
		height: 24px;
		font-size: 14px;
	}
	
	/* My Service */
	.catalog-items{
		margin-top: 50px;
		margin-left: 135px;
		margin-bottom: 25px;
	}
	
	.catalog{
		display: grid;	
		grid-template-columns: 300px 400px;
		grid-template-rows: 300px 300px;
		row-gap : 10px;
		column-gap: 20px;
	}
	
	
</style>
<body>
	<c:import url="header.jsp"></c:import>
	
	<div class="resource-usage">
		<h2 class="title">My Resource Usage</h2>
		<div class="resource-usage-items" >
			<div class="resource-usage-item">
				<span class="resource-usage-text">2</span><br>
				<span>VMs Count</span>
			</div>
			<div class="resource-usage-item">
				<span class="resource-usage-text">4</span><br>
				<span>CPU Count</span>
			</div>
			<div class="resource-usage-item">
				<span class="resource-usage-text">100</span><br>
				<span>Storage(GB)</span>
			</div>
			<div class="resource-usage-item">
				<span class="resource-usage-text">8</span><br>
				<span>Memory(GB)</span>
			</div>
		</div>
	</div >
	
	<div class="catalogbox">
		<h2 class="title">Catalog Items</h2>
		<div class="catalog" id="container">
			
		</div>	
	</div>
</body>
</html>