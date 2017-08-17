function executer(url,method,params,callbackFunc) {
    console.log(" in executer ")
    var http = new XMLHttpRequest();
    http.open(method, url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            callbackFunc(http.responseText);
        }
    }
    http.send(params);
}

function userLogin() {
    var method = "post"; // Set method to post by default if not specified.


    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var params = "username=" + username + "&password=" + password;

    executer("/ToDo/login","post",params,loginFunc);
}

function userRegister() {
    console.log(" registeration")
    var method = "post"; // Set method to post by default if not specified.


    var username = document.getElementById("username1").value;
    var password = document.getElementById("password1").value;

    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;


    var params = "username=" + username + "&password=" + password + "&email=" + email + "&phone=" + phone;

    executer("/ToDo/register","post",params,registerFunc);


}


function loginFunc(response) {
    window.location = "http://localhost:8080/ToDo/home.html";
  //  alert(response);
}

function registerFunc(abc) {
    window.location = "http://localhost:8080/ToDo/home.html";
  //  console.log(abc);
}