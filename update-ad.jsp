<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <div class="centeringDiv">
                <c:forEach var="i" items="${ads}">
                    <form:form cssClass="registerForm" method="post" action="/TopSpeed/ad/update-ad.htm" enctype="multipart/form-data" commandName="adForm">
                        <table >
                            <tbody>
                                <tr style="text-align: left;">
                                    <td colspan="2" style="font-weight: normal">Bike ID</td>
                                    <td colspan="2" style="font-weight: normal">Model</td>
                                    <td colspan="2" style="font-weight: normal">Color</td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2"><form:input path="id.regId" placeholder="Bike ID" class="form-control" value="${i['reg_id']}" /></td>
                                    <td colspan="2"><form:input path="bike.model" placeholder="Model" class="form-control" value="${i['model']}"/></td>
                                    <td colspan="2"><form:input path="bike.color" placeholder="Color" class="form-control" value="${i['color']}"/></td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2" style="font-weight: normal">Year of Production</td>
                                    <td colspan="2" style="font-weight: normal">Mileage</td>
                                    <td colspan="2" style="font-weight: normal">Validity</td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2"><form:input path="bike.yearOfPro" placeholder="Year of Production" class="form-control" value="${i['year_of_pro']}"/></td>
                                    <td colspan="2"><form:input path="bike.mileage" placeholder="Mileage" class="form-control" value="${i['mileage']}"/></td>
                                    <td colspan="2"><form:input path="bike.validityOfReg" placeholder="Validity of registration" class="form-control" value="${i['validity_of_reg']}"/></td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2" style="font-weight: normal">General condition</td>
                                    <td colspan="2" style="font-weight: normal">Country</td>
                                    <td colspan="2" style="font-weight: normal">City</td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2"><form:input path="bike.genCondition" placeholder="General Condition" class="form-control" value="${i['gen_condition']}"/></td>
                                    <td colspan="2"><form:input path="bike.country" placeholder="Country" class="form-control" value="${i['country']}"/></td>
                                    <td colspan="2"><form:input path="bike.city" placeholder="City" class="form-control" value="${i['city']}"/></td>
                                </tr>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2" style="font-weight: normal">Price</td>
                                    <td colspan="2" style="font-weight: normal">Valid until:</td>
                                    <td colspan="2" style="font-weight: normal">Posted on:</td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2"><form:input path="bike.price" placeholder="Price" class="form-control" value="${i['price']}"/></td>
                                    <td colspan="2"><form:input path="adValidityDate" placeholder="Ad Validity" class="form-control" value="${i['ad_validity_date']}"/></td>
                                    <td colspan="2"><form:input path="adPostDate" placeholder="Ad Post Date" class="form-control" value="${i['ad_post_date']}"/></td>
                                </tr>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="2" style="font-weight: normal">Note:</td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="6"><form:textarea path="bike.tempNote" placeholder="Note" class="form-control" value="${i['temp_note']}"/></td>
                                </tr>
                                <tr style="text-align: left;">
                                    <td colspan="3">Select Photo: </td>
                                    <td colspan="3"><input type="file" name="photo" class="btn btn-primary"></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <form:hidden path="id.adId" value="${i['ad_id']}" />
                                        <form:hidden path="id.email" value="${i['email']}" />
                                        <form:hidden path="bike.id.email" value="${i['email']}" />
                                        <input type="submit" value="Save" class="btn btn-primary">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form:form>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</t:custom_layout> 
