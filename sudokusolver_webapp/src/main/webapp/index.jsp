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
		<% if (request.getMethod().equals("GET")) { %>

		<div class="subtitle">Type in your Sudoku Game...</div>
		<form action="" method="post">
			<table>
			<% for (int row = 0; row < 9; row++)  { %>
				<tr>
				<% for (int col = 0; col < 9; col++) { %>
					<td class="cell c<%=row%3 * 3 + col%3 %>">
						<input type="text" name="<%="r"+row+"c"+col %>" maxlength="1"/>
					</td>
				<% } %>
				</tr>
			<% } %>
			</table>
			<div class="submit">
				<input class="submit" type="button" value="Solve it!" onclick="submit()"/>
			</div>
		</form>

		<% } else { %>

		<div class="subtitle">Solution:</div>
		<table>
		<% for (int row = 0; row < 9; row++)  { %>
			<tr>
			<% for (int col = 0; col < 9; col++) { %>
				<td class="cell c<%=row%3 * 3 + col%3 %>">
					2
				</td>
			<% } %>
			</tr>
		<% } %>
		</table>
		<div class="back">
			<button onclick="window.open(window.location, '_self');">
				Go back
			</button>
		</div>
		<% } %>
	</body>
	
</html>