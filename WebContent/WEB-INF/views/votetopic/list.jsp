<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<label>搜索住宅:</label><input id="search-name" class="wu-text"
				style="width: 100px"> <label>投票项目:</label> <select
				id="search-role" class="easyui-combobox" panelHeight="auto"
				style="width: 120px">
				<option value="-1">全部</option>
				<c:forEach items="${VoteProject }" var="project">
					<option value="${project.id }">${project.projectName }</option>
				</c:forEach>
			</select> <a href="#" id="search-btn" class="easyui-linkbutton"
				iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- End of toolbar -->
	<table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- 新增住宅窗口-->
<div id="add-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 420px; padding: 10px;">
	<form id="add-form" method="post">
		<table>
			<tr>
				<td width="60" align="right">投票项目:</td>
				<td><select name="projectId" class="easyui-combobox"
					panelHeight="auto" style="width: 268px"
					data-options="required:true, missingMessage:'请选择角色'">
						<c:forEach items="${VoteProject }" var="project">
							<option value="${project.id }">${project.projectName }</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td width="60" align="right">题目名称:</td>
				<td><input type="text" name="topicName"
					class="wu-text easyui-validatebox"
					data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
			</tr>

			<tr>
				<td width="60" align="right">性别:</td>
				<td><select name="category" class="easyui-combobox"
					panelHeight="auto" style="width: 268px">
						<option value="0"></option>
						<option value="1">单选</option>
						<option value="2">多选</option>
				</select></td>
			</tr>
		</table>
	</form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 450px; padding: 10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id">
		<table>
			<tr>
				<td width="60" align="right">投票项目:</td>
				<td><select name="projectId" id="edit-projectId"
					class="easyui-combobox" panelHeight="auto" style="width: 268px"
					data-options="required:true, missingMessage:'请选择角色'">
						<c:forEach items="${VoteProject }" var="project">
							<option value="${project.id }">${project.projectName }</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td width="60" align="right">题目名称:</td>
				<td><input type="text" name="topicName" id="edit-topicName"
					class="wu-text easyui-validatebox"
					data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
			</tr>

			<tr>
				<td width="60" align="right">选项类型:</td>
				<td><select name="category" id="edit-category"
					class="easyui-combobox" panelHeight="auto" style="width: 268px">
						<option value="0"></option>
						<option value="1">单选</option>
						<option value="2">多选</option>
				</select></td>
			</tr>
		</table>
	</form>
</div>

<!-- 修改投票选项窗口 -->
<div id="voteOptiono-dialog" class="easyui-dialog"
	data-options="closed:true,iconCls:'icon-save'"
	style="width: 400px; height: 500px; padding: 10px;">
	<form id="voteOption-form" method="post">
		<input type="hidden" name="projectId" id="voteOption-projectId">
		<input type="hidden" name="id" id="voteOption-id">
		
		<table id="voteOption-table" cellspacing="8" >
			<div class="content">
				<div class="options" id="voteOption-div">

				</div>
				<div class="add-area" id="add-area">
					<div class="addBtn ">添加</div>
				</div>
				</div>
		</table>
	</form>
</div>


