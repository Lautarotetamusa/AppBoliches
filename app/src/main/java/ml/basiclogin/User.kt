package ml.basiclogin

class User (
    var username : String,
    var password : String,
    ) {

    fun loginUser() : Boolean {
        return login(username, password, "usuarios");
    }

    fun registerUser() : Boolean {
        return register(username, password, "usuarios");
    }
}