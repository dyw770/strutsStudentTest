<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${requestScope.user.name }欢迎你<br>
${requestScope.msg }
<form action="publish.action" method="post">
	新闻标题:<input type="text" name="news.title"><br>
	新闻内容:<textarea rows="5" cols="10" name="news.context">
			</textarea><br>
			<input type="submit" value="发布">
</form>
</body>
</html>