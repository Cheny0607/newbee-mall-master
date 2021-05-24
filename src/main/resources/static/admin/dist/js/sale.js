//download
$('#download').on('click',function(){
  debugger;
  var ids = [];
  $('input:checkbox:checked').parent().next().map(function (){
    ids.push($(this).text())
    return ids;
  })
  if(ids == null){
    swal("请选择一条记录",{
      icon:"warning",
    });
    return
  }
  $.ajax({
    type:'POST',//方法类型
    url:"/admin/download/file",
    contentType: 'application/json',
    data:JSON.stringify(ids),
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

//upload
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

//search keyword
$("#searchForCampaign").keyup(function(){
  debugger;
  var keyword = $("#searchForCampaign").val();
  $.ajax({
    type:"get",    //method="POST"
    url:"/sale/search?name="+keyword, //post url
    // url:"/goods/sale", //post url
    //data: keyword,  // JSONデータ本体
    //contentType: 'application/json', // リクエストの Content-Type
    dataType:"json",
    success:function(json_data){
      debugger;
      clearResultList();
      showResultForLikeSearch(json_data);
      debugger;
      var list = json_data.data.list[0];
      var str = list.name;
      var arr = str.split(" ");
      arr.filter(keyword => keyword.includes(keyword));
      // keywordInsert(keyword);
    },
    error:function(){
      debugger;
      alert("Server Error. Please try again later.");
    }
  });
});

function showResultForLikeSearch(result){
  var list = result.data.list;
  // var _href = "/admin/goods/sale?keyword=";
  for(var i = 0; i < list.length;i++){
    var el = $(".saleDumyLi").clone().removeClass("saleDumyLi");
    var link = el.find("a");
    link.text(list[i].name);
    // link.attr("href",_href + list[i].id);
    $(".saleDumyLi").before(el);
  }
  $("#saleSearchResultUl").show();
  addendToSearchBar($("#saleSearchResultUl"));
}

function addendToSearchBar(el){
  var searchBar = $("#searchForCampaign");//jquery
  //var searchBar = document.getElementById("keyword");//dom
  var rect = searchBar[0].getBoundingClientRect();//convert jquery object to dom by searchBar[0]
  console.log(rect.top,rect.right,rect.bottom,rect.left);
  var sbHeight = searchBar.height();
  el.css({top:rect.top + sbHeight,left:rect.left,position:'absolute'});
}

function clearResultList() {
  //clear #searchResultUl's elements
  //foreach is javascript's method
  //$("#searchResultUl").children() is jquery
  //toArray() convert $("#searchResultUl").children() to javascript array
  $("#saleSearchResultUl").children().toArray().forEach(
      function (value, index, array) {
        // check if include class name which is dumyLi
        // value is dom html element
        var incFlag = $(value).attr("class").includes("saleDumyLi");
        // delete elements besides dumyLi
        if (!incFlag) {
          $(value).remove();
        }
      })
}

//added by c 2021/05/22 check all
$('#select-all').click(function(event) {
  if(this.checked) {
    // Iterate each checkbox
    $(':checkbox').each(function() {
      this.checked = true;
    });
  } else {
    $(':checkbox').each(function() {
      this.checked = false;
    });
  }
});

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
  var name = $("#saleName").val();
  var starDate = $("#startDate").val();
  var endDate = $("#endDate").val();
  data = {
    "name": name,
    "startDate": starDate,
    "endDate": endDate,
  };
  $.ajax({
    type: 'POST',//方法类型
    url: "/admin/goods/saleInsert",
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

