<%@page import="userInteraction.WebInteraction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="userInteraction.WebInteractionImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sudoku Game Solution</title>
</head>
	<body>
		<h1 class="title">Sudoku Game Solver</h1>
		<div class="subtitle">Solution:</div>
		<%
		    WebInteractionImpl interaction = new WebInteractionImpl();
			for (int row = 0; row < 9; row++)  {
				for (int col = 0; col < 9; col++) {
					String value = Integer.valueOf(request.getParameter("r"+row+"c"+col));
					interaction.addCellOfUnsolvedGame(col, row, value);
				}
			}
			
			interaction.solveIt();
			
			
		%>
		
		
		<table>
		<% 
			for (int row = 0; row < 9; row++)  {
		%>
			<tr>
			<% for (int col = 0; col < 9; col++) {
			%>
				<td class="c<%=row%3 * 3 + col%3 %>">
					
				</td>
			<% }
			%>
			</tr>
		<% } 
		%>
		</table>
	</body>
</html>