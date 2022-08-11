const vote = {
    init : function () {
        $('#vote-up-btn').on('click', () =>{
            this.vote_up();
        }),

        $('#vote-down-btn').on('click', () => {
            this.vote_down();
        });
    },

    vote_up : function () {
        const id = $('#boardID').val();
        $.ajax({
            type: `POST`,
            url: `/api/v1/board/${id}/voteUp`,
        }).done(function () {
            alert('추천완료');
            $('#vote-up-number').load(location.href+' #vote-up-number');
        }).fail(function (){
            alert("이미 추천한 게시글 입니다");
        })
    },

    vote_down : function () {
        const id = $('#boardID').val();
        $.ajax({
            type: `POST`,
            url: `/api/v1/board/${id}/voteDown`,
        }).done(function () {
            alert('반대');
            $('#vote-down-number').load(location.href+' #vote-down-number');
        }).fail(function (){
            alert("이미 반대한 게시글 입니다");
        })
    }
};

vote.init();