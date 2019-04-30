userGmail = function() {
	email = prompt("Utilisateur ?");
	window.open("http://localhost:8080/email/nbunread?userKey=" + email);
}

userEvent = function() {
	event = prompt("Utilisateur ?");
	window.open("http://localhost:8080/event/next?userKey=" + event);
}

userRename = function() {
	user = prompt("Utilisateur ?");
	newUser = prompt("Nouvel Utilisateur ?")
	if (newUser !== null && newUser !== "") {
		newUser = newUser.replace(/[^a-zA-Z0-9]/g, "");
		window.open("http://localhost:8080/changeUserName?userKey=" + user
				+ "&newUserKey=" + newUser, "_self");
	}
}
