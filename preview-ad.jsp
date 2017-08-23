<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <c:forEach var="i" items="${ads}">
                <div style="width:30%;height:auto;float:left;margin-right:10%;text-align: center">
                    <img width="330" height="280" src="<c:out value="${i['photo']}"/>"/>
                    <span style="color:black;background-color: lightgray;font-weight: bold">Price in $:
                        <c:out value="${i['price']}"/>
                    </span>
                </div>
                <table style="width:50%;float:right;" class="table table-bordered">
                    <tbody>
                    <th colspan="2" style="background-color: lightgray;text-align: center;border-radius :2px;border-style: groove;border-color: white;color:black">Details</th>
                    <tr>
                        <td>Ad ID </td>
                        <td><c:out value="${i['ad_id']}"/></td>
                    </tr>
                    <tr>
                        <td>Bike ID</td>
                        <td><c:out value="${i['reg_id']}"/></td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td><c:out value="${i['email']}"/></td>
                    </tr>
                    <tr>
                        <td>Model</td>
                        <td><c:out value="${i['model']}"/></td>
                    </tr>
                    <tr>
                        <td>Color</td>
                        <td><c:out value="${i['color']}"/></td>
                    </tr>
                    <tr>
                        <td>Year of production</td>
                        <td><c:out value="${i['year_of_pro']}"/></td>
                    </tr>
                    <tr>
                        <td>Mileage</td>
                        <td><c:out value="${i['mileage']}"/></td>
                    </tr>
                    <tr>
                        <td>Validity of registration</td>
                        <td><c:out value="${i['validity_of_reg']}"/></td>
                    </tr>
                    <tr>
                        <td>General condition</td>
                        <td><c:out value="${i['gen_condition']}"/></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><c:out value="${i['country']}"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><c:out value="${i['city']}"/></td>
                    </tr>
                    <tr>
                        <td>Ad Validity</td>
                        <td><c:out value="${i['ad_validity_date']}"/></td>
                    </tr>
                    <tr>
                        <td>Published on</td>
                        <td><c:out value="${i['ad_post_date']}"/></td>
                    </tr>
                    <tr>
                        <td>Note</td>
                        <td><c:out value="${i['temp_note']}"/></td>
                    </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>
    </jsp:attribute>
</t:custom_layout> 
