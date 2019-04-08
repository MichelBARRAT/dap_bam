myJsFunc = function() {
	email=prompt("Utilisateur ?");
	window.open("http://localhost:8080/email/nbunread?userKey=" + email);
}
myJsFunc2 = function() {
	event=prompt("Utilisateur ?");
	window.open("http://localhost:8080/event/next?userKey=" + event);
}