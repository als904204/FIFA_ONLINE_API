let userInfo = {
    init:function () {
        $("#btn-join").on("click",() =>{
           this.userJoin();
        });
    },

    userJoin:function () {
        let data = {
            nickname : $("#nickname").val(),
            email : $("#email").val(),
            password : $("#password").val(),
            confirmPassword : $("#confirmPassword").val()
        };

        console.log(data);

        const idCheck = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
        if (!idCheck.test(data.nickname)) {
            alert('닉네임은 한글 및 영어만 입력 가능합니다.');
            $("nickname").focus();
            return false;
        }
        if(data.nickname.length < 2){
            alert('닉네임은 2글자 또는 2글자 이상이어야 합니다.');
            $("nickname").focus();
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
            if (error.responseText === "중복된 닉네임입니다") {
                alert("중복된 닉네임입니다");
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