const vote = {
    init : function () {
        $('#vote-up-btn').on('click', () =>{
            this.vote_up();
        });
    },

    vote_up : function () {

        const id = $('#boardID').val();

        $.ajax({
            type: `POST`,
            url: `/api/v1/board/${id}/vote`,
        }).done(function () {
            alert('추천');
            $('#vote-up-number').load(location.href+' #vote-up-number');
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};

vote.init();