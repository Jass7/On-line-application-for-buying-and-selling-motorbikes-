<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <c:forEach var="i" items="${ads}">
                <div class="eachAd">
                    <img width="150" height="100" src="<c:out value="${i['photo']}"/>"/>
                    <div class="get-ad-right-info">
                        <table>
                            <tr style="text-align: left;">
                                <td colspan="4" style="color:white; width:30%"> <b> <c:out value="${i['model']}"/> </b></td>
                            </tr> 
                            <tr> 
                                <td colspan="3">
                                    Price in USD: 
                                </td>
                                <td>
                                    <b> <c:out value="${i['price']}"/></b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" >
                                    Year of Production:
                                </td>
                                <td>
                                    <c:out value="${i['year_of_pro']}"/></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    Mileage:
                                </td>
                                <td><c:out value="${i['mileage']}"/></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    Color:
                                </td>
                                <td><c:out value="${i['color']}"/></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <a href="/TopSpeed/ad/preview-ad.htm?id=<c:out value="${i['ad_id']}"/>">Preview</a>
                                    <a href="/TopSpeed/ad/update-ad.htm?id=<c:out value="${i['ad_id']}"/>">Update</a>
                                    <a href="/TopSpeed/ad/delete-ad.htm?id=<c:out value="${i['ad_id']}"/>" class="del_btn">Delete</a>
                                </td>
                                <td><c:out value="${i['']}"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>
</t:custom_layout> 