<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	 *  添加记录
	 */
	function add() {
		var validate = $("#add-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒", "请检查你输入的数据!", "warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url : 'add',
			dataType : 'json',
			type : 'post',
			data : data,
			success : function(data) {
				if (data.type == 'success') {
					$.messager.alert('信息提示', '添加成功！', 'info');
					$("#add-content").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				} else {
					$.messager.alert('信息提示', data.msg, 'warning');
				}
			}
		});
	}

	/**
	 * Name 修改记录
	 */
	function edit() {
		var validate = $("#edit-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒", "请检查你输入的数据!", "warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url : 'edit',
			dataType : 'json',
			type : 'post',
			data : data,
			success : function(data) {
				if (data.type == 'success') {
					$.messager.alert('信息提示', '修改成功！', 'info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				} else {
					$.messager.alert('信息提示', data.msg, 'warning');
				}
			}
		});
	}

	/**
	 * 删除记录
	 */
	function remove() {
		$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
			if (result) {
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url : 'delete',
					dataType : 'json',
					type : 'post',
					data : {
						id : item.id
					},
					success : function(data) {
						if (data.type == 'success') {
							$.messager.alert('信息提示', '删除成功！', 'info');
							$('#data-datagrid').datagrid('reload');
						} else {
							$.messager.alert('信息提示', data.msg, 'warning');
						}
					}
				});
			}
		});
	}

	/**
	 * Name 打开添加窗口
	 */
	function openAdd() {
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed : false,
			modal : true,
			title : "添加住宅信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : add
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#add-dialog').dialog('close');
				}
			} ],
			onBeforeOpen : function() {
				//$("#add-form input").val('');
			}
		});
	}

	/**
	 * 打开修改窗口
	 */
	function openEdit() {
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if (item == null || item.length == 0) {
			$.messager.alert('信息提示', '请选择要修改的数据！', 'info');
			return;
		}
		if (item.length > 1) {
			$.messager.alert('信息提示', '请选择一条数据进行修改！', 'info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed : false,
			modal : true,
			title : "修改住宅信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : edit
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#edit-dialog').dialog('close');
				}
			} ],
			onBeforeOpen : function() {
				$("#edit-id").val(item.id);
				$("#edit-topicName").val(item.topicName);
				$("#edit-category").combobox('setValue', item.category);
			}
		});
	}

	function add0(m) {
		return m < 10 ? '0' + m : m
	}
	function format(shijianchuo) {
		//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth() + 1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':'
				+ add0(mm) + ':' + add0(s);
	}

	function voteOptiono(id, projectId) {
		var content;
		var item = $('#data-datagrid').datagrid('getSelections');
		$('#voteOptiono-dialog')
				.dialog(
						{
							closed : false,
							modal : true,
							title : "修改题目信息",
							buttons : [
									{
										text : '确定',
										iconCls : 'icon-ok',
										handler : function() {
											var validate = $("#voteOption-form")
													.form("validate");
											if (!validate) {
												$.messager
														.alert("消息提醒",
																"请检查你输入的数据!",
																"warning");
												return;
											}
											var data = $("#voteOption-form").serialize();
											$
													.ajax({
														url : 'addoption',
														dataType : 'json',
														type : 'post',
														data : data,
														success : function(data) {
															if (data.type == 'success') {
																$.messager
																		.alert(
																				'信息提示',
																				'添加成功！',
																				'info');
																$('#voteOptiono-dialog').dialog(
																'close');
																$(
																		'#data-datagrid')
																		.datagrid(
																				'reload');
																
															} else {
																$.messager
																		.alert(
																				'信息提示',
																				data.msg,
																				'warning');
															}
														}
													});
										}
									},
									{
										text : '取消',
										iconCls : 'icon-cancel',
										handler : function() {
											$('#voteOptiono-dialog').dialog(
													'close');
										}
									} ],
							onBeforeOpen : function() {
								$("#voteOption-div").html("");
								$("#voteOption-projectId").val(projectId);
								$("#voteOption-id").val(id);
								//首先改题目有的题目
								$
										.ajax({
											url : 'get_all_option',
											data : {
												id : id
											},
											type : 'post',
											dataType : 'json',
											success : function(data) {
												content = data;
												var div = '';
												for (var i = 0; i < content.length; i++) {
													/* 每一个按钮的效果  <a onclick="selected(this)" href="javascript:void(0)" class="icon-accept">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>*/
													var tbody = '<div class="option-item"><input name="name" id="'+content[i].id+'" value="'+content[i].content+'" type="text" /><div class="deleteBtn">删除</div></div>';
													div += tbody;
												}
												$("#voteOption-div").append(
														div);//将凭借的字符串显示在table中
											}
										});

							}
						});
	}

	//搜索按钮监听
	$("#search-btn").click(function() {
		var option = {
			content : $("#search-name").val()
		};
		$('#data-datagrid').datagrid('reload', option);
	});

	/** 
	 * 载入数据
	 */
	$('#data-datagrid')
			.datagrid(
					{
						url : 'list',
						rownumbers : true,
						singleSelect : true,
						pageSize : 20,
						pagination : true,
						multiSort : true,
						fitColumns : true,
						idField : 'id',
						fit : true,
						columns : [ [
								{
									field : 'id',
									title : '题目id',
									width : 100,
									sortable : true
								},
								{
									field : 'projectId',
									title : '所属项目',
									width : 100,
									sortable : true
								},
								{
									field : 'topicName',
									title : '题目名称',
									width : 100,
									sortable : true
								},
								{
									field : 'category',
									title : '题目类别',
									width : 100,
									sortable : true
								},
								{
									field : 'topicVoteNumber',
									title : '投票人数',
									width : 100,
									sortable : true
								},
								{
									field : 'icon',
									title : '修改选项',
									width : 100,
									formatter : function(value, row, index) {
										var test = '<a class="voteOptiono-edit" onclick="voteOptiono('
												+ row.id
												+ ','
												+ row.projectId
												+ ')"></a>'
										return test;
									}
								}, ] ],
						onLoadSuccess : function(data) {
							$('.voteOptiono-edit').linkbutton({
								text : '编辑题目',
								plain : true,
								iconCls : 'icon-edit'
							});
						}
					});
</script>
<script type="text/javascript"
	src="../../resources/admin/easyui/js/index.js"></script>