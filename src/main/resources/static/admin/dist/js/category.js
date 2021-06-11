var MouseOnSearchResultUl  //全局变量
function nextLevel(thi,categoryId){
  debugger;
  // var categoryId = $(this).parent().find("#mainCategoryBtn").val();
  $.ajax({
    type: 'POST',//方法类型
    url: '/admin/popUp/page',
    contentType: 'application/json',
    data:JSON.stringify(categoryId),
    success: function (result) {
      if (result.resultCode == 200) {
        debugger;
        showResult(thi,result);
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
};
//鼠标移开时候删除elements的内容d
$(".button1").focusout(function(){
  if(MouseOnSearchResultUl)
    return;
  // clearResultList()
  $("#categoryResultUl").hide();
})

function showResult(thi,result){
  var gsList = result.data.gsList;
  var subCategoryList = result.data.subCategoryList;
  var option = "";
  var cloneUl = $("#categoryResultUl").clone();
  // var nextLevel = $(this).parent().find("#subCategoryBtn").val();
  for(var i = 0; i< gsList.length; i++){
    var se = $('<select/>');
    option += '<option value=\"'+gsList[i].id+'\">' + gsList[i].campaign + '</option>'
    se.html(option);
    var el = $(".dumyLi").clone().removeClass("dumyLi");
    for(var c = 0;c < subCategoryList.length;c++){
      if(gsList[i].id == subCategoryList[c].id && subCategoryList[c].id != null){
        se.val(subCategoryList[c].id);
      }
    }
    $('.start').val(subCategoryList[i].startDate);
    $('.end').val(subCategoryList[i].endDate);
    el.find("input:first-child").before(se);
    if (subCategoryList[i].id !=null){
      el.find("#check2").prop('checked',true);
    }
    var link = el.find("a");
    link.text(subCategoryList[i].categoryName);
    cloneUl.find(thi).attr('onClick','nextLevel('+thi+','+subCategoryList[i].categoryId+');');
    cloneUl.find(".dumyLi").before(el);
  }
  cloneUl.show();
  //appendToSearchBar(thi,cloneUl);
  var rect = thi.getBoundingClientRect();//转换成dom加[0]  convert jquery object to dom by searchBar[0]
  console.log(rect.top,rect.right,rect.bottom,rect.left);
  cloneUl.css({top: rect.top,left: rect.right,position:'absolute'});//相对定位relative  绝对定位absolute
  $("#main").append(cloneUl);
  // appendToSearchBar(thi,cloneUl);
}
// function appendToSearchBar(thi,el){
//   debugger;
//   // var searchBar = $(".button1");//jquery object
//   //var searchBar = document.getElementById("downBox");//dom
//   var rect = thi.getBoundingClientRect();
//   console.log(rect.top,rect.right,rect.bottom,rect.left);
//   el.css({top: rect.top,left: rect.right,position:'fixed'});//相对定位relative  绝对定位absolute
// }
// function clearResultList() {
//   $("#categoryResultUl").children().toArray().forEach(
//       function (value, index, array) {
//         // check if include class name which is dumyLi
//         // value is dom html element
//         var incFlag = $(value).attr("class").includes("saleDumyLi");
//         // delete elements besides dumyLi
//         if (!incFlag) {
//           $(value).remove();
//         }
//       })
// }
$("#categoryResultUl").mousemove(function(){
  MouseOnSearchResultUl = true;
});
$("#categoryResultUl").mouseleave(function(){
  MouseOnSearchResultUl = false;
})

//checkBox
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
    var id = $(this).parent().parent().find('.custom-select').val();
    var startDate = $(this).parent().find('.startDate').val();
    var endDate = $(this).parent().find('.endDate').val();
    var categoryId = $(this).val();
    var data = {
      "id": id,
      "categoryId": categoryId,
      "startDate": startDate,
      "endDate": endDate,
    }
    var url = "/admin/campaign/save";
    $.ajax({
      type: 'POST',//方法类型
      url: url,
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (result) {
        if (result.resultCode == 200) {
          swal(
            "ご登録ありがとうございます！",{
              icon:"success",
          });
        } else {
          swal(result.message, {
            icon: "error",
          });
        };
      },
      error: function () {
        swal("期間外です!", {
          icon: "error",
        });
      }
    });
  }
});

//modal
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

