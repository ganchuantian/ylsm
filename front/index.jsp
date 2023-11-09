<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>自定义同步选择</title>
  <link type="text/css" rel="stylesheet" href="https://www.jq22.com/demo/jeDate-gh-pages20160303/test/jeDate-test.css">
  <link type="text/css" rel="stylesheet" href="https://www.jq22.com/demo/jeDate-gh-pages20160303/skin/jedate.css">
  <script type="text/javascript" src="https://www.jq22.com/demo/jeDate-gh-pages20160303/src/jedate.js"></script>
</head>
<body>
<style type="text/css">
  .ant-btn {
    line-height: 1.499;
    position: relative;
    display: inline-block;
    font-weight: 400;
    white-space: nowrap;
    text-align: center;
    background-image: none;
    border: 1px solid transparent;
    -webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.015);
    box-shadow: 0 2px 0 rgba(0,0,0,0.015);
    cursor: pointer;
    -webkit-transition: all .3s cubic-bezier(.645, .045, .355, 1);
    transition: all .3s cubic-bezier(.645, .045, .355, 1);
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    height: 32px;
    padding: 0 15px;
    font-size: 14px;
    border-radius: 4px;
    color: #333333;
    background-color: #ece9d8;
    border-color: #d9d9d9;
    cursor:pointer;
  }
  .ant-btn-red {
    color: #fff;
    background-color: #FF5A44;
    border-color: #FF5A44;
    text-shadow: 0 -1px 0 rgba(0,0,0,0.12);
    -webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.045);
    box-shadow: 0 2px 0 rgba(0,0,0,0.045);
  }
</style>
<div class="jebody">

  <div class="jewarp">
    <h3 class="gray">自定义同步选择</h3>
    <div class="content">
      <form action="" id="postParam" method="post" enctype="multipart/form-data">
      <div class="jeitem">
        <label class="jelabel">开始时间</label>
        <div class="jeinpbox"><input type="text" class="jeinput" id="queryStartDate" name="queryStartDate" placeholder=""></div>
      </div>
      <div class="jeitem">
        <label class="jelabel">结束时间</label>
        <div class="jeinpbox"><input type="text" class="jeinput" id="queryEndDate" name="queryEndDate" placeholder=""></div>
      </div>
      <div class="jeitem">
        <label class="jelabel">搜索类型</label>
        <div class="jeinpbox">
          <select id="type" name="type" style="display:block;width:100%;padding-left:10px;height:36px;line-height:34px\9;border:1px solid #e6e6e6;border-radius:3px;background-color:#fcfcfc">
            <option value="cloundSender">云仓同步</option>
            <option value="postProductSender">发货单同步</option>
            <option value="productInfoSender">商品档案同步</option>
            <option value="rejectProductSender">退货单同步</option>
          </select>
        </div>
      </div>
    </form>
    </div>
  </div>
  <div style="margin: auto;text-align: center;">
    <input type="button" value="确定" class="ant-btn ant-btn-red" onclick="post()">
  </div>

</div>
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
  var enLang = {
    name: "en",
    month: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
    weeks: ["SUN", "MON", "TUR", "WED", "THU", "FRI", "SAT"],
    times: ["Hour", "Minute", "Second"],
    timetxt: ["Time", "Start Time", "End Time"],
    backtxt: "Back",
    clear: "Clear",
    today: "Now",
    yes: "Confirm",
    close: "Close"
  }

  jeDate("#queryStartDate", {
    format: "YYYY-MM-DD"
  });
  jeDate("#queryEndDate", {
    format: "YYYY-MM-DD"
  });

  function post(){
    $.ajax({
      type: "POST",
      url: "http://localhost/bg/api",
      data: $("#postParam").serialize(),
      dataType: "text",
      success: function(data){
        alert("成功!");
      },
      error:function(arg1){
        //请求出错处理
        console.log(arg1)
        alert("后端已处理该请求，请于数据库查看结果!");
      }
    });
  }

</script>
</body>
</html>
