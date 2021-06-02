var MouseOnSearchResultUl  //全局变量
//ajax与后台通信，查找查询履历
$( "#mainCategoryBtn" ).focus(function(){
  var keyword = $( "#mainCategoryBtn" ).val();
  if(keyword != ""){
    $( "#mainCategoryBtn" ).trigger("keyup");
  }
  $.ajax({
    type: 'POST',//方法类型
    url: '/searchHistory/getSearchHistory',
    contentType: 'application/json',
    success: function (result) {
      if (result.resultCode == 200) {
        debugger;
        showResult(result);
      } else {
        swal(result.message, {
          icon: "error",
        });
      }

    },
    error: function () {
      swal("操作失败", {
        icon: "error",
      });
    }
  })
});
//鼠标移开时候删除elements的内容d
$("#mainCategoryBtn").focusout(function(){
  if(MouseOnSearchResultUl)
    return;
  clearResultList()
  $("#categoryResultUl").hide();
})
//ajax あいまい検索
$("#mainCategoryBtn").keyup(function(){
  debugger;
  var keyword = $("#mainCategoryBtn").val();
  $.ajax({
    type: 'get',//方法类型  //method = "POST"
    url: "/goods/search?goodsName="+keyword,  //Post送信先のurl
    dataType:"json",
    success: function (json_data) {
      debugger;
      clearResultList();
      showResultForLikeSearch(json_data);
      debugger;
      var list = json_data.data.list[0];
      var str = list.goodsName;
      var arr = str.split(" ");
      // arr.filter(keyword => keyword.includes(keyword));
      for (var i=0;i<arr.length;i++){
        if(arr[i].includes(keyword)){
          keyword = arr[i];
        }
      }
      /*  keywordInsert(keyword);*/
    },
    error: function() {
      debugger;
      alert("Service Error. Pleasy try again later.");
    }
  });

});

function clearResultList(){
  $("#categoryResultUl").children().toArray().forEach(function(value,index,array){
    var incFlag = $(value).attr('class').includes("dumyLi");
    if(!incFlag){
      $(value).remove();
    }
  })
}

function showResult(result){
  var list = result.data;
  var _href = "/goods/detail/";
  for(var i = 0; i< list.length; i++){
    var el = $(".dumyLi").clone().removeClass("dumyLi");
    var link = el.find("a");
    link.text(list[i].goodsName);
    link.attr("href", _href + list[i].goodsId);
    $(".dumyLi").before(el);
  }
  $("#categoryResultUl").show();
  appendToSearchBar($("#categoryResultUl"));
}


function appendToSearchBar(el){
  debugger;
  var searchBar = $("#mainCategoryBtn");//jquery object
  //var searchBar = document.getElementById("downBox");//dom
  var rect = searchBar[0].getBoundingClientRect();//转换成dom加[0]  convert jquery object to dom by searchBar[0]
  console.log(rect.top,rect.right,rect.bottom,rect.left);
  //var sbHeight = searchBar.height();
  //el.height(rect.top + sbHeight)
  //el.left(rect.left);
  el.css({top: rect.top,left: rect.right,position:'fixed'});//相对定位relative  绝对定位absolute
}
$("#categoryResultUl").mousemove(function(){
  MouseOnSearchResultUl = true;
});
$("#categoryResultUl").mouseleave(function(){
  MouseOnSearchResultUl = false;
})
//insert
function keywordInsert(keyword){
  debugger;
  var keyword = $("#mainCategoryBtn").val();
  data = {
    "keyword":keyword,
    /* "id":id*/
  };
  $.ajax({
    type: 'POST',//方法类型
    url: '/goods/insertKeyword',
    contentType: 'application/json',
    data: JSON.stringify(data),//data:keyword变量
    success: function (result) {
      //サーバーが成功した場合
      if (result.resultCode == 200) {
        debugger;

      } else {
        swal(result.message, {
          icon: "error",
        });
      }

    },
    error: function () {
      swal("操作失败", {
        icon: "error",
      });
    }
  })
}

$(".check1").change(function() {
  debugger;
  var ischecked = $(this).is(':checked');
  if (!ischecked) {
    var categoryId = $(this).val();
  var url = "/admin/campaign/delete";
  var swlMessage = '刪除成功';
  $.ajax({
    type: 'POST',//方法类型
    url: url,
    contentType: 'application/json',
    data: JSON.stringify(categoryId),
    success: function (result) {
      if (result.resultCode == 200) {
        swal({
          title: swlMessage,
          type: 'success',
          showCancelButton: false,
          confirmButtonColor: '#1baeae',
          confirmButtonText: '確定',
          confirmButtonClass: 'btn btn-success',
          buttonsStyling: false
        })
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
  });
  } else {
    debugger;
    var ischecked = $(this).is(':checked');
    var id = $(this).parent().parent().find('.custom-select').val();
    ;
    var startDate = $(this).parent().find('.startDate').val();
    var endDate = $(this).parent().find('.endDate').val();
    if (!ischecked) {
      var categoryId = $(this).val();
    }
    if (ischecked) {
      var categoryId = $(this).val();
    }
    var data = {
      "id": id,
      "categoryId": categoryId,
      "startDate": startDate,
      "endDate": endDate,
    }
    var url = "/admin/campaign/save";
    var swlMessage = '插入成功';
    // var url = "/admin/campaign/delete";
    // var swlMessage = '刪除成功';
    $.ajax({
      type: 'POST',//方法类型
      url: url,
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (result) {
        if (result.resultCode == 200) {
          swal({
            title: swlMessage,
            type: 'success',
            showCancelButton: false,
            confirmButtonColor: '#1baeae',
            confirmButtonText: '確定',
            confirmButtonClass: 'btn btn-success',
            buttonsStyling: false
          })
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
    });
  }
});

//modal
//added by c 2021/5/24 insertSale
$(function () {
  $("#modal-open").click(function () {
    $(".modal").fadeIn();
  });
  $("#modal-close").click(function () {
    $(".modal").fadeOut();
  });
});

$("#saveSaleButton").click(function () {
  debugger;
  var primaryGoodsId = $("#primaryGoodsId").val();
  var subGoodsId = $("#subGoodsId").val();
  data = {
    "primaryGoodsId": primaryGoodsId,
    "subGoodsId": subGoodsId,
  };
  $.ajax({
    type: 'POST',//方法类型
    url: "/admin/campaignSet/insert",
    contentType: 'application/json',
    data: JSON.stringify(data),
    success: function (result) {
      if (result.resultCode == 200) {
        debugger;
        swal("保存成功", {
          icon: "success",
        });
      } else {
        swal(result.message, {
          icon: "error",
        });
      }
      ;
    },
    error: function () {
      swal("操作失败", {
        icon: "error",
      });
    }
  })
});
