const boardSaveForm = {
    init: function () {
        $("#btn-save-board").on("click",() =>{
            this.boardSave();
        });
    },

    boardSave : function () {
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        if (data.title === '') {
            alert("제목을 입력해주세요");
        }
        $.ajax({
            type : "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res){
            alert("글쓰기가 완료되었습니다");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};


boardSaveForm.init();