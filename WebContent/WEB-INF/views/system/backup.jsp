<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2020, GreenBee, Ltd." />
<title></title>
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/wu.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/icon.css" />
<script type="text/javascript" src="../resources/admin/easyui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body class="easyui-layout">

<!-- 修改密码窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:false,iconCls:'icon-save',modal:true,title:'备份还原数据库',buttons:[{text:'确认还原',iconCls: 'icon-ok',handler:Recover},{text:'备份数据库',iconCls: 'icon-ok',handler:Backup}]" style="width:500px; padding:10px;">
	<form id="edit-form" method="post">
        <table>
           <tr>
                <td width="60" align="right">恢复文件:</td>
                <td><input type="text" id="edit-photo" name="username" class="wu-text easyui-validatebox" readonly="readonly" value="" /></td>
                <td><a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">选择文件</a></td>
            </tr>
        </table>
    </form>
</div>

<input type="file" id="photo-file" style="display:none;" onchange="upload()">
</body>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	function Recover(){
		var validate = $("#edit-form").form("validate");
		$.ajax({
			url:'recover',
			type:'post',
			data:{newpassword:$("#newPassword").val(),oldpassword:$("#oldPassword").val()},
			dataType:'json',
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert("消息提醒","数据库还原成功!","warning");
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			}
		})
	}
	
	function Backup(){
		var validate = $("#edit-form").form("validate");

		$.ajax({
			url:'backup',
			type:'post',
			data:{newpassword:$("#newPassword").val(),oldpassword:$("#oldPassword").val()},
			dataType:'json',
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert("消息提醒","数据库备份成功!","warning");
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			}
		})
	}
	
	//上传图片
	function start(){
			var value = $('#p').progressbar('getValue');
			if (value < 100){
				value += Math.floor(Math.random() * 10);
				$('#p').progressbar('setValue', value);
			}else{
				$('#p').progressbar('setValue',0)
			}
	};
	function upload(){
		
		if($("#photo-file").val() == '')return;
		var formData = new FormData();
		formData.append('photo',document.getElementById('photo-file').files[0]);
		$("#process-dialog").dialog('open');
		var interval = setInterval(start,200);
		$.ajax({
			url:'back',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#preview-photo").attr('src',data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src',data.filepath);
					$("#edit-photo").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败!","warning");
			}
		});
	}
	
	function uploadPhoto(){
		$("#photo-file").click();
		
	}
	
	function recover(){
		var validate = $("#edit-form").form("validate");
		$.ajax({
			url:'recover',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#preview-photo").attr('src',data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src',data.filepath);
					$("#edit-photo").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败!","warning");
			}
		});
	}
	
	function backup(){
		var validate = $("#edit-form").form("validate");
		$.ajax({
			url:'backup',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#preview-photo").attr('src',data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src',data.filepath);
					$("#edit-photo").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败!","warning");
			}
		});
	}
</script>