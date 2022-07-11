var main = {
    init: function () {
        var _this = this;
        $('#btn-user-info').on('click', function () {
            _this.search_user_info();
        });
        $('#nickname').on('keyup', function (e) {
            if(e.key==='Enter'||e.keyCode===13){
                _this.search_user_info();
            }
        })
    },
    search_user_info : function () {
        const nickname = $('#nickname').val();

        if (nickname === '') {
            alert('구단주명을 입력해주세요');
        }
        $.ajax({
            type: 'GET',
            url: '/api/v1/user/'+nickname,
            dataType: 'text',
            contentType: 'application/json; charset=utf-8'
        }).done(function (id) {
            window.location.href = '/user/info/' + nickname;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }


};

main.init();