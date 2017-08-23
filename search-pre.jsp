<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main" class="registerClass">
            <h3 style="text-align: center">Here you can find available offers:</h3>
            <div class="under-register-class">
                <form:form cssClass="search-form" action="/TopSpeed/ad/search-pre.htm" method="post" commandName="adForm">
                    <table class="search-pre-table">
                        <tbody>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td>
                                    Model<br/>
                                    <form:select path="bike.model" cssClass="form-control"> 
                                        <form:option value="" label="--Select phone--"> 
                                            <form:options items="${modelList}"/> 
                                        </form:option>
                                    </form:select> 
                                </td>
                                <td>
                                    Color<br/>
                                    <form:select path="bike.color" cssClass="form-control"> 
                                        <form:option value="" label="--Select phone--"> 
                                            <form:options items="${colorList}"/> 
                                        </form:option>
                                    </form:select> 
                                </td>
                                <td>
                                    Year of Production<br/>
                                    <form:select path="bike.yearOfPro" cssClass="form-control"> 
                                        <form:option value="0" label="--Select phone--"> 
                                            <form:options items="${yOfProList}"/> 
                                        </form:option>
                                    </form:select> 
                                </td>
                            </tr>
                            <tr>
                                <td class="search-range">
                                    Price
                                    <div style="clear:both;"></div> 
                                    <form:input path="bike.price" placeholder="From" class="form-control"/>
                                    <input type="text" placeholder="To" class="form-control" name="to-limit"/>
                                    <div style="clear:both;"></div>
                                </td>
                                <td >
                                    City<br/>
                                    <form:select path="bike.city" cssClass="form-control"> 
                                        <form:option value="" label="--Select phone--"> 
                                            <form:options items="${cityList}"/> 
                                        </form:option>
                                    </form:select> 
                                </td>
                                <td >
                                    Country<br/>
                                    <form:select path="bike.country" cssClass="form-control"> 
                                        <form:option value="" label="--Select phone--"> 
                                            <form:options items="${countryList}"/> 
                                        </form:option>
                                    </form:select> 
                                </td>
                            </tr>
                            <tr> 
                                <td>
                                </td>
                                <td>
                                </td>
                                <td> 
                                    <input value="Confirm" type="submit" class="btn btn-primary"/> 
                                </td> 
                            </tr> 
                        </tbody>
                    </table> 
                </form:form>
            </div>
        </div>
    </jsp:attribute>
</t:custom_layout> 
