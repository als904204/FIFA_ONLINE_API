const board = {
    init : function () {
        $('#btn-board-delete').on('click', () =>{
            this.deleteById();
        });
    },
    deleteById : function () {

        let id = $("#boardID").val();

        if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                type: 'DELETE',
                url: `/api/v1/board/${id}`,
            }).done(function () {
                alert('삭제가 완료되었습니다');
                window.history.back();
            }).fail(function (error){
                alert(JSON.stringify(error));
            })
        } else {
            return false;
        }
     }
};

board.init();