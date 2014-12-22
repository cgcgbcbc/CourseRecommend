<%--
  Created by IntelliJ IDEA.
  User: zhaoxin
  Date: 14-12-19
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <!-- 新 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

  <!-- 可选的Bootstrap主题文件（一般不用引入） -->
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <script src="/js/action.js"></script>

  <title>MainPage</title>

</head>
<body onload="load()">
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">CourseRecommendation</a>

    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


      <ul class="nav navbar-nav navbar-right">
        <p class="navbar-text">Signed in as <span id="username">${Username}</span></p>
      </ul>

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="row">
  <div class="col-lg-2">
    <ul class="nav nav-pills nav-stacked" role="tablist">

      <li id="mycourse" role="presentation" class="active"><a style="text-align: center;" href="#" onclick="cancelEdit()">我的课程</a></li>
      <li id="editcourse" role="presentation"><a href="#" style="text-align: center;" onclick="editCourse()">编辑课程</a></li>
      <li id="addcourse" role="presentation"><a href="#" style="text-align: center;" onclick="addCourse()">添加课程</a></li>
      <li id="reccourse" role="presentation"><a href="#" style="text-align: center;" onclick="recCourse()">推荐课程</a></li>
    </ul>
<p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <ul class="list-group">
      <li class="list-group-item" style="text-align: center"><span >经过课程在图中寻找同学</span></li>
      <li class="list-group-item" style="text-align: center"><span id="step1"></span></li>
      <li class="list-group-item" style="text-align: center"><span id="step2"></span></li>
      <li class="list-group-item" style="text-align: center"><span id="step3"></span></li>
      <li class="list-group-item" style="text-align: center"><span id="step4"></span></li>
      <li class="list-group-item" style="text-align: center"><span id="step5"></span></li>
      <li class="list-group-item" style="text-align: center"><span id="step6"></span></li>
    </ul>
  </div><!-- /.col-lg-1 -->

  <div class="row">
    <div class="col-lg-6 col-lg-offset-1">

      <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">我的课程
        </div>
        <!-- Table -->
        <table class="table table-hover" id ="courestable">
          <!-- On cells (`td` or `th`) -->
          <tr>
            <td class="active" style="text-align: center;">课程名</td>
            <td class="success" style="text-align: center;width: 60px;">成绩</td>
          </tr>

        </table>

      </div>
      <nav id="pages" style="display: none">
        <ul class="pager">
          <li><a href="#" onclick='pre()'>Previous</a></li>
          <li><a href="#" onclick='next()'>Next</a></li>
        </ul>
      </nav>
    </div><!-- /.col-lg-1 -->
  </div>
  </div>
</div><!-- /.row -->

</body>
</html>
