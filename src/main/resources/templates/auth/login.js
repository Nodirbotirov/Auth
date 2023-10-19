function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    console.log(username, password);
    if (username && password) {
        let data = new FormData();
        data.append("username", username)
        data.append("password", password)
        fetch("/api/auth/login", {
            method: "post",
            body: data
        })
            .then(res => res.json())
            .then(resp => {
                localStorage.setItem("token", resp?.data?.token);
                window.location.href = '/admin/news'
            })
            .catch(err => {
                document.getElementById("alertMessage").innerText = "Login yoki parol xato!";
                document.getElementById("alertContainer").style.display = "block"
                setTimeout(() => {
                    document.getElementById("alertContainer").style.display = "none"
                }, 5000)
                localStorage.removeItem("token")
            })
    } else {
        document.getElementById("alertMessage").innerText = "Iltimos login va parol maydonini to'ldiring!";
        document.getElementById("alertContainer").style.display = "block"
        setTimeout(() => {
            document.getElementById("alertContainer").style.display = "none"
        }, 5000)
    }


}