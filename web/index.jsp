<%--
  Created by IntelliJ IDEA.
  User: Robot
  Date: 2018/1/12
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome</title>
    <style>
      .myinput{
        width: auto;
      }
      .cnbody , form, .top{
        text-align: center;
      }
      .cnborder{
          border: 1px;
      }
      input{
        margin-left: 8px;
      }
    </style>
  </head>
  <body>
  <div>
      <div id="cnborder">
        <div class="top"><h3>WELCOME</h3></div>
        <div class="cnbody">
            <form action="loginServlet.view" method="post">
              <div class="myinput" >YRID<input name="myname" type="text"><br><br></div>
              <div class="myinput" >PASS<input name="mypass" type="password"><br></div>
                <p style="color: red;">${sessionScope.loginmsg}</p><br>
              <div class="myinput"><button type="submit">login</button></div>
            </form>
        </div>
        <div class="foot"></div>
      </div>
  </div>
  </body>
</html>
