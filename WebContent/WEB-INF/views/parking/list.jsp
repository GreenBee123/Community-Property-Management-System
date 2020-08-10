<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
           <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>菜单名称：</label><input id="search-name" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-treegrid" toolbar="#wu-toolbar"></table>
</div>
<style>
.selected{
	background:red;
}
</style>
<!-- Begin of easyui-dialog -->
<!-- 添加菜单填窗 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">车位状态:</td>
                <td>
                	<select name="statu" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">空闲</option>
            			<option value="1">已租</option>
            			<option value="2">已售</option>
		            </select>
                </td>
            </tr>
           <tr>
                <td width="60" align="right">车位类别:</td>
                <td>
                	<select name="category" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="1">地上</option>
            			<option value="2">地下</option>
		            </select>
                </td>
            </tr>
            <tr>
                <td align="right">预售价格:</td>
                <td><input type="text" name="presale_prices" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">预租价格:</td>
                <td><input type="text" name="prelease_prices" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">车位面积:</td>
                <td><input type="text" name="area" class="wu-text" /></td>
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
                <td width="60" align="right">车位状态:</td>
                <td>
                	<select id="edit-statu" name="statu" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">空闲</option>
            			<option value="1">已租</option>
            			<option value="2">已售</option>
		            </select>
                </td>
            </tr>
           <tr>
                <td width="60" align="right">车位类别:</td>
                <td>
                	<select id="edit-category" name="category" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="1">地上</option>
            			<option value="2">地下</option>
		            </select>
                </td>
            </tr>
            <tr>
                <td align="right">预售价格:</td>
                <td><input type="text" id="edit-presale_prices" name="presale_prices" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">预租价格:</td>
                <td><input type="text" id="edit-prelease_prices" name="prelease_prices" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">车位面积:</td>
                <td><input type="text" id="edit-area" name="area" class="wu-text" /></td>
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
		var data = $("#add-form").serialize();//from表单序列化，把表单中的输入框的内容编程key-value的键值对
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').treegrid('reload');
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
					$('#data-datagrid').treegrid('reload');
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
							$('#data-datagrid').treegrid('reload');
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
            title: "添加菜单信息",
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
            }]
        });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').treegrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
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
            onBeforeOpen:function(){//再打开之前获取修改前的内容
            	$("#edit-id").val(item.id);
            	$("#edit-statu").combobox('setValue',item.statu);
            	$("#edit-category").combobox('setValue',item.category);;
            	$("#edit-presale_prices").val(item.presale_prices);
            	$("#edit-prelease_prices").val(item.prelease_prices);
            	$("#edit-area").val(item.area);
            }
        });
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		$('#data-datagrid').treegrid('reload',{
			name:$("#search-name").val()
		});
	});
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').treegrid({
		url:'list',
		rownumbers:true,//是否显示行号
		singleSelect:true,//只能单选
		pageSize:20, //默认每页显示数量          
		pagination:true,//是否分页
		multiSort:true,
		animate:true,//是否显示动画
		fitColumns:true,//列是否填充
		idField:'id',
		fit:true,
		columns:[[
			/*{checkbox:true}   是否显示勾选框*/
			{ field:'id',title:'车位编号',width:100,sortable:true},/*sortable是否支持排序*/
			{ field:'statu',title:'车位状态',width:100,formatter:function(value,index,row){
				switch (value) {
				case 0:	
					return '空闲';
				case 1:	
					return '已租';
				case 2:	
					return '已售';
				}
				return value;
			}},/*sortable是否支持排序*/
			{ field:'category',title:'车位类别',width:100,formatter:function(value,index,row){
				switch (value) {
				case 1:	
					return '地上';
				case 2:	
					return '地下';
				}
				return value;
			}},
			{ field:'presale_prices',title:'预售价格',width:100,sortable:true},/*sortable是否支持排序*/
			{ field:'prelease_prices',title:'预租价格',width:100,sortable:true},
			{ field:'area',title:'面积',width:100,sortable:true},
		]]
	});
</script>