var websocket = null;
(function () {


    var url = 'property';
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/project-web/websocket/");
    }
    else {
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        //setMessageInnerHTML("open");
        /*type为property表示物业大屏界面，type为financial时表示财务大屏界面*/
        //send("financial");
        send("repairOrder");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }


})();

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send(type) {
    websocket.send(type);
}

function uploads() {
    $.ajaxFileUpload({
        url: 'http://localhost:8080/project-web/fileManage/uploads', //用于文件上传的服务器端请求地址
        type: 'post',
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: ['file1'], //文件上传域的ID
        data: {
            "userId": 1
        },//一同上传的数据
        dataType: 'json', //返回值类型一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
            console.log(data);
        }
    })
}

function downLoad() {

    var url = 'http://localhost:8080/project-web/fileUpload/download';// 你发送请求的地址
    var param = {
        fileId: 3,
        fileName: "测试"
    };
    $.get(url, param, function (data) {// 回调函数
        alert("成功！");
        console.log(data);// 控制台打印返回结果
    });
    /* $.ajax({
     type:"post",//type可以为post也可以为get
     url:"http://localhost:8080/project-web/fileUpload/download",
     data:{"fileId":3,
     fileName:"测试"
     },//这行不能省略，如果没有数据向后台提交也要写成data:{}的形式
     dataType:"json",//这里要注意如果后台返回的数据不是json格式，那么就会进入到error:function(data){}中
     success:function(data){
     alert("成功！");
     },
     error:function(data){
     alert("用户名提交出现了错误！");
     }
     });*/
}