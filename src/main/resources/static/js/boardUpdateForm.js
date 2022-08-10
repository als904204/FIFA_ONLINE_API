const main = {
    init: function () {
        $("#btn-update-form").on("click", () => {
            this.formUpdate();
        });
    },

    formUpdate : function () {
        let data = {
            id : $("#board-update-id").val(),
            title : $("#form-title").val(),
            content: $("#form-content").val(),
            username : $("#board-author").val()
        }

        console.log(data.id);
        console.log(data.title);
        console.log(data.content);
        console.log(data.username);
        if (!data.title || data.title.trim() === '') {
            alert("빈 제목입니다");
            return false;
        } else {
            $.ajax({
                type: `PUT`,
                url: `/api/v1/board/${data.id}`,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data), // data 를 json 형태로 윗 url 로 보냄
                dataType: `json`
            }).done(function (res) {
                alert("수정이 완료되었습니다");
                location.href="/board/boardList"
            }).fail(function (error) {
                console.log(error);
                alert(JSON.stringify(error))
            });
        }
    }

}

main.init();