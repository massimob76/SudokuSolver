<%@page import="utils.LoggerInitializer"%>
<%@page import="java.util.logging.LogManager, java.io.FileInputStream, java.io.IOException, utils.LoggerInitializer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%! { LoggerInitializer.initialize(); } %>
<%	
	String errorMsg = "";
	workflow.WebInteractionImpl interaction = null;
	boolean solutionFound = false;
	if (request.getMethod().equals("POST")) {
		interaction = new workflow.WebInteractionImpl();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				String value = request.getParameter("r" + row + "c" + col);
				interaction.addCellOfUnsolvedGame(col, row, value);
			}
		}

		try {
			interaction.solveIt();
			solutionFound = true;
		} catch (Exception e) {
			errorMsg = e.getMessage();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="sudoku.css">
<script type="text/javascript" src="sudoku.js"></script>
<script type="text/javascript">
	var page='<%=solutionFound? "solution" : errorMsg.equals("")? "landing" : "error" %>'
</script>
<script type="text/javascript" src="analyticsTracking.js"></script>
<title>Sudoku Game</title>
</head>
<body onload="keydown_listener();">
	<h1 class="title">Sudoku Game Solver</h1>
	
	<%
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
					int value = interaction.getNextCellValueFromSolvedGame();
					boolean isSolution = !Integer.toString(value).equals(request.getParameter("r" + row + "c" + col));
			%>
			<td class="cell c<%=row % 3 * 3 + col % 3%> <%=isSolution?"solution":"" %>">
				<%=value%>
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
		<%=interaction.getElapsedTime()%>ms
	</div>
	<div class="back">
		<button onclick="window.open(window.location, '_self');">Another game</button>
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
						String value = request.getParameter("r" + row + "c" + col);
						value = (value==null) ? "" : value;
				%>
				<td class="cell c<%=row % 3 * 3 + col % 3%>">
					<input type="text" name="<%="r" + row + "c" + col%>" value="<%= value %>" maxlength="1" 
					onclick="cell_values_correct();" onkeydown="cell_values_correct();" />
				</td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<div id="errorMsg" class="error">
			<%=errorMsg%>
		</div>
		<div class="submit">
			<input type="button" value="Solve it!" onclick="if (cell_values_correct()) {submit()}" />
		</div>
	</form>

	<%
		}
	%>
</body>

</html>