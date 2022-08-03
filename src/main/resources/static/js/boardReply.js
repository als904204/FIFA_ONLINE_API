const main ={
    init : function () {
        $("#btn-reply-save").on("click",() =>{
            this.replySave();
        });
    },
    replySave : function () {
        let data = {
            id : $("#boardID").val(),
            content : $("#reply-content").val()
        };

        $.ajax({
            type: `POST`,
            url: `/api/v1/board/${data.id}/reply`,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: `json`
        }).done(function (res) {
            alert("댓글을 등록하셨습니다");
            document.location.reload();
        });
    }
}

main.init();