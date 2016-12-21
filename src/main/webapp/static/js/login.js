$(function () {

    $('#switch_qlogin').click(function () {
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left: '0px', width: '70px'});
        $('#qlogin').css('display', 'none');
        $('#web_qr_login').css('display', 'block');

    });
    $('#switch_login').click(function () {

        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left: '154px', width: '70px'});

        $('#qlogin').css('display', 'block');
        $('#web_qr_login').css('display', 'none');
    });
    if (getParam("a") == '0') {
        $('#switch_login').trigger('click');
    }

});

function logintab() {
    scrollTo(0);
    $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
    $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
    $('#switch_bottom').animate({left: '154px', width: '96px'});
    $('#qlogin').css('display', 'none');
    $('#web_qr_login').css('display', 'block');

}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) {
    var params = location.search.substr(1); // 获取参数 平且去掉？
    var ArrParam = params.split('&');
    if (ArrParam.length == 1) {
        //只有一个参数的情况
        return params.split('=')[1];
    }
    else {
        //多个参数参数的情况
        for (var i = 0; i < ArrParam.length; i++) {
            if (ArrParam[i].split('=')[0] == pname) {
                return ArrParam[i].split('=')[1];
            }
        }
    }
}


//var reMethod = "POST",
//    pwdmin = 6;
//
//$(document).ready(function () {
//
//
//    $('#reg').click(function () {
//
//        if ($('#user').val() == "") {
//            //$('#user').focus().css({
//            //    border: "1px solid red",
//            //    boxShadow: "0 0 2px red"
//            //});
//            $('#user').focus();
//            $("#user").tips({
//                side: 2,
//                msg: '用户名不能为空',
//                bg: '#AE81FF',
//                time: 3
//            });
//            return false;
//        }
//
//        if ($('#user').val().length < 4 || $('#user').val().length > 16) {
//
//            $('#user').focus();
//            //$('#user').focus().css({
//            //    border: "1px solid red",
//            //    boxShadow: "0 0 2px red"
//            //});
//            $("#user").tips({
//                side: 2,
//                msg: '用户名位4-16字符',
//                bg: '#AE81FF',
//                time: 3
//            });
//            return false;
//        }
//
//        if ($('#passwd').val().length < pwdmin) {
//            $('#passwd').focus();
//            $("#passwd").tips({
//                side: 2,
//                msg: '密码不能小于' + pwdmin + '位',
//                bg: '#AE81FF',
//                time: 3
//            });
//            return false;
//        }
//        if ($('#passwd2').val() != $('#passwd').val()) {
//            $('#passwd2').focus();
//            $("#passwd2").tips({
//                side: 2,
//                msg: '两次密码不一致',
//                bg: '#AE81FF',
//                time: 3
//            });
//            return false;
//        }
//
//        var sqq = /^1[34578]\d{9}$/;
//        if (!sqq.test($('#cellNumber').val()) || $('#cellNumber').val().length < 5 || $('#cellNumber').val().length > 12) {
//            //$('#cellNumber').focus().css({
//            //    border: "1px solid red",
//            //    boxShadow: "0 0 2px red"
//            //});
//            $("#cellNumber").tips({
//                side: 2,
//                msg: '手机号格式不正确',
//                bg: '#AE81FF',
//                time: 3
//            });
//            return false;
//        } else {
//            $('#cellNumber').css({
//                border: "1px solid #D7D7D7",
//                boxShadow: "none"
//            });
//
//        }
//
//        var loginname = $("#u").val();
//        var password = $("#p").val();
//
//        $.ajax({
//            type: "POST",
//            url: '<%=request.getContextPath()%>/userAction/login',
//            data: {loginId: loginname, pwd: password},
//            dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
//            cache: false,
//            success: function (data) {
//
//            }
//        });
//    });
//
//
//});