const reply = {
    init : function () {
        $("#btn-reply").on("click",() => {
            this.replySave();
        });
    },

    replySave : function () {

        let  data = {
            boardID : $("#boardID").val(),
            content : $("#reply-content").val()
        };

        if (!data.content || data.content.trim() === '') {
            alert("빈 내용입니다");
            return false;
        } else{
            $.ajax({
                type: `POST`,
                url: `/api/v1/reply/create/${data.boardID}`,
                dataType: 'JSON',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
        }
    }
};
reply.init();