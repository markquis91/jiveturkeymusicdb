<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel=stylesheet type=text/css href=servlet.css>
<title>Jive Turkey Music Player|Albums</title>
</head>
<body>
	<h2 class=pagetitle>Jive Turkey Music Player</h2>
	<h3 class="pagedescription">Cloud-based Music Management System</h3>
	
	<ul class=displaymenu>
		<li><a class=atable href=albumservlet?action=albums style=display:block;>Albums</a></li>
		<li><a href=albumservlet?action=artists style=display:block;>Artists</a></li>
		<li><a href=albumservlet?action=songs style=display:block;>Songs</a></li> 	
	</ul>	
	
	<table align=center>
		<tr><th>Artist Id</th><th>Name</th></tr>
		<c:forEach items="${artistset}" var="artistItem">
		<tr><td>${artistItem.artistId}</td><td>${artistItem.artistName}</td></tr>
		</c:forEach>
	</table>
</body>
</html>