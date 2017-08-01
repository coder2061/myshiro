<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Java验证框架测试</title>
  </head>

  <body>
    <form:form method="post" modelAttribute="testModel" action="http://localhost:8080/ssm/user/login">
        <!-- path的值可以为 * ，表示显示所有错误 -->
        <h1><form:errors path="userName" /></h1>
        <h1><form:errors path="password" /></h1>
        userName:<form:input path="userName" /><br/>
        password:<form:input path="password" /><br/>
        <input type="submit" value="提交"/>
    </form:form>
  </body>
</html>