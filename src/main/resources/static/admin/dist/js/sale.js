/*
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
  /!*console.log("selected value",$('#zv-cqa-select-sort :selected').text());*!/
  var pageNo = 0;
  console.log("current page:", page);
  var url = "/goods";
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
    url: "/goods",
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
        /!* if(ar.length>0)*!/
        for (let i = 0; i < ar.length; i++) {
          el = $($(".hidden")[0]).clone().removeClass("hidden");
          el.find(".campaignTable-saleId").html(result.data.list[i].id);
          el.find(".campaignTable-saleName").html(result.data.list[i].name);
          el.find(".campaignTable-saleDate").html(result.data.list[i].startDate);
          el.find(".campaignTable-endDate").html(result.data.list[i].endDate);

          $("#detailFooter").before(el);
          /!*qa.appendTo("#ZVCQuestionArea");*!/
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
}*/

$('#download').on('click',function(){
  debugger;
  var _data = [1,2,3]
  $.ajax({
    type:'POST',//方法类型
    url:"/admin/download/file",
    contentType: 'application/json',
    data:JSON.stringify(_data),
    success:function(result){
      debugger;
      //サーバーが成功の場合ここが呼ばれる
      if (result.resultCode == 200){
        /*this.windows.href = result.data;
        data.url = "/Users/chennaiyuan/Desktop/upload/test.csv"; */  //path
        Download(result.data);
      } else {
        swal(result.message,{
          icon:"error",
        });
      };
    },
    //エラーの場合
    error:function (){
      swal("操作失敗",{
        icon:"error",
      });
    }
  });
});
function Download(url){
  debugger;
  document.getElementById('my_iframe').src = url;
}

debugger;
    new AjaxUpload('#uploadSale', {
      action: '/admin/uploadTest/file',
      name: 'file',
      autoSubmit: true,
      responseType: "json",
      onSubmit: function (file, extension) {
        if (!(extension && /^(jpg|jpeg|png|gif|csv)$/.test(extension.toLowerCase()))) {
          alert('只支持jpg、png、gif、csv格式的文件！');
          return false;
        }
      },
      onComplete: function (file, r) {
        if (r != null && r.resultCode == 200) {
          $("#goodsCoverImg").attr("src", r.data);
          $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
          return false;
        } else {
          alert("error");
        }
      }
    });