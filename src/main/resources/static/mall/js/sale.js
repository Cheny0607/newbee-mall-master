$(function(){
  //disable previous page
  debugger;
  $(".previousPage").css("pointer-events","none").css("color","#009e96");
})
$("#campaignTable-select-sort").change(function (){
  paging(2);
});
//下一页
$( ".nextPage" ).click(function(){
  paging(0);
  $(".previousPage").css("pointer-events","auto").css("color","#009e96");
});
//上一页
$( ".previousPage" ).click(function(){
  paging(1);
});

function paging(num) {
  var page = $("#currentPageNo").text();
  /*console.log("selected value",$('#zv-cqa-select-sort :selected').text());*/
  var pageNo = 0;
  console.log("current page:", page);
  var url = "/goods/sale";
  if (num == 0) {
    //下页
    pageNo = parseInt(page) + 1;
  } else if (num == 1) {
    //上页
    pageNo = parseInt(page) - 1;
  } else {
    pageNo = 1;
  }
  var data = {
    "page": pageNo
  };
  // console.log("data".data);
  $.ajax({
    type: 'POST',//方法类型
    url: "/goods/sale",
    contentType: 'application/json',
    data: JSON.stringify(data),
    success: function (result) {
      //サーバーが成功した場合
      if (result.resultCode == 200) {
        var el;
        if (result.data.list.length > 0) {
          $("#campaignTable").find(".delete").remove();
        }
        var ar = result.data.list;
        /* if(ar.length>0)*/
        for (let i = 0; i < ar.length; i++) {
          el = $($(".hidden")[0]).clone().removeClass("hidden");
          el.find(".campaignTable-saleId").html(result.data.list[i].id);
          el.find(".campaignTable-saleName").html(result.data.list[i].name);
          el.find(".campaignTable-saleDate").html(result.data.list[i].startDate);
          el.find(".campaignTable-endDate").html(result.data.list[i].endDate);

          $("#detailFooter").before(el);
          /*qa.appendTo("#ZVCQuestionArea");*/
        }
      } else {
        swal(result.message, {
          icon: "error",
        });
      };
    },
    error: function () {
      swal("操作失败", {
        icon: "error",
      });
    }
  })
}