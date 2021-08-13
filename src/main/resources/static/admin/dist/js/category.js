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
  //joinList or goodsList
  var list = result.data.list;
  //goodsSale
  var gsList = result.data.gsList;
  var cloneUl = $("#nextPopUp").find("#categoryResultUl").clone().removeClass("categoryResultUl");
  for(var c = 0;c < list.length;c++){
    var el = cloneUl.find(".dumyLi").clone().removeClass("dumyLi");
    var se = $('<select/>',{class:"custom-select"}).css({"width": "20%",});
    var option = "";
    var temp = "";
    for(var i = 0; i< gsList.length; i++){
      option += '<option value=\"'+gsList[i].id+'\">' + gsList[i].name + '</option>'
      if(gsList[i].id == list[c].id && gsList[i].id != null){
        temp = gsList[i].id;
        //checkBox
        el.find(".check1").prop('checked',true);
      }
      se.html(option);
      if (temp != "") {
        se.val(temp);
      }
    }
    el.find("input:first-child").before(se);
    var link = el.find("a");
    link.text(list[c].categoryName);
    link.text(list[c].goodsName);
    var sd = el.find("#date3");
    var ed = el.find("#date4");
    var categoryId = el.find("#categoryId");
    var goodsId = el.find("#goodsId");
    var goodsCategoryId = el.find("#goodsCategoryId");
    sd.val(list[c].startDate);
    ed.val(list[c].endDate);
    categoryId.val(list[c].categoryId);
    goodsId.val(list[c].goodsId);
    goodsCategoryId.val(list[c].goodsCategoryId);
    //second popup
    el.find(".button2").attr('onclick','nextLevel(this,'+list[c].categoryId+')');
    // if(list[c].goodsCategoryId != null) {
    //   el.find(".button2").attr('onclick',
    //       'nextLevel(this,' + list[c].goodsCategoryId + ')');
    // }
    el.find(".check1").attr('onchange','checkBox(this)');
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

//check
function checkBox(thi){
  debugger;
  var flag = $(thi).is(':checked');
  var id = $(thi).parent().find(".custom-select").val();
  var goodsId = $(thi).parent().find("#goodsId").val();
  var startDate = $(thi).parent().find(".startDate").val();
  var endDate = $(thi).parent().find(".endDate").val();
  $("#campaignSetModal").find("#primaryGoodsIdValue").val(goodsId);
  $("#campaignSetModal").find("#startDate").val(startDate);
  $("#campaignSetModal").find("#endDate").val(endDate);
  if (id == 1){
    var goodsName = $(thi).parent().find('.categoryName').text();
    var goodsCategoryId = $(thi).parent().find("#goodsCategoryId").val();
    $("#campaignSetModal").find("#primaryGoodsId").val(goodsName);
    $("#campaignSetModal").find("#csCategoryId").val(goodsCategoryId);
    var data = {
      "goodsId": goodsId,
      "flag": flag,
      "goodsCategoryId": goodsCategoryId,
    };
    $.ajax({
      type: 'POST',//方法类型
      url: '/admin/giftGoods',
      contentType: 'application/json',
      data:JSON.stringify(data),
      success: function (result) {
        if (result.resultCode == 200) {
          debugger;
          if (!flag) {
            swal("ご削除出来ました！", {
              icon: "success",
            });
          } else {
            var goodsList = result.data;
            for(var i = 0; i< goodsList.length; i++) {
              $("#subGoodsId").append(
                  '<option value=\"' + goodsList[i].goodsId + '\">'
                  + goodsList[i].goodsName + '</option>');
            }
          }
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
    if (goodsId&&flag&&id==1){
      $("#campaignSetModal").modal('show');
    }
    return;
  }else {
    var flag = $(thi).is(':checked');
    var id = $(thi).parent().parent().find('.custom-select').val();
    var startDate = $(thi).parent().find('.startDate').val();
    var endDate = $(thi).parent().find('.endDate').val();
    var categoryId = $(thi).parent().find("#categoryId").val();
    var data = {
      "id": id,
      "categoryId": categoryId,
      "startDate": startDate,
      "endDate": endDate,
      "flag": flag,
    };
    $.ajax({
      type: 'POST',//方法类型
      url: "/admin/check/event",
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (result) {
        if (result.resultCode == 200) {
          if (flag) {
            swal(
                "ご登録ありがとうございます！", {
                  icon: "success",
                });
          } else {
            swal(
                "刪除成功！", {
                  icon: "success",
                });
          }
        } else {
          swal(result.message, {
            icon: "error",
          });
        }
        ;
      },
      error: function () {
        swal("期間外です!", {
          icon: "error",
        });
      }
    });
  }
}

//modal
// $("#modal-close").click(function () {
//   $(".modal").fadeOut();
// });

$("#saveSaleButton").click(function () {
  debugger;
  var primaryGoodsId = $("#primaryGoodsIdValue").val();
  var subGoodsId = $("#subGoodsId").val();
  var startDate = $("#startDate").val();
  var endDate = $("#endDate").val();
  var campaignId = 1;
  var categoryId = $("#csCategoryId").val();
  data = {
    "primaryGoodsId": primaryGoodsId,
    "subGoodsId": subGoodsId,
    "startDate":startDate,
    "endDate":endDate,
    "campaignId":campaignId,
    "categoryId":categoryId
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

