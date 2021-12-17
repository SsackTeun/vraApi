function loginBtn(){
    login()
}

function login(){
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let domain = 'sddc.local';
        //document.getElementById().value;
    let scope = 'string';
        //document.getElementById().value;

    fetch('/enhancedLogin', {
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
    }).then(res => console.log(res))

    /*
    alert("[username] : " + username +"\n"
    + "[password] : " + password + "\n"
    + "[domain] : " + domain + "\n"
    + "[scope] : " + scope);
     */
}