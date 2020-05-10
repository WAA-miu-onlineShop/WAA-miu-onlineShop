$(function () {
    var getHost = function() {
        var port = (window.location.port == "9090") ? ":9090" : "";
        return ('http://') + window.location.hostname + port;
    };

    $("#loginForm11").submit(function (event) {
        location.replace("http://localhost:9090/main/")
        // let username = $("#username").val();
        // let password = $("#password").val();
        // let loginFormErrors = $("#loginFormErrors");
        //
        // var loginData = {
        //     "username": username,
        //     "password": password
        // };
        //
        // $.ajax({
        //     "url": "http://localhost:8083/pick_n_go_app_war_exploded/login",
        //     "type": "POST",
        //     "dataType": "json",
        //     "contentType": "application/json",
        //     "data": JSON.stringify(loginData)
        // }).done(function (obj) {
        //     loginFormErrors.html("login successful");
        //     loginFormErrors.css("display", "block");
        //     loginFormErrors.removeClass("alert-danger");
        //     loginFormErrors.addClass("alert-success");
        //     console.log(obj);
        //     displayLandingPage(obj.data.username, obj.data.type);
        // }).fail(function (xhr, status, exception) {
        //     console.log(xhr.status);
        //     loginFormErrors.html("Invalid username and or password");
        //     loginFormErrors.css("display", "block");
        //
        // });
        //
        // event.preventDefault();
    });
    function displayLandingPage(username = "mwasswa", role = "admin") {
        location.replace("http://localhost/pickNgo/mainpage/main.php?" + "name=" + username + "&role=" + role);
    }
});