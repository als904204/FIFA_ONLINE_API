const main = {
    init : function () {
        $("#btn-save-form").on("click",() =>{
            this.formSave();
        });
    },
    formSave : function () {
        let data = {
            title : $("#form-title").val(),
            content: $("#form-content").val()
        };

        if (!data.title || data.title.trim() === '') {
            alert("빈 제목입니다");
            return false;
        } else {
            $.ajax({
                type: `POST`,
                url: `/api/v1/board/create`,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data), // data 를 json 형태로 윗 url 로 보냄
                dataType: `json`
            }).done(function (res) {
                alert("글쓰기가 완료되었습니다");
                location.href="/board/boardList"
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
        }

    }
}

main.init();