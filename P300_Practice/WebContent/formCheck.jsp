<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

		<script src="jquery/jquery-1.11.0.min.js"></script>
		<script src="jquery/jquery.validate.min.js"></script>
		<script src="jquery/messages_zh.js"></script>

		<style type="text/css">
			.error {
				color: red;
			}

    *{
        margin: 0px;
        padding: 0px;
        box-sizing: border-box;
    }
    body{
        background: url("img/register_bg.png") no-repeat center;
        padding-top: 25px;
    }

    .rg_layout{
        width: 900px;
        height: 500px;
        border: 8px solid #EEEEEE;
        background-color: white;
        /*让div水平居中*/
        margin: auto;
    }

    .rg_left{
        /*border: 1px solid red;*/
        float: left;
        margin: 15px;
    }
    .rg_left > p:first-child{
        color:#FFD026;
        font-size: 20px;
    }

    .rg_left > p:last-child{
        color:#A6A6A6;
        font-size: 20px;

    }


    .rg_center{
        float: left;
       /* border: 1px solid red;*/

    }

    .rg_right{
        /*border: 1px solid red;*/
        float: right;
        margin: 15px;
    }

    .rg_right > p:first-child{
        font-size: 15px;

    }
    .rg_right p a {
        color:pink;
    }

    .td_left{
        width: 100px;
        text-align: right;
        height: 45px;
    }
    .td_right{
        padding-left: 50px ;
    }

    #username,#password,#email,#name,#tel,#birthday,#checkcode,#password2{
        width: 251px;
        height: 32px;
        border: 1px solid #A6A6A6 ;
        /*设置边框圆角*/
        border-radius: 5px;
        padding-left: 10px;
    }
    #checkcode{
        width: 110px;
    }

    #img_check{
        height: 32px;
        vertical-align: middle;
    }

    #btn_sub{

        width: 150px;
        height: 40px;
        background-color: #FFD026;
        border: 1px solid #FFD026 ;
    }
    .error{
        color:red;
    }
    #td_sub{
        padding-left: 150px;
    }
	</style>


	</head>
	<body>

		<div class="rg_layout">


			<div class="rg_center">
				<div class="rg_form">
					<!--定义表单 form-->

					<form action="#" method="post">

						<table>
							<tr>
								<td class="td_left"><label for="username">用户名</label></td>
								<td class="td_right"><input type="text" name="username"  id="username"
									 placeholder="请输入用户名"> <span id="s_username" class="error"></span></td>

							</tr>

							<tr>
								<td class="td_left"><label for="password">密码</label></td>
								<td class="td_right"><input type="password" name="password" id="password"  placeholder="请输入密码">
									<span id="s_password" class="error"></span></td>
							</tr>
							
							<tr>
								<td class="td_left"><label for="password">密码2</label></td>
								<td class="td_right"><input type="password" name="password2" id="password2" placeholder="请输入密码"
									 equalTo="#password"> <span id="s_password" class="error"></span>
								</td>
							</tr>
							
							<tr>
								<td class="td_left"><label for="email">Email</label></td>
								<td class="td_right"><input type="email" name="email" id="email"  placeholder="请输入邮箱"> <span
									 id="s_email" class="error"></span></td>
							</tr>

							<tr>
								<td class="td_left"><label for="name">姓名</label></td>
								<td class="td_right"><input type="text" name="name" id="name" placeholder="请输入姓名"> <span id="s_name"
									 class="error"></span></td>
							</tr>

							<tr>
								<td class="td_left"><label for="tel">手机号</label></td>
								<td class="td_right"><input type="text" name="tel" id="tel" placeholder="请输入手机号"> <span id="s_tel"
									 class="error"></span></td>
							</tr>


							<tr>
								<td class="td_left"><label for="tel">网址</label></td>
								<td class="td_right"><input type="url" name="tel" id="tel" placeholder="网址"> <span id="s_tel"
									 class="error"></span></td>
							</tr>

							<tr>
								<td class="td_left"><label for="birthday">出生日期</label></td>
								<td class="td_right"><input type="dateISO" name="birthday" id="birthday"  placeholder="请输入出生日期"></td>
							</tr>



							<tr>
								<td colspan="2"  align="center"><input type="submit" id="btn_sub" value="注册"></td>
							</tr>
						</table>

					</form>


				</div>

			</div>



		</div>

		<script type="text/javascript">
			$("form").validate({
				rules:{
					username:{
						required:true,
						minlength:6,
						maxlength:12
					},
					name:{
						required:true,
						minlength:6,
						maxlength:12
					},
					password:{
						required:true,
						minlength:8,
						
					},
					password2:{
						required:true,
						equalTo:"#password"
					},
					email:{
						required:true,
						eamil:true
					},
					tel:{
						required:true,
						digits:true,
						minlength:11,
						maxlength:11
					},
					birthday:{
						required:true,
						dateISO:true
					},
					url:{
						required:true,
						url:true
					}
					
				},
				messages:{
					username:{
						required:"必须字段：",
						minlength:"最小长度：6",
						maxlength:"最大长度：12"
					},
					name:{
						required:"必须字段：",
						minlength:"最小长度：6",
						maxlength:"最大长度：12"
					},
					password:{
						required:"必须字段：",
						minlength:"最小长度：8"
					},
					password2:{
						required:"必须字段：",
						equalTo:"两次密码不一致"
					},
					email:{
						required:"必须字段：",
						eamil:"格式不正确：XXX@xxx.xxx"
					},
					tel:{
						required:"必须字段：",
						digits:"只能数字",
						minlength:"最小长度：11",
						maxlength:"最大长度：11"
					},
					birthday:{
						required:"必须字段：",
						dateISO:"正确格式为：1997-08-28"
					},
					url:{
						required:"必须字段：",
						url:"格式不对"
					}
				}
				
			});
		</script>
	</body>
</html>
