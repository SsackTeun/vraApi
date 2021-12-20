<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
body, html {
	color: #575757;
	font-family: Metropolis, "Avenir Next", "Helvetica Neue", Arial,
		sans-serif;
	letter-spacing:0.5px;
}

.top {
	width: 100%;
	height: 60px;
	background-color: #314351;
	color: white;
	display: flex;
	align-items: center;
}

.titleArea {
	height: 38px padding: 11px 0px 0px 0px;
	margin-left: 24px;
	display: table;
}

.titleArea>span {
	display: table-cell;
	vertical-align: middle;
	padding: 0px 0px 0px 11px;
}

/* ��� ������ �Ʒ��� */
.bottom {
	width: 100%;
	height: 35px;
	background-color: white;
	border-top-color: gray;
	box-shadow: 0 -1px 0 #ccc inset;
}

.nav {
	white-space: nowrap;
	display: inline-block;
	color: #737373;
}
/* .nav-link ��� ������ �޴� �κ� �⺻ �Ӽ� */
.nav-link {
	float: left;
	font-size: 13px;
	color: #737373;
}
/* .nav-link �� ���콺 ������ �߻� �ϴ� �Ӽ�*/
.nav-link:hover {
	box-shadow: 0 -3px 0 #0077b8 inset;
	transition: box-shadow .2s ease-in;
	font-weight: 400;
	color: black;
}
/* link text ���� */
/* �� �� �� �� */
a {
	text-decoration: none;
}

/* �� ũ �� �� */
a:link {
	color: #575757;
	text-decoration: none;
}

/* �湮 ��ũ ���� */
a:visited {
	color: #575757;
	text-decoration: none;
}
}
</style>
<body>
	<div class="header">
		<div class="top">
			<div class="titleArea">
				<img class="top-logo" src="/img/cataloglogo/vmwlogo.png"> <span>vRealize
					Automation - Service Broker </span>
			</div>
		</div>
	</div>
	<div class="bottom">
		<nav>
			<ul class="nav">
				<li class="nav-link"><a href="/catalog">Catalog</a></li>
				<li class="nav-link"><a href="/deployment">Deployments</a></li>
				<li class="nav-link"><a href="#/3">Content & Policies</a></li>
				<li class="nav-link"><a href="#/4">Infrastructure</a></li>
				<li class="nav-link"><a href="#/5">Approvals</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>