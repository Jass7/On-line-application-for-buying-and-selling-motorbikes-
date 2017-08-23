<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign Up</title>
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/stylesheet1.css" />
    </head>
    <body style="background-image: url(${pageContext.request.contextPath}/resources/images/wallpaper.jpg);">
        <div id="wrapper">
            <div id="header">
                <div id="logo">
                    <p><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="TopSpeed" title="TopSpeed"></p>
                    </a>
                </div>
                <div class="login-check">
                    <div class="login-check-ok">
                        <a href="/TopSpeed/user/login.htm">Sign-In</a>
                    </div>
                </div>
                <div id="nav">
                    <ul>
                        <li><a href="/TopSpeed/index/index.htm">Home</a></li>
                        <li><a href="/TopSpeed/ad/search-pre.htm">Bid</a></li>
                        <li><a href="/TopSpeed/static/news.htm">News</a></li>

                    </ul>
                </div><!-- end #nav -->
                <div id="main" class="registerClass1">
                    <div class="centeringDiv">
                        <form:form cssClass="registerForm" action="/TopSpeed/user/register.htm" method="post" commandName="userForm">
                            <table class="registerTable">
                                <tbody>
                                    <tr>
                                        <td colspan="2"><form:input path="email" placeholder="Email" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><form:password path="password" placeholder="Password" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><form:input path="fullName" placeholder="FullName" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><form:input path="tellNo" placeholder="Phone No" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><input type="submit" value="Register" class="btn btn-primary" /></td>
                                    </tr>
                                    <form:errors path="*" cssClass="error" />
                                </form:form>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="footer">
                    <p>Copyright &copy; Jass</p>
                </div>
            </div>
    </body>
</html>
