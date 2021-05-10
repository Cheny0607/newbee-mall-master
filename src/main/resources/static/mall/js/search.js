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

//検索履歴
$("#keyword").focus(function(){
    var keyword=$("#keyword").val();
    if(keyword !=""){
        $("#keyword").trigger("keyup")
;    }
   $.ajax({
       type:'POST',
       url:'/searchHistory/getSearchHistory',
       contentType:'application/json',
       success:function (result){
           if (result.resultCode == 200){
               showResult(result);
           }else{
               swal(result.message,{
                   icon:"error",
               });
           };
       },
    error:function(){
       swal("操作失敗",{
           icon:"error",
       });
    }
   });
});

function showResult(result){
    var list = result.data;
    var _href = "/goods/detail/";
    for(var i = 0; i < list.length;i++){
        var el = $(".dumyLi").clone().removeClass("dumyLi");
        var link = el.find("a");
        link.text(list[i].goodsName);
        link.attr("href",_href + list[i].goodsId);
        $(".dumyLi").before(el);
    }
    $("#searchResultUl").show();
    addendToSearchBar($("#searchResultUl"));
}

function showResultForLikeSearch(result){
    var list = result.data.list;
    var _href = "/goods/detail/";
    for(var i = 0; i < list.length;i++){
        var el = $(".dumyLi").clone().removeClass("dumyLi");
        var link = el.find("a");
        link.text(list[i].goodsName);
        link.attr("href",_href + list[i].goodsId);
        $(".dumyLi").before(el);
    }
    $("#searchResultUl").show();
    addendToSearchBar($("#searchResultUl"));
}

function addendToSearchBar(el){
    var searchBar = $("#keyword");//jquery
    //var searchBar = document.getElementById("keyword");//dom
    var rect = searchBar[0].getBoundingClientRect();//convert jquery object to dom by searchBar[0]
    console.log(rect.top,rect.right,rect.bottom,rect.left);
    var sbHeight = searchBar.height();
    el.css({top:rect.top + sbHeight,left:rect.left,position:'absolute'});
}

$("#keyword").keyup(function(){
    debugger;
    var keyword = $("#keyword").val();
    $.ajax({
        type:"get",
        url:"/goods/search?goodsName="+keyword,
        //data: keyword,  // JSONデータ本体
        //contentType: 'application/json', // リクエストの Content-Type
        dataType:"json",
        success:function(json_data){
            debugger;
            clearResultList();
            showResultForLikeSearch(json_data);
        },
        error:function(){
            debugger;
            alert("Server Error. Please try again later.");
        }
    });
});

function clearResultList(){
    //clear #searchResultUl's elements
    //foreach is javascript's method
    //$("#searchResultUl").children() is jquery
    //toArray() convert $("#searchResultUl").children() to javascript array
    $("#searchResultUl").children().toArray().forEach(function(value,index,array){
        // check if include class name which is dumyLi
        // value is dom html element
        var incFlag = $(value).attr("class").includes("dumyLi");
        // delete elements besides dumyLi
        if(!incFlag){
            $(value).remove();
        }
    })
}