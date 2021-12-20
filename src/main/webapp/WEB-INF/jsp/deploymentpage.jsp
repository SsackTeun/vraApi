<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
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

.removeborder {
	border: 0 solid black;
	border-bottom: 1px solid #575757;
}

.removeborder:focus {
	outline: none;
}

.arrow::-webkit-outer-spin-button, .arrow::-webkit-inner-spin-button {
	opacity: 1;
}

.requestBox {
	margin-top: 20px;
}

/*card*/
.card {
	width: 800px;
	height: 110px;
}

.wrapper-l {
	width: 25%;
	height: 100%;
	border: solid red;
	display: inline-block;
	float: left;
}

.logo-cover {
	width: 48px;
	height: 95%;
	border: solid blue;
	margin-left: 5px;
	margin-right: 3px;
	display: inline-block;
	float: left;
}

.logo-cover>img {
	width: 40px;
	height: 40px;
}

.info-1 {
	width: 70%;
	height: 95%;
	border: solid blue;
	display: inline-block;
}

.wrapper-m {
	width: 50%;
	height: 100%;
	border: solid blue;
	display: inline-block;
}
</style>
<script type="text/javascript">
	/* 객체 생성 */
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
						console.log(k + " : " + contentArr[i][j][k])
						jsonObj[k] = contentArr[i][j][k];
					}
				}
				else{
					console.log(j + " : " + contentArr[i][j])
					jsonObj[j] = contentArr[i][j];
				}
			}
			console.log("-----------------------------------------")
			jsonObjArr[i] = jsonObj;
		}
		console.log(jsonObjArr)
	})
	
</script>
<body style="background-color: #fafafa">
	<jsp:include page="header.jsp"></jsp:include>

</body>
</html>