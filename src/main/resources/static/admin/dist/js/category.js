// var MouseOnSearchResultUl
function nextLevel(thi,categoryId){
  debugger;
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

function showResult(thi,result){
  //joinList
  var subCategoryList = result.data.subCategoryList;
  //goodsSale
  var gsList = result.data.gsList;
  var cloneUl = $("#nextPopUp").find("#categoryResultUl").clone().removeClass("categoryResultUl");
  for(var c = 0;c < subCategoryList.length;c++){
    var el = cloneUl.find(".dumyLi").clone().removeClass("dumyLi");
    var se = $('<select/>');
    var option = "";
    for(var i = 0; i< gsList.length; i++){
      option += '<option value=\"'+gsList[i].id+'\">' + gsList[i].name + '</option>'
      if(gsList[i].id == subCategoryList[c].id && gsList[i].id != null){
        se.val(gsList[i].id);
        //checkBox
        el.find("#check2").prop('checked',true);
      }
    }
    se.html(option);
    el.find("input:first-child").before(se);
    var link = el.find("a");
    link.text(subCategoryList[c].categoryName);
    var sd = el.find("input:nth-child(5)");
    var ed = el.find("input:nth-child(7)");
    sd.val(subCategoryList[c].startDate);
    ed.val(subCategoryList[c].endDate);
    //second popup
    el.find(".button2").attr('onclick','nextLevel(this,'+subCategoryList[c].categoryId+')');
    debugger;
    cloneUl.find(".dumyLi").before(el);
    //close
    cloneUl.find("#popUpClose").click(function() {
      cloneUl.find("#popup").remove();
    });
  }
  cloneUl.show();
  var rect = thi.getBoundingClientRect();//转换成dom加[0]  convert jquery object to dom by searchBar[0]
  console.log(rect.top,rect.right,rect.bottom,rect.left);
  cloneUl.css({top: rect.top,left: rect.right,position:'fixed'});//相对定位relative  绝对定位absolute
  $("#main").append(cloneUl);
}

//checkBox
$(".check1").change(function() {
  debugger;
  var flag = $(this).is(':checked');
  var id = $(this).parent().parent().find('.custom-select').val();
  var startDate = $(this).parent().find('.startDate').val();
  var endDate = $(this).parent().find('.endDate').val();
  var categoryId = $(this).val();
  var data = {
    "id": id,
    "categoryId": categoryId,
    "startDate": startDate,
    "endDate": endDate,
    "flag":flag,
  };
  $.ajax({
    type: 'POST',//方法类型
    url: "/admin/check/invent",
    contentType: 'application/json',
    data: JSON.stringify(data),
    success: function (result) {
      if (result.resultCode == 200) {
        if(flag) {
          swal(
              "ご登録ありがとうございます！", {
                icon: "success",
              });
        }else {
          swal(
              "刪除成功！", {
                icon: "success",
              });
        }
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
})

// $(".check1").change(function() {
//   debugger;
//   var ischecked = $(this).is(':checked');
//   if (!ischecked) {
//     var categoryId = $(this).val();
//   var url = "/admin/campaign/delete";
//   var swlMessage = '刪除成功';
//   $.ajax({
//     type: 'POST',//方法类型
//     url: url,
//     contentType: 'application/json',
//     data: JSON.stringify(categoryId),
//     success: function (result) {
//       if (result.resultCode == 200) {
//         swal({
//           title: swlMessage,
//           type: 'success',
//           showCancelButton: false,
//           confirmButtonColor: '#1baeae',
//           confirmButtonText: '確定',
//           confirmButtonClass: 'btn btn-success',
//           buttonsStyling: false
//         })
//       } else {
//         swal(result.message, {
//           icon: "error",
//         });
//       };
//     },
//     error: function () {
//       swal("操作失败", {
//         icon: "error",
//       });
//     }
//   });
//   } else {
//     debugger;
//     var id = $(this).parent().parent().find('.custom-select').val();
//     var startDate = $(this).parent().find('.startDate').val();
//     var endDate = $(this).parent().find('.endDate').val();
//     var categoryId = $(this).val();
//     var data = {
//       "id": id,
//       "categoryId": categoryId,
//       "startDate": startDate,
//       "endDate": endDate,
//     }
//     var url = "/admin/campaign/save";
//     $.ajax({
//       type: 'POST',//方法类型
//       url: url,
//       contentType: 'application/json',
//       data: JSON.stringify(data),
//       success: function (result) {
//         if (result.resultCode == 200) {
//           swal(
//             "ご登録ありがとうございます！",{
//               icon:"success",
//           });
//         } else {
//           swal(result.message, {
//             icon: "error",
//           });
//         };
//       },
//       error: function () {
//         swal("期間外です!", {
//           icon: "error",
//         });
//       }
//     });
//   }
// });

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

