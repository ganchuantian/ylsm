<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>自定义同步选择</title>
  <!-- <link type="text/css" rel="stylesheet" href="https://www.jq22.com/demo/jeDate-gh-pages20160303/test/jeDate-test.css">
  <link type="text/css" rel="stylesheet" href="https://www.jq22.com/demo/jeDate-gh-pages20160303/skin/jedate.css">
  <script type="text/javascript" src="https://www.jq22.com/demo/jeDate-gh-pages20160303/src/jedate.js"></script> -->
  <script src="js/vue.js"></script>
  <script src="js/index.js"></script>
</head>
<body>
<style type="text/css">
  @import url("//unpkg.com/element-ui@2.15.14/lib/theme-chalk/index.css");
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
  .jebody .jewarp {
    width: 300px;
    justify-content: center;
    align-items: center;
    margin: auto;
    text-align: center;
  }
</style>
<div class="jebody">

  <div class="jewarp">
    <h3 class="gray">自定义同步选择</h3>
    <div class="content">
      <form action="" id="postParam" method="post" enctype="multipart/form-data">
        <div id = "date">
          <template>
            <div class="block">
              <span class="demonstration" style="float: left; padding-top: 5px;">开始时间</span>
              <el-date-picker
                      v-model="value1"
                      type="datetime"
                      placeholder="选择日期时间"
                      id = "queryStartDate"
                      name = "queryStartDate"
                      :picker-options = "pickerOptions">
              </el-date-picker>
            </div>
            <div class="block">
              <span class="demonstration" style="float: left; padding-top: 5px;">结束时间</span>
              <el-date-picker
                      v-model="value2"
                      type="datetime"
                      placeholder="选择日期时间"
                      id = "queryEndDate"
                      name = "queryEndDate"
                      :picker-options = "pickerOptions">
              </el-date-picker>
            </div>
          </template>
        </div>
        <span class="demonstration" style="float: left; margin-right: 8px; padding-top: 5px;">搜索类型</span>
        <div class="jeinpbox" style="margin-left: 5px;">
          <select id="type" name="type" style="display:block;padding-left:10px;width: 75%;height:36px;line-height:34px\9;border:1px solid #e6e6e6;border-radius:3px;background-color:#fcfcfc">
            <option value="CLOUD_SENDER">云仓同步</option>
            <option value="POST_PRODUCT_SENDER">发货单同步</option>
            <option value="PRODUCE_INFO_SENDER">商品档案同步</option>
            <option value="REJECT_PRODUCT_SENDER">退货单同步</option>
          </select>
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

  function post(){
    $.ajax({
      type: "POST",
      url: "http://localhost/bg/api",
      data: JSON.stringify({queryStartDate: $("#queryStartDate").val(), queryEndDate: $("#queryEndDate").val(), type: $("#type").val()}),
      dataType: "text",
      contentType:"application/json",
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
  var Main1 = {
    data() {
      return {
        options: [{
          value: 'CLOUD_SENDER',
          label: '云仓同步'
        }, {
          value: 'POST_PRODUCT_SENDER发货单同步',
          label: '发货单同步'
        }, {
          value: 'PRODUCE_INFO_SENDER商品档案同步',
          label: '商品档案同步'
        }, {
          value: 'REJECT_PRODUCT_SENDER',
          label: '退货单同步'
        }],
        value: ''
      }
    }
  }
  var Ctor = Vue.extend(Main1)
  new Ctor().$mount('#select')

  var Main2 = {
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            } },
            {
              text: '昨天',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24);
                picker.$emit('pick', date);
              } },
            {
              text: '一周前',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', date);
              } }] },


        value1: '',
        value2: '',
        value3: '' };

    } };

  var Ctor = Vue.extend(Main2);
  new Ctor().$mount('#date');

</script>
</body>
</html>
