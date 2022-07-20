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
        };

        console.log(data);

        $.ajax({
            type: "POST",
            url: "/api/v1/user/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            console.log(res);
            alert("성공");
            location.href = "/";
        }).fail(function (error) {
            alert("실패");
            console.log(JSON.stringify(error));
        });
    }
};

userInfo.init();