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

        .button-gray {
            background-color: #e0e0e0;
            color: #888888;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<%@page import="model.ExamPlan" %>
<%@page import="java.util.ArrayList" %>
<% ArrayList<ExamPlan> examPlans = (ArrayList)request.getAttribute("examPlans");%>
<% int userId = (int)request.getAttribute("userId"); %>

<div class="container">
    <div class="page-selector">
        <h2>資格管理システム</h2>
        <ul>
            <li><a href="SikakuView?userId=<%=userId %>">合格資格一覧</a></li>
            <li><a href="VoucherView?userId=<%=userId %>">バウチャー一覧</a></li>
            <li><a href="ScheduleView?userId=<%=userId %>"><strong>受験予定資格</strong></a></li>
        </ul>
    </div>

    <div class="data-table">
        <h2>資格取得スケジュール</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>番号</th>
                    <th>資格名</th>
                    <th>申込状況</th>
                    <th>受験日</th>
                </tr>
            </thead>
            <tbody>
            	<%
            		for(int i=0; i < examPlans.size(); i++) {
            			ExamPlan plan = examPlans.get(i);
            			out.print("<tr>");
            			out.print("<td>" + (i+1) + "</td>");
            			out.print("<td>" + plan.getExamName() + "</td>");
            			
            			if (plan.getApplicationStatus() == 0) {
            				out.print("<td><button class='button-gray' disabled>予約済み</button></td>");
            			} else {
            				out.print("<td><a href=''./reservationInfo.html' class='btn btn-primary'>予約情報登録</a></td>");
            			}
            			
            			out.print("<td>" + plan.getJapaneseExamDateString() + "</td>");
            			
            			out.print("<tr>");
            		}
            	%>
            </tbody>
        </table>
        <a href="./scheduleAdd.html" class="btn btn-primary">スケジュール追加</a>
    </div>
</div>

<!-- Bootstrap JS (Popper.js and Bootstrap JS) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
