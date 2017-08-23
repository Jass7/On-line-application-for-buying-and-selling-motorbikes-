<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <div class="centeringDiv">
                <form:form cssClass="registerForm" method="post" action="/TopSpeed/ad/give-ad.htm" enctype="multipart/form-data" commandName="adForm">
                    <table >
                        <tbody>
                            <tr>
                                <td colspan="2"><form:input path="bike.id.regId" placeholder="Bike ID" class="form-control"/></td>
                                <td colspan="2"><form:input path="bike.model" placeholder="Model" class="form-control"/></td>
                                <td colspan="2">
                                    <form:select path="bike.color" class="form-control">
                                        <form:option value="" label="Select Color" />
                                        <form:options items="${colorList}" />
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><form:input path="bike.yearOfPro" placeholder="Year of Production" class="form-control"/></td>
                                <td colspan="2"><form:input path="bike.mileage" placeholder="Mileage" class="form-control"/></td>
                                <td colspan="2"><form:input path="bike.validityOfReg" placeholder="Validity of registration" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <form:select path="bike.genCondition" class="form-control">
                                        <form:option value="" label="Select General Condition" />
                                        <form:options items="${genConditionList}" />
                                    </form:select>
                                </td>
                                <td colspan="2"><form:input path="bike.country" placeholder="Country" class="form-control"/></td>
                                <td colspan="2"><form:input path="bike.city" placeholder="City" class="form-control"/></td>
                            </tr> 
                            <tr>
                                <td colspan="2"><form:input path="bike.price" placeholder="Price" class="form-control"/></td>
                                <td colspan="2"><form:input path="adValidityDate" placeholder="Ad Validity" class="form-control"/></td>
                            </tr> 
                            <tr>
                                <td colspan="6"><form:textarea path="bike.tempNote" placeholder="Note.." class="form-control"/></td>
                            </tr>
                            <tr>
                                <td colspan="3">Select Photo: </td>
                                <td colspan="3"><input type="file" name="photo" class="btn btn-primary"></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Save Ad" class="btn btn-success">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
    </jsp:attribute>
</t:custom_layout> 
