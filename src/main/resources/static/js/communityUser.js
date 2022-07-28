let userInfo = {
    init:function () {
        $("#btn-join").on("click",() =>{
           this.userJoin();
        });
    },

    userJoin:function () {
        let data = {
            username : $("#username").val(),
            email : $("#email").val(),
            password : $("#password").val(),
            confirmPassword : $("#confirmPassword").val()
        };

        console.log(data);

        const idCheck = /^[a-z0-9]+$/;
        if (!idCheck.test(data.username) || data.username.length < 6) {
            alert('아이디는 영소문자,숫자로 구성된 6글자 이상으로 조합하시오.');
            $("username").focus();
            return false;
        }

        if (data.password.length < 8) {
            alert('비밀번호는 8글자 이상입니다.')
            return false;
        }

        if(data.password !== data.confirmPassword){
            alert('비밀번호가 일치하지 않습니다');
            return false;
        }



        $.ajax({
            type: "POST",
            url: "/api/v1/user/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원가입이 완료되었습니다");
            location.href = "/";
        }).fail(function (error) {
            if (error.responseText === "중복된 아이디입니다") {
                alert("중복된 아이디입니다");
                return false;
            }
            if (error.responseText === "중복된 이메일입니다") {
                alert("중복된 이메일입니다");
                return false;
            }
            else
                console.log(JSON.stringify(error));
                alert("JSON.stringify_ERROR")
        });
    }
};

userInfo.init();