<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main">
            <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,400normal,700,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
            <p>Welcome to our website! We appreciate your willingness to use services that we can offer. Kindly ask you to contact your sales representative for personal service and immediate answers on inquiries about rates, deadlines and other important questions.</p>
            <p><img src="${pageContext.request.contextPath}/resources/images/motorcycle.jpg" alt="Neki opis" title="Neki opis"></p>
            <p>The company was founded in 2000 in Belgrade.  Managers are software engineers David Davidovic and Pera Detlic. By virtue of team of experts our company became one of the leading companies in the field of motorcycling. </p>
        </div>
    </jsp:attribute>
</t:custom_layout> 
