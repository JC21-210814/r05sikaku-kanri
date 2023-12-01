<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>資格管理システム</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background-color: #eef7f1; /* 薄いグリーンの背景色 */
        }

        .page-selector {
            width: 30%;
            float: left;
            padding: 20px;
        }

        .data-table {
            width: 70%;
            float: left;
        }

        .data-table table {
            width: 100%;
        }

        .data-table th, .data-table td {
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@page import="model.Voucher" %>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Voucher> ownedVouchers = (ArrayList)request.getAttribute("ownedVouchers"); %>
<% int userId = (int)request.getAttribute("userId"); %>

<div class="container">
    <div class="page-selector">
        <h2>資格管理システム</h2>
        <ul>
            <li><a href="<%=request.getContextPath() %>/SikakuView?userId=<%=userId %>">合格資格一覧</a></li>
            <li><a href="<%=request.getContextPath() %>/VoucherView?userId=<%=userId %>"><strong>バウチャー一覧</strong></a></li>
            <li><a href="<%=request.getContextPath() %>/ScheduleView?userId=<%=userId %>">受験予定資格</a></li>
        </ul>
    </div>

    <div class="data-table">
        <h2>バウチャー一覧</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>番号</th>
                    <th>バウチャー名</th>
                    <th>期限</th>
                </tr>
            </thead>
            <tbody>
            	<%
            		for(int i=0; i < ownedVouchers.size(); i++) {
            			Voucher voucher = ownedVouchers.get(i);
            			out.print("<tr>");
            			out.print("<td>" + (i+1) + "</td>");
            			out.print("<td>" + voucher.getName() + "</td>");
            			out.print("<td>" + voucher.getJapaneseLimitDate() + "</td>");
            			out.print("</tr>");
            		}
            	%>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS (Popper.js and Bootstrap JS) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
