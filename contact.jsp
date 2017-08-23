<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:custom_layout title = "index page">
    <jsp:attribute name="body_area">
        <div id="main">
            <div id="contact-form" class="clearfix">
                <h3>Contact Us!</h3>
                <ul id="errors" class="">
                    <li id="info">There were some problems with your form submission:</li>
                </ul>
                <p id="success">Thanks for your message! We will get back to you ASAP!</p>
                <form:form cssClass="registerForm" action="/TopSpeed/static/contact.htm" method="post" commandName="enqForm">
                    <form:input path="enqName" placeholder="Pera Detlic" class="form-control"/>
                    <form:input path="enqEmail" placeholder="pera@detlic.com" class="form-control"/>
                    <form:input path="enqPhoneNo" placeholder="telephone" class="form-control"/>
                    <form:select path="enqType" class="form-control">
                        <form:option value="" label="Select Enquiry:" />
                        <form:options items="${enqType}" />
                    </form:select>
                    <form:textarea path="enqMsg" placeholder="Note..." class="form-control"/>
                    <span id="loading"></span>
                    <input type="submit" value="Send!" id="submit-button" />
                    <p id="req-field-desc"><span class="required">*</span> Indicates a required field</p>
                </form:form>
            </div>
        </div>
    </jsp:attribute>
</t:custom_layout> 
