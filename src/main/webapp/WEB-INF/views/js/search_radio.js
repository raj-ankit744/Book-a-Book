function show1() {
	document.getElementById("isbn_text").disabled = false;
	document.getElementById("title_text").disabled = true;
	document.getElementById("author_text").disabled = true;
}
function show2() {
	document.getElementById("isbn_text").disabled = true;
	document.getElementById("title_text").disabled = false;
	document.getElementById("author_text").disabled = true;
}
function show3() {
	document.getElementById("isbn_text").disabled = true;
	document.getElementById("title_text").disabled = true;
	document.getElementById("author_text").disabled = false;
}		