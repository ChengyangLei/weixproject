<%--
  Created by IntelliJ IDEA.
  User: leichengyang
  Date: 2019/4/28 0028
  Time: 上午 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<form action="<%=basePath%>saveUser">
    <p>用户名：<input type="text" id="username" name="username"></p>
    <p>密码：<input type="text" id="password" name="password"></p>
    <p><input type="submit" value="保存"></p>

</form>
</body>
</html>
