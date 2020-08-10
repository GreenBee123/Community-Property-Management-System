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
                <td width="60" align="right">住房编码:</td>
                <td><input type="text" name="houseId" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写住房编码'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">建筑面积:</td>
                <td><input type="text" name="floor_area" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">使用面积:</td>
                <td><input type="text" name="usable_floor_area" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写使用面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">入住用户Id:</td>
                <td><input type="text" name="user.id" class="wu-text easyui-validatebox"  /></td>
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
                <td width="60" align="right">住房编码:</td>
                <td><input type="text" id="edit-houseId" name="houseId" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写住房编码'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">建筑面积:</td>
                <td><input type="text" id="edit-floor-area" name="floor_area" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">使用面积:</td>
                <td><input type="text" id="edit-usable-floor-area" name="usable_floor_area" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写使用面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">入住用户Id:</td>
                <td><input type="text" id="edit-userId" name="user.id" class="wu-text easyui-validatebox"  /></td>
            </tr>
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
            	if(item.id!=null){
            		$("#edit-id").val(item.id);
            	}
            	if(item.user!=null&&item.user.id!=null){
            		$("#edit-userId").val(item.user.id);
            	}
            	$("#edit-houseId").val(item.houseId);
				$("#edit-name").val(item.name);
            	$("#edit-floor-area").val(item.floor_area);
            	$("#edit-usable-floor-area").val(item.usable_floor_area);
            	$("#edit-statu").combobox('setValue',item.statu);
            	
            }
        });
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
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'houseId',title:'住房编码',width:100,sortable:true},
			{ field:'name',title:'住房名称',width:100,sortable:true},
			{ field:'floor_area',title:'建筑面积',width:100,sortable:true},
			{ field:'usable_floor_area',title:'使用面积',width:100,sortable:true},
			{ field:'statu',title:'住房状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '未入住';
					}
					case 1:{
						return '已入住';
					}
				}
				return value;
			},styler:function(value,row,index){
				switch(value){
					case 0:{
						return 'color:red;';
					}
					case 1:{
						return 'color:blue;';
					}
				}
				return value;
			}},
			{ field:'user.username',title:'住户名称',width:100,formatter:function(value,row,index){
				if(row.user!=null){
					return row.user.username;
				}
				return '无';
			}},
		]]
	});
</script>