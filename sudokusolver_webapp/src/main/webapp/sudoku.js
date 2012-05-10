
function valid_old(value) {
	if (value==="") {
		return true;
	}
	for (i in ["a", "1", "2", "3", "4", "5", "6", "7", "8", "9"]) {
		alert("i: " + i);
		if (i==value) {
			return true;
		}
	}
	return false;
}

function valid(value) {
	return (value=="" || ((value >= 1) && (value <= 9)));
}

function display_error_msg() {
	errorMsg = document.getElementById("errorMsg").childNodes[0];
	errorMsg.data = "Please fix Cells in red";
}

function cell_values_correct() {
	retVal = true;
	for (row = 0; row < 9; row++) {
		for (col = 0; col < 9; col++) {
			element = document.getElementsByName("r" + row + "c" + col)[0];
			if (!valid(element.value)) {
				element.classList.add("error");
				display_error_msg();
				retVal = false;
			} else {
				element.classList.remove("error");				
			}
		}
	}
	return retVal;

}