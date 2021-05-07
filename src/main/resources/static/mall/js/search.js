$(function () {
    $('#keyword').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if (q && q != '') {
                window.location.href = '/search?keyword=' + q;
            }
        }
    });
});

function search() {
    var q = $('#keyword').val();
    if (q && q != '') {
        window.location.href = '/search?keyword=' + q;
    }
}

$("#keyword").focus(function(){
   console.log("focused");
});

//ajax 曖昧検索
$("#keyword").keyup(function(){
    console.log("Handler for .keyup() called.");
});

function showResult(result){
    var list = result.data;
    //link=/goods/detail/goodsId/
    var _href = "/goods/detail/";
    for(var i = 0; i < list.size();i++){
        var link = $(".dumyli").clone().removeClass("dumyli").find("a");
        link.text(list[i].goodsName);
        link.attr("href",_href + list[i].goodsId);
    }
    $("#searchResult")
}