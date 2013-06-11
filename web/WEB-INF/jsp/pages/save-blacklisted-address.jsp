<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" lang="en-us"/>
</head>
<body>
<h1> Add Black listed Address for Restaurant</h1>


<c:if test="${not empty resultStatus}">
    <c:choose>
        <c:when test="${resultStatus == 'error'}">
            <h2 style="color: red">Entered Address is out of delivery range</h2>
        </c:when>
        <c:when test="${resultStatus == 'success'}">
            <h2 style="color: green">Blacklisted Address was successfully saved</h2>
        </c:when>
    </c:choose>
</c:if>


<form method="POST" action="/save-blacklisted-address.htm" name="save_blacklisted_address_form" onsubmit="return false;">
    <fieldset>
        <label for="black_address">Address: *</label>
        <input type="text" name="address" id="black_address" value="${address}">
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

    <fieldset>
        <label for="radius">Radius(miles): *</label>
        <select name="radius" id="radius">
            <option value="1000">1000</option>
            <option value="1500">1500</option>
            <option value="2000">2000</option>
            <option value="2500">2500</option>
            <option value="3000">3000</option>
        </select>
    </fieldset>

    <input type="button" value="Add" onclick="validateAndSubmit();">
</form>



<script type="text/javascript">
    function validateAndSubmit() {
        var userAddress = document.getElementById("black_address").value;
        if (!userAddress.trim()) {
           alert("Please enter address");
           return;
        }

        var restaurantId =document.getElementById("restaurant").value;
        if (!restaurantId.trim()) {
            alert("Please select Restaurant");
            return;
        }

        document.forms["save_blacklisted_address_form"].submit();
    }
</script>
</body>
</html>