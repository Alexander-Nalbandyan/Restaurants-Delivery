<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" lang="en-us"/>
</head>
<body>
<h1> Register Address for Restaurant</h1>


<c:if test="${not empty resultStatus}">
    <c:choose>
        <c:when test="${resultStatus == 'error'}">
            <h2 style="color: red">Entered Address is out of delivery range</h2>
        </c:when>
        <c:when test="${resultStatus == 'success'}">
            <h2 style="color: green">User Address was successfully saved</h2>
        </c:when>
    </c:choose>
</c:if>


<form method="POST" action="/save-user-address.htm" name="save_user_address_form" onsubmit="return false;">
    <fieldset>
        <label for="user_address">Address: *</label>
        <input type="text" name="address" id="user_address" value="${address}">
    </fieldset>

    <fieldset>
        <label for="restaurant">Restaurant: *</label>
        <select name="restaurantId" id="restaurant">
            <option value="">Select</option>
            <c:forEach items="${restaurants}" var="restaurantInfo">
                <option value="${restaurantInfo.id}" ${restaurantId == restaurantInfo.id?'selected':''}>${restaurantInfo.name}</option>
            </c:forEach>
        </select>
    </fieldset>

    <input type="button" value="Save" onclick="validateAndSubmit();">
</form>



<script type="text/javascript">
    function validateAndSubmit() {
        var userAddress = document.getElementById("user_address").value;
        if (!userAddress.trim()) {
           alert("Please enter user address");
           return;
        }

        var restaurantId =document.getElementById("restaurant").value;
        if (!restaurantId.trim()) {
            alert("Please select Restaurant");
            return;
        }

        document.forms["save_user_address_form"].submit();
    }
</script>
</body>
</html>