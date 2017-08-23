<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <table border="0" cellspacing="3" cellpadding="3" class="searchTable table">
                <thead class="thead-inverse">
                    <tr>
                        <th>Ad ID</th>
                        <th>Model</th>
                        <th>Year of Production</th>
                        <th>Price</th>
                        <th>Publishing date</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${myBikes}">
                        <tr>
                            <td> <c:out value="${i['ad_id']}"/></td>
                            <td> <c:out value="${i['model']}"/> </td>
                            <td><c:out value="${i['year_of_pro']}"/></td>
                            <td><c:out value="${i['price']}"/></td>
                            <td><c:out value="${i['ad_post_date']}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role=='admin'}">
                                        <a href="/TopSpeed/ad/preview-ad.htm?id=<c:out value="${i['ad_id']}"/>">Preview</a>
                                        <a href="/TopSpeed/ad/update-ad.htm?id=<c:out value="${i['ad_id']}"/>">Update</a>
                                        <a href="/TopSpeed/ad/delete-ad.htm?id=<c:out value="${i['ad_id']}"/>" class="del_btn">Delete</a>
                                    </c:when> 
                                    <c:otherwise>
                                        <a href="/TopSpeed/ad/preview-ad.htm?id=<c:out value="${i['ad_id']}"/>">Preview</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:attribute>
</t:custom_layout> 