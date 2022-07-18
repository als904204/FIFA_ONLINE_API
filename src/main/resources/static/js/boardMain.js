const main = {
    init : function () {
        var _this = this;
        $('#btn-delete').on('click',function () {
            _this.delete();
        });
    },
    delete : function () {
        $.ajax({
            type: 'DELETE',
            url : '/board/boardDelete',
        }).done(function () {
            alert('모든 글 삭제');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();