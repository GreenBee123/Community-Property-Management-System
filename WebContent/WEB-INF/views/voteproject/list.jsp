<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!-- 顶部搜索栏 -->
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>搜索住宅:</label><input id="search-name" class="wu-text" style="width:100px">
            
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- 新增住宅窗口-->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
           <tr>
                <td width="60" align="right">投票项目名称:</td>
                <td><input type="text" name="projectName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">开始时间:</td>
                <td><input class="wu-text easyui-datetimebox" name="startTime" data-options="required:true,showSeconds:true, missingMessage:'请填写开始时间'"/></td>
            </tr>
            <tr>
                <td width="60" align="right">结束时间:</td>
                <td><input class="wu-text easyui-datetimebox" name="endTime" data-options="required:true,showSeconds:true, missingMessage:'请填写开始时间'"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
       <input type="hidden" name="id" id="edit-id">
        <table>
             <tr>
             <tr>
                <td width="60" align="right">投票项目名称:</td>
                <td><input type="text" id="edit-projectName" name="projectName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">开始时间:</td>
                <td><input class="wu-text easyui-datetimebox" id="edit-startTime" name="startTime" data-options="required:true,showSeconds:true, missingMessage:'请填写开始时间'"/></td>
            </tr>
            <tr>
                <td width="60" align="right">结束时间:</td>
                <td><input class="wu-text easyui-datetimebox" id="edit-endTime" name="endTime" data-options="required:true,showSeconds:true, missingMessage:'请填写开始时间'"/></td>
            </tr>
        </table>
    </form>
</div>

<!-- 投票窗口 -->
<div id="voting-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 400px; height: 500px; padding: 10px;">
	<form id="voting-dialog-form" method="post">
		<table id="voting-dialog-table" cellspacing="8" >
		
		</table>
	</form>
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	
	
	/**
	*  添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$("#add-content").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加住宅信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            }
        });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改住宅信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#edit-id").val(item.id);
            	$("#edit-projectName").val(item.projectName);
            	$('#edit-startTime').val(item.startTime);
				$("#edit-endTime").val(item.endTime);
				
            	
            }
        });
	}	
	
	/**
	* 打开修改窗口
	*/
	function voting(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要投票的项目！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行投票！','info');
			return;
		}
		item = item[0];
		$('#voting-dialog').dialog({
			closed: false,
			modal:true,
            title: "投票！",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler : function() {
					var validate = $("#voting-dialog-form")
							.form("validate");
					if (!validate) {
						$.messager
								.alert("消息提醒",
										"请检查你输入的数据!",
										"warning");
						return;
					}
					var data = $("#voting-dialog-form").serialize();
					$
							.ajax({
								url : 'addvoting',
								dataType : 'json',
								type : 'post',
								data : data,
								success : function(data) {
									if (data.type == 'success') {
										$.messager
												.alert(
														'信息提示',
														'投票成功！',
														'info');
										$('#voting-dialog').dialog(
										'close');
										$(
												'#data-datagrid')
												.datagrid(
														'reload');
										
									} else {
										$('#voting-dialog').dialog(
										'close');
										$.messager
												.alert(
														'信息提示',
														data.msg,
														'warning');
										
									}
									
								}
							});
				}
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#voting-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#votingproject-id").val(item.id);
            	$("#voting-dialog-table").html("");
				$.ajax({
					url:'get_voting_option',
					dataType:'json',
					type:'post',
					data:{id:item.id,startTime:item.startTime,endTime:item.endTime},
					success:function(data){
						if(data.type == 'success'){
							var topic=data.VoteTopic;
							var option=data.VoteOption;
							var table = '';
							for (var i = 0; i < topic.length; i++) {
								/* 每一个按钮的效果  <a onclick="selected(this)" href="javascript:void(0)" class="icon-accept">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>*/
								table +='<label>'+topic[i].topicName+':</label></br>';
								
								for(var j=0;j<option.length;j++){
									if(option[j].topicId==topic[i].id){
										var tbody = '<input name="'+topic[i].id+'" id="'+option[j].id+'" value="'+option[j].id+'" type="radio" />'+option[j].content+'</br>';
										table += tbody;
									}
								}
								
							}
							$("#voting-dialog-table").append(
									table);//将凭借的字符串显示在table中
							
						}else{
							$('#voting-dialog').dialog(
							'close');
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
				
            	
            }
        });
	}	
	
	function add0(m){return m<10?'0'+m:m }
	function format(shijianchuo){
	//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	}
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {content:$("#search-name").val()};
		$('#data-datagrid').datagrid('reload',option);
	});

	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
		fit:true,
		columns:[[
			{ field:'projectName',title:'投票项目名称',width:100,sortable:true},
			{ field:'startTime',title:'开始时间',width:100,formatter:function(value,row,index){
				return format(value);
			}},
			{ field:'endTime',title:'结束时间',width:100,formatter:function(value,row,index){
				return format(value);
			}},{
				field : 'icon',
				title : '统计结果',
				width : 100,
				formatter : function(value, row, index) {
					var test = '<a class="voteOptiono-edit" onclick=""></a>'
					return test;
				}
			},
			
		]],
		onLoadSuccess : function(data) {
			$('.voteOptiono-edit').linkbutton({
				text : '统计结果',
				plain : true,
				iconCls : 'icon-edit'
			});
		}
	});
</script>