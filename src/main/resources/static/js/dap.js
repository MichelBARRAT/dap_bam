userGmail = function() {
	email = prompt("Utilisateur ?");
	//TODO bam by Djer |MVC| Attention tu as changé les URIs !! (allnbunread)
	//TODO bam by Djer |Thymleaf| Il ne faut jamais mettre le "hote" quand tu fais référence à toi-même. SI j'installe ton appli sur mon serveur "dap.djer.fr" plus aucun des tes liens ne fonctionne ! Thymeleaf porpose le @{}, mais un peu compelexe à utilsier en JS (éventuellement à fournir en paramètre de ta "méthode JS")
	window.open("http://localhost:8080/email/nbunread?userKey=" + email);
}

userEvent = function() {
	event = prompt("Utilisateur ?");
    //TODO bam by Djer |MVC| Attention tu as changé les URIs !! (/allevent/next)
	//TODO bam by Djer |Thymleaf| Il ne faut jamais mettre le "hote" quand tu fais référence à toi-même.
	window.open("http://localhost:8080/event/next?userKey=" + event);
}

userRename = function() {
	user = prompt("Utilisateur ?");
	newUser = prompt("Nouvel Utilisateur ?")
	if (newUser !== null && newUser !== "") {
		newUser = newUser.replace(/[^a-zA-Z0-9]/g, "");
		//TODO bam by Djer |Thymleaf| Il ne faut jamais mettre le "hote" quand tu fais référence à toi-même.
		window.open("http://localhost:8080/changeUserName?userKey=" + user
				+ "&newUserKey=" + newUser, "_self");
	}
}
