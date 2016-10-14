<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<html>
<head>
	<title>index</title>
</head>
<body>
<h1>
index
</h1>

<P>  The time on the server is ${serverTime}. </P>
<jsp:forward page="/sample/openSampleList.do"/>
</body>
</html>
