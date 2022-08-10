const comment = {
    init : function () {
        $('.btn-comment-delete').on('click', () =>{
            this.commentDeleteById();
        });
    },
    commentDeleteById : function () {

        let id = $("#commentID").val();
        console.log(id);

        if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                type : `DELETE`,
                url: `/api/v1/board/${id}/reply`,
            }).done(function () {
                alert('삭제가 완료되었습니다');
                location.reload();
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
        }
    }
};

comment.init();