const reply = {
    init : function () {
        $("#btn-reply").on("click",() => {
            this.replySave();
        });
    },

    replySave : function () {

        let  data = {
            replyContent : $("#reply-content").val(),
        };
        let  boardID = $("#boardID").val()

        alert(data.replyContent)
        alert(boardID)

        if (data.replyContent === '') {
            alert("빈 내용입니다");
        }
            $.ajax({
                type: `POST`,
                url: `/api/v1/reply/create/${boardID}`,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"

            }).done(function (boardID) {
                window.location.href = `/board/boardDetail/${boardID}`;
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
    }
};
reply.init();