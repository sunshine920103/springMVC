<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎</title>
</head>
<body>

<a href="/lang?langType=zh">中文</a>
<a href="/lang?langType=en">英文</a>
<h2>Hello World!${user.userName},${error}</h2>

<form action="login" method="post">
    用户名：<input id="username" name="username" type="text" /><br>
    密&nbsp;&nbsp;码：<input id="password" name="password" type="password" /><br>
    <input type="submit">
</form>
<span>当前IP：<%=request %></span>
</body>
</html>
