<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign Up</title>
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
            </div>
            <div class="login-check">
                <c:if test="${not empty email}">
                    <div class="login-check-ok">
                        <a href="#">${email}</a>
                        &nbsp;
                        <a href="#">Sign-Out</a>
                    </div>
                </c:if>
            </div>
            <div id="nav">
                <ul>
                    <li><a href="/TopSpeed/index/index.htm">Home</a></li>
                    <li><a href="/TopSpeed/ad/search-pre.htm">Bid</a></li>
                    <li><a href="/TopSpeed/static/news.htm">News</a></li>
                </ul>
            </div><!-- end #nav -->
            <div id="main" class="registerClass1" >
                <div class="centeringDiv">
                    <h1 style="text-align: left;color: orangered">Please Sign-In here:</h1>
                    <form:form cssClass="registerForm" action="/TopSpeed/user/login.htm" method="post" commandName="userForm">
                        <table class="registerTable">
                            <tbody>
                                <tr>
                                    <td colspan="2"><form:input path="email" placeholder="Email" class="form-control"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><form:password path="password" placeholder="Password" class="form-control"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><input type="submit" value="Sign In" class="btn btn-primary" /></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><a href="/TopSpeed/user/register.htm">Not a member yet.Join!</a></td>
                                </tr>
                                <c:if test="${key1=='1'}">
                                    <tr>
                                        <td colspan="2" class="error">Not a valid username or password!</td>
                                    </tr>
                                </c:if>
                            <ul>
                                <li><form:errors path="email" cssClass="error"/></li>
                                <li> <form:errors path="password" cssClass="error"/></li>
                            </ul>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
            <div id="footer">
                <p>Copyright &copy; Jass</p>
            </div>
        </div>
    </body>
</html>
