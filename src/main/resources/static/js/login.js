function loginBtn(){
    login()
}

function loginCheck(){
    loginCheck()
}

function loginCheck(){
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let domain = 'sddc.local';
    //document.getElementById().value;
    let scope = 'string';
    //document.getElementById().value;

    fetch('/loginCheck', {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            'token' : token
        }),
    }).
    then(resp => resp.json())
}

function login(){
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let domain = 'sddc.local';
        //document.getElementById().value;
    let scope = 'string';
        //document.getElementById().value;

    fetch('/login', {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            username : username,
            password : password,
            domain : domain,
            scope : scope
        }),
    }).
    then(resp => resp.json())
        .then(data => {
            if(data.link != null){
                location.href = data.link;
            }
            else{
                alert("로그인 실패");
            }
        })
    /*
    alert("[username] : " + username +"\n"
    + "[password] : " + password + "\n"
    + "[domain] : " + domain + "\n"
    + "[scope] : " + scope);
     */
}