<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Installments</title>
    <link rel="stylesheet" type="text/css"
          href="https://www.w3.org/WAI/content-assets/wai-aria-practices/patterns/table/examples/css/sortable-table.css">
</head>
<body>
<h1>Installments</h1>
<div class="table-wrap">
    <table class="sortable" id="installment-table">
        <caption>
            All Paid Installments to parent Id - ${parentId}<BR><BR>
        </caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Sender</th>
            <th>Receiver</th>
            <th>Total Amount</th>
            <th>Paid Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${installments}" var="installment">
            <tr>
                <td>${installment.id}</td>
                <td>${installment.sender}</td>
                <td>${installment.receiver}</td>
                <td>${installment.totalAmount}</td>
                <td>${installment.paidAmount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>