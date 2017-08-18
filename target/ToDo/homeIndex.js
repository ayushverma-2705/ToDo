function createIt(id, title, description, name, date, assignTo, assignBy, status) {
    var str = "<div class=\"panel panel-default\">";
    str += "<div class=\"panel-heading\">";
    str += "<h4 class=\"panel-title\">";


    str += "<table border=\"0\" style=\"border:none\">";
    str += "<tr><td><a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#q" + id + "\">" + title + "</a></td>";

    if (status < 2) {
        str += "<form method=\"post\" name=\"login_form\">";

        if (status === 0) {
            str += "<td ><input type=\"text\" tabindex=\"1\"  id=\"" + id + "_input\" placeholder=\"Assign To\"  class=\"inputtext radius1\"  required></td>";
        }

        str += "<td ><input onclick = \"assign(" +  id  + "," + status + ");\" type=\"submit\" class=\"fbbutton\" name=\"login\" value=\"Shift\" /></td></tr></form>";
    }

  //  str += "<td ><input onclick = \"deleteIt(" +  id  + "," + status + ");\" type=\"submit\" class=\"fbbutton\" name=\"login\" value=\"Delete\" /></td></tr></form>";

    str += "</table></h4></div>";

    str += "<div id=\"q" + id + "\" class=\"panel-collapse collapse in\">";

    str += "<div class=\"panel-body\"> Description :- " + description + "<br>  Creator Name :- " + name +
     "<br> Creation Date :- " + date;

    var str2 = "";

    if (status != 0) {
        str2 += "<br> Assign To :- " + assignTo + "<br> Assign By  :- " + assignBy;
    }

    str += str2 + "</div>";

    str += "</div></div>";

    return str;
}

function displayIt(arrUnassigned, arrAssigned, arrCompleted) {
    console.log("  In Java Script of Displaying ToDo's ");
    console.log("arrUnassigned :- ", arrUnassigned);
    console.log("arrAssigned :- ", arrAssigned);
    console.log("arrCompleted :- ", arrCompleted);

    var str = "<div class=\"panel-group\" id=\"accordion\">  <label class=\"trying textcenter\"> UnAssigned ToDo's </label>";

    for (var i in arrUnassigned) {
        str += createIt(arrUnassigned[i]["tId"], arrUnassigned[i]["title"],  arrUnassigned[i]["description"], arrUnassigned[i]["creatorName"], arrUnassigned[i]["creationDate"], arrUnassigned[i]["assignTo"], arrUnassigned[i]["assignBy"], 0);
    }

    str += "</div>";


    document.getElementById("pending").innerHTML = str;


    var str = "<div class=\"panel-group\" id=\"accordion\">  <label class=\"trying textcenter\"> Assigned ToDo's </label>";

    for(var i in arrAssigned){
        str += createIt(arrAssigned[i]["tId"], arrAssigned[i]["title"],  arrAssigned[i]["description"], arrAssigned[i]["creatorName"], arrAssigned[i]["creationDate"], arrAssigned[i]["assignTo"], arrAssigned[i]["assignBy"], 1);
    }

    str += "<div>";
    document.getElementById("progress").innerHTML = str;


    var str = "<div class=\"panel-group\" id=\"accordion\">  <label class=\"trying textcenter\"> Completed ToDo's </label>";

    for(var i in arrCompleted){
        str += createIt(arrCompleted[i]["tId"], arrCompleted[i]["title"],  arrCompleted[i]["description"], arrCompleted[i]["creatorName"], arrCompleted[i]["creationDate"], arrCompleted[i]["assignTo"], arrCompleted[i]["assignBy"], 2);;
    }

    str += "<div>";
    document.getElementById("completed").innerHTML = str;


    $(function() {
        $(".collapse").collapse('hide');
    });
}

function updateLast(UpUnassigned, UpAssigned, UpCompleted) {
    console.log(" In update Last  ");
    console.log(" Found UpUnassigned :-- " , UpUnassigned);
    console.log(" Found UpAssigned :-- " , UpAssigned);
    console.log(" Found UpCompleted :-- " , UpCompleted);

    console.log(" Found window.lastUnassigned :-- " , window.lastUnassigned);
    console.log(" Found window.lastAssigned :-- " , window.lastAssigned);
    console.log(" Found window.lastCompleted :-- " , window.lastCompleted);

    for (var j in UpUnassigned) {
        var found = false;

        for (var i in window.lastUnassigned) {
            index = index + 1;

            if (window.lastUnassigned[i]["tId"] === UpUnassigned[j]["tId"]) {
                found = true;
                break;
            }
        }

        if (found === false) {
            window.lastUnassigned.push(UpUnassigned[j]);
        }
    }


    for (var j in UpAssigned) {
        var index = -1;
        var found = false;

        for (var i in window.lastUnassigned) {
            index = index + 1;
            if (window.lastUnassigned[i]["tId"] === UpAssigned[j]["tId"]) {
                found = true;
                break;
            }
        }


        if (found === true) {
          //  console.log(" getting in last unasigned ");
            window.lastUnassigned.splice(index, 1);
        }

        found = false;

        for (var i in window.lastAssigned) {
            index = index + 1;

            if (window.lastAssigned[i]["tId"] === UpAssigned[j]["tId"]) {
                found = true;
                break;
            }
        }

        if (found === false) {
            window.lastAssigned.push(UpAssigned[j]);
        }
    }


    for (var j in UpCompleted) {
        var index = -1;
        var found = false;

        for (var i in window.lastUnassigned) {
            index = index + 1;
            if (window.lastUnassigned[i]["tId"] === UpCompleted[j]["tId"]) {
                found = true;
                break;
            }
        }

        if (found === true) {
            //console.log(" Found in last Unassigned at index", index);
            window.lastUnassigned.splice(index, 1);
        }

        index = -1;
        found = false;

        for (var i in window.lastAssigned) {
            index = index + 1;
            if (window.lastAssigned[i]["tId"] === UpCompleted[j]["tId"]) {
                found = true;
                break;
            }
        }

        if (found === true) {
          //  console.log(" Found in last Assigned at index", index);
            window.lastAssigned.splice(index, 1);
        }

        found = false;
        index = -1;

        for (var i in window.lastCompleted) {
            index = index + 1;

            if (window.lastCompleted[i]["tId"] === UpCompleted[j]["tId"]) {
                found = true;
                break;
            }
        }

        if (found === false) {
            window.lastCompleted.push(UpCompleted[j]);
        }
    }

    console.log("After Completion");
    console.log(" Found window.lastUnassigned :-- " , window.lastUnassigned);
    console.log(" Found window.lastAssigned :-- " , window.lastAssigned);
    console.log(" Found window.lastCompleted :-- " , window.lastCompleted);


}


function getLists() {
    console.log(" Opening GetLists function");
    var url = "/ToDo/addTask";
    var http = new XMLHttpRequest();

    var params = "&Date=" + window.date;

    http.open("GET", url + "?" + params, true);
    http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState == 4 && http.status == 200) {
            console.log(" In Get http response :- ", http.responseText);

            data = JSON.parse(http.responseText);

            UpUnassigned = []
            UpAssigned = []
            UpCompleted = []


            if (window.lastUnassigned === undefined) {
                window.lastUnassigned = []
                window.lastAssigned = []
                window.lastCompleted = []
            }

            UpUnassigned = JSON.parse(data["pending"]);
            UpAssigned = JSON.parse(data["progress"]);
            UpCompleted = JSON.parse(data["completed"]);

            updateLast(UpUnassigned, UpAssigned, UpCompleted);

            window.date = data["Date"];

            console.log("Window Date Updated ", window.date);

            displayIt(window.lastUnassigned, window.lastAssigned, window.lastCompleted);
            //cancelToDo();
        } else if (http.status != 200) {
            document.body.innerHTML = http.responseText;
        }

    }

    http.send(null);
}


console.log(" Reloading Home.js");
getLists();

function addToDoButton() {
    document.getElementById("addTaskForm").style.display="block";
}

function addToDo() {
    var method = "post"; // Set method to post by default if not specified.

    console.log("Inside Post Javascript method");

    var username = document.getElementById("title").value;
    var password = document.getElementById("description").value;
    var params = "title=" + username + "&description=" + password + "&Date=" + window.date;

    var http = new XMLHttpRequest();
    http.open(method, "/ToDo/addTask", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            console.log("Post Java Script Response");
            console.log("response Text = " , http.responseText);

//            data = JSON.parse(http.responseText);
//            window.date = data["Date"];
//            console.log("Window Date Updated ", window.date);


            getLists();
        }
    }
    http.send(params);
}

function cancelToDo() {
    document.getElementById("addTaskForm").style.display="none";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function assign(id, status) {
    console.log(" Inside Assign");

    var tId = id;

    var assignTo = "";

    if (status === 0) {
        assignTo = document.getElementById(id + "_input").value;
    }

    console.log(" id :- " ,id, " status :- " ,status, " input :- " ,assignTo , "Date :-- ", Window.date);

    var params = "todoid=" + tId + "&assignTo=" + assignTo + "&status=" + status  + "&Date=" + window.date;

    var http = new XMLHttpRequest();
    http.open("post", "/ToDo/modify", true);

    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    var index = -1;
    var obj = null;

    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            console.log("Inside Assign response");
            getLists();
        } else if(http.status != 200){
          document.body.innerHTML = http.responseText;
      }
    }

    http.send(params);
}

function deleteIt() {}

/*
function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}

var delete_cookie = function(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

function logOut() {
    var temp_cookie = getCookie("session-id");
    delete_cookie("session-id");

    var url = "/ToDo/logOut";
    var http = new XMLHttpRequest();

    var params = "&Cookie=" + temp_cookie;

    http.open("GET", url + "?" + params , true);

    http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState == 4 && http.status == 200) {

        }
    }

    http.send(null);
}
*/