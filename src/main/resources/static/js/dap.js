userGmail = function() {
	email=prompt("Utilisateur ?");
	window.open("http://localhost:8080/email/nbunread?userKey=" + email);
}
userEvent = function() {
	event=prompt("Utilisateur ?");
	window.open("http://localhost:8080/event/next?userKey=" + event);
}
userRename = function() {
	user=prompt("Utilisateur ?");
	newuser=prompt("Nouvel Utilisateur ?")
	newuser=newuser.replace( /[^a-zA-Z]/ , "");
	window.open("http://localhost:8080/changeUserName?userKey="+ user +"&newUserKey=" + newuser, "_self" );
}