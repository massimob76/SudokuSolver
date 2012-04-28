<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="sudoku.css">
	<title>Sudoku Game</title>
	</head>
	<body>
		<h1 class="title">Sudoku Game Solver</h1>
		<div class="subtitle">Type in your Sudoku Game...</div>
		<form action="solution.jsp" method="post">
			<table>
			<% for (int row = 0; row < 9; row++)  {
			%>
				<tr>
				<% for (int col = 0; col < 9; col++) {
				%>
					<td class="c<%=row%3 * 3 + col%3 %>">
						<input class="cell" type="text" name="<%="r"+row+"c"+col %>" maxlength="1"/>
					</td>
				<% }
				%>
				</tr>
			<% } 
			%>
			</table>
			<div class="submit">
				<input class="submit" type="button" value="Solve it!" onclick="submit()"/>
			</div>
		</form>
	</body>
	
</html>