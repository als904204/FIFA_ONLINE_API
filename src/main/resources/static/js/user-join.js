let index = {
    init: function () {

        $("#btn-join").on("click",() => {
           this.save();
        });
    },
    save : function (){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        console.log(data)
        $.ajax({
            type: "POST",
            url: "/api/user/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            console.log(res);
            alert("회원수정이 완료되었습니다");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
index.init();