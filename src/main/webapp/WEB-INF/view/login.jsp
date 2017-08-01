<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div style="text-align: center; margin-top: 100px;">
	<form action="/myshiro/login" method="post">
	    <input type="text" name="username" placeholder="用户名"><br/><br/>
		<input type="password" name="password" placeholder="密码"><br/><br/>
	    <input type="submit" value="登录">
	    <input type="reset" value="重置">
	</form>
</div>

</body>
</html>