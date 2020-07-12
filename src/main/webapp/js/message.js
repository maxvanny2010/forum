function prepareUpdate(input) {
    let id = input.id.split('_');
    let idPost = id[0];
    let idMsg = id[1];
    let authorMsg = id[2];
    let authorPost = id[3];
    let message = $('#DescU_' + input.id).val();
    $('#comment').html(`
      <form name="comm" id="comm" action="/post/message/update" method="POST">
                            <input type="hidden" name="idMsgPostUpdate" value="${idPost}">
                            <input type="hidden" name="idMsgUpdate" value="${idMsg}">
                            <input type="hidden" name="authorPost" value="${authorPost}">
                            <input type="hidden" name="authorMsg" value="${authorMsg}">
                                <div class="form-group">
                                    <label class="sr-only" for="msgUpdate">message</label>
                                    <textarea name="msgUpdate" id="msgUpdate"
                                              style="width: 300px; height: 200px; font-size: medium">${message}</textarea>
                                </div>
                                <div class="form-group">
                                    <input class="btn"
                                           style="color: white; background-color:dodgerblue; border-radius: 0; font-weight: bold; width: 300px;"
                                           type="submit" form="comm"
                                           value="обновить коммент"/>
                                </div>
                        </form>
    
    
    `);
}

function preventDoubleMsg(input) {
    let $addMsg = $('#' + input.id);
    setTimeout(function () {
        $addMsg.attr('disabled', 'disabled');
    }, 1000);
    setTimeout(function () {
        $addMsg.attr('disabled', false);
    }, 1200);

}
