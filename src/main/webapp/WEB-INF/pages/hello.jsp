<%--
  Created by IntelliJ IDEA.
  User: zhaoxin
  Date: 14-12-19
  Time: 下午2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

	<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<title>Login</title>
</head>
<body>
<div class="page-header">
	<h1>输入你的名字进行登录 <small>我们将为你量身推荐课程</small></h1>
</div>
<div class="row">
	<p>&nbsp </p>
	<p>&nbsp </p>
	<p>&nbsp </p>
	<p>&nbsp </p>

</div>
<div class="row">
	<div class="col-lg-6 col-lg-offset-3">
		<form role="form" action="/Login">
			<div class="form-group">
				<label for="exampleInputEmail1">User Name</label>
				<input class="form-control" id="exampleInputEmail1" name="Username" placeholder="User Name">
			</div>
			<p>&nbsp </p>
			<p>&nbsp </p>

			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div><!-- /.col-lg-6 -->
</div><!-- /.row -->
</div>

</body>
</html>

