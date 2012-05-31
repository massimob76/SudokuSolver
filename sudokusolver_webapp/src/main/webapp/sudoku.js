
function set_error_msg(msg) {
	var fieldNameElement = document.getElementById("errorMsg");
  	while(fieldNameElement.childNodes.length >= 1) {
  		fieldNameElement.removeChild(fieldNameElement.firstChild);
  	}
  	fieldNameElement.appendChild(fieldNameElement.ownerDocument.createTextNode(msg));
}

function myset() {
	this.values_so_far = [];
	this.add = function add(value) {
		if (value==null || value=="") {
			return true;
		}
		if (this.values_so_far[value]==null) {
			this.values_so_far[value]=true;
			return true;
		} else {
			return false;
		}
	};
}

function check_unique_element(set, row, col) {
	element = document.getElementsByName("r" + row + "c" + col)[0];
	if (set.add(element.value)) {
		element.className = "";
		return true;
	} else {
		element.className = "error";
		return false;				
	}
}

function valid(value) {
	return (value=="" || ((value >= 1) && (value <= 9)));
}

function values_correct() {
	var correct = true;
	for (row = 0; row < 9; row++) {
		for (col = 0; col < 9; col++) {
			element = document.getElementsByName("r" + row + "c" + col)[0];
			if (valid(element.value)) {
				element.className = "";
			} else {
				element.className = "error";
				correct = false;				
			}
		}
	}
	return correct;
}

function columns_correct() {
	correct = true;
	for (col = 0; col < 9; col++) {
		var set = new myset();
		for (row = 0; row < 9; row++) {
			if (!check_unique_element(set, row, col)) {
				correct = false;
			}
		}
	}
	return correct;
}

function rows_correct() {
	correct = true;
	for (row = 0; row < 9; row++) {
		var set = new myset();
		for (col = 0; col < 9; col++) {
			if (!check_unique_element(set, row, col)) {
				correct = false;
			}
		}
	}
	return correct;
}

function square_correct() {
	correct = true;
	for (square = 0; square < 9; square++) {
		var set = new myset();
		var row_shift = Math.floor(square / 3) * 3;
		var col_shift = square % 3 * 3;
		for (row = row_shift; row < row_shift + 3; row++) {
			for (col = col_shift; col < col_shift + 3; col++) {
				if (!check_unique_element(set, row, col)) {
					correct = false;
				}
			}
		}
	}
	return correct;
}

function cell_values_correct() {
	if (values_correct()) {
		if (columns_correct()) {
			if (rows_correct()) {
				if (square_correct()) {
					set_error_msg("");
					return true;
				} else {
					set_error_msg("Square must have unique values");
				}
			} else {
				set_error_msg("Rows must have unique values");			
			}
		} else {
			set_error_msg("Columns must have unique values");			
		}
	} else {
		set_error_msg("Please insert a value between 1 and 9");
	}
	return false;
}

document.onkeydown = keydown;  

function keydown(event) {  
    var code;  
    var e;  
    if (document.all) {  
        if (event) {  
            var e = window.event;  
            code = e.keyCode;  
        }  
    }  
    else if (event.which) {  
    code = event.which;  
    e = event;  
    }  
    if (code == 9) {
    	cell_values_correct();
    }  
}  





