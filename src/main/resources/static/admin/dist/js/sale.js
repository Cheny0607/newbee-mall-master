//download
$('#download').on('click',function(){
  debugger;
  var ids = [];
  $('input:checkbox:checked').parent().next().map(function (){
    ids.push($(this).text())
    return ids;
  })
  var format = $("#inputGroupSelect04").val();
  var index = ids.indexOf("Campaign Id");
  if(index > -1){
    ids.splice(index,1);
  }
  var data = {
    "ids":ids,
    "format":format
  }
  if(ids==null){
    swal("请选择一条记录",{
      icon:"warning",
    });
    return
  }
  $.ajax({
    type:'POST',//方法类型
    url:"/admin/download/file",
    contentType: 'application/json',
    data:JSON.stringify(data),
    success:function(result){
      debugger;
      //サーバーが成功の場合ここが呼ばれる
      if (result.resultCode == 200){
        // window.location.assign(result.data);
        // result.data = "/Users/chennaiyuan/Desktop/upload/test.csv";   //path
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

//絞り込み検索 filter 2021/05/25
(function(document) {
  'use strict';
  var LightTableFilter = (function(Arr) {
    var _input;
    function _onInputEvent(e) {
      _input = e.target;
      var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
      Arr.forEach.call(tables, function(table) {
        Arr.forEach.call(table.tBodies, function(tbody) {
          Arr.forEach.call(tbody.rows, _filter);
        });
      });
    }
    function _filter(row) {
      var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
      row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
    }
    return {
      init: function() {
        var inputs = document.getElementsByClassName('light-table-filter');
        Arr.forEach.call(inputs, function(input) {
          input.oninput = _onInputEvent;
        });
      }
    };
  })(Array.prototype);
  document.addEventListener('readystatechange', function() {
    if (document.readyState === 'complete') {
      LightTableFilter.init();
    }
  });
})(document);

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
  var campaign = $("#campaign").val();
  var content1 = $("#content1").val();
  var content2 = $("#content2").val();
  var content3 = $("#content3").val();
  var content4 = $("#content4").val();
  var content5 = $("#content5").val();
  var flag = $("#flag").val();
  data = {
    "name": name,
    "startDate": starDate,
    "endDate": endDate,
    "campaign":campaign,
    "content1":content1,
    "content2":content2,
    "content3":content3,
    "content4":content4,
    "content5":content5,
    "flag":flag,
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

//added by c 2021/5/25 sort
 // カラムのクリックイベント
  $("th").click(function(){
    // ★span要素の独自属性（sort）の値を取得
    var sortClass = $(this).find("span").attr("sort");
    var sortFlag = "asc";
    // 初期化
    $("table thead tr span").text("");
    $("table thead tr span").attr("sort", "");

    if(isBlank(sortClass) || sortClass == "asc") {
      $(this).find("span").text("▼");
      // ★独自属性（sort）の値を変更する
      $(this).find("span").attr("sort", "desc");
      sortFlag = "desc";
    } else if(sortClass == "desc") {
      $(this).find("span").text("▲");
      $(this).find("span").attr("sort", "asc");
      sortFlag = "asc";
    }

    var element = $(this).attr("id");
    sort(element, sortFlag);
  });

  function sort(element, sortFlag) {
    // ★sort()で前後の要素を比較して並び変える。※対象が文字か数値で処理を変更
    var arr = $("table tbody tr").sort(function(a, b) {
      if ($.isNumeric($(a).find("td").eq(element).text())) {
        // ソート対象が数値の場合
        var a_num = Number($(a).find("td").eq(element).text());
        var b_num = Number($(b).find("td").eq(element).text());

        if(isBlank(sortFlag) || sortFlag == "desc") {
          // 降順
          return b_num - a_num;
        } else {
          // 昇順
          return a_num - b_num;
        }
      } else {
        // ソート対象が数値以外の場合
        var sortNum = 1;
        if($(a).find("td").eq(element).text()
            > $(b).find("td").eq(element).text()) {
          sortNum = 1;
        } else {
          sortNum = -1;
        }
        if(isBlank(sortFlag) || sortFlag == "desc") {
          // 降順
          sortNum *= (-1) ;
        }

        return sortNum;
      }
    });
    // ★html()要素を置き換える
    $("table tbody").html(arr);
  }

  //バリデーションチェック
  function isBlank(data){
    if (data.length ==0 || data == ''){
      return true;
    } else {
      return false;
    }
  }


