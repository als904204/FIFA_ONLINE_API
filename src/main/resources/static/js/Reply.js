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
        const  boardID = $("#boardID").val()

        if (data.replyContent === '') {
            alert("빈 내용입니다");
        } else{
            $.ajax({
                type: `POST`,
                url: `/api/v1/reply/create/${boardID}`,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"

            }).done(function () {
                window.location.href = `/board/boardDetail/${boardID}`;
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
        }

    }
};
reply.init();