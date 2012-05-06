<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="workflow.WebInteractionImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="sudoku.css">
<title>Sudoku Game</title>
</head>
<body>
	<h1 class="title">Sudoku Game Solver</h1>
	<%
		String errorMsg = "";
		WebInteractionImpl interaction = null;
		boolean solutionFound = false;
		if (request.getMethod().equals("POST")) {
			interaction = new WebInteractionImpl();
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					String value = request.getParameter("r" + row + "c" + col);
					if (!value.equals("")) {
						interaction.addCellOfUnsolvedGame(col, row, value);
					}
				}
			}

			try {
				interaction.solveIt();
				solutionFound = true;
			} catch (Exception e) {
				errorMsg = e.getMessage();
			}

		}

		if (solutionFound) {
	%>

	<div class="subtitle">Solution:</div>
	<table>
		<%
			for (int row = 0; row < 9; row++) {
		%>
		<tr>
			<%
				for (int col = 0; col < 9; col++) {
			%>
			<td class="cell c<%=row % 3 * 3 + col % 3%>"><%=interaction.getNextCellValueFromSolvedGame()%>
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<div class="elapsedTime">
		solution found in
		<%=interaction.getElapsedTime()%>>ms
	</div>
	<div class="back">
		<button onclick="window.open(window.location, '_self');">Go
			back</button>
	</div>

	<%
		} else {
	%>

	<div class="subtitle">Type in your Sudoku Game...</div>
	<form action="" method="post">
		<table>
			<%
				for (int row = 0; row < 9; row++) {
			%>
			<tr>
				<%
					for (int col = 0; col < 9; col++) {
				%>
				<td class="cell c<%=row % 3 * 3 + col % 3%>"><input type="text"
					name="<%="r" + row + "c" + col%>" maxlength="1" /></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<div class="error">
			<%=errorMsg%>
		</div>
		<div class="submit">
			<input type="button" value="Solve it!" onclick="submit()" />
		</div>
	</form>

	<%
		}
	%>
</body>

</html>