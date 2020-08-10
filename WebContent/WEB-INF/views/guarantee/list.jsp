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
<!-- 新增装修窗口-->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">保证金:</td>
                <td><input type="text" name="bail" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写使用面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">违约金:</td>
                <td><input type="text" name="penalty" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td width="60" align="right">装修内容:</td>
                <td><input type="text" name="decorationContent" class="wu-text easyui-validatebox"  /></td>
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
                <td width="60" align="right">保证金:</td>
                <td><input type="text" id="edit-bail" name="bail" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写使用面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">违约金:</td>
                <td><input type="text" id="edit-penalty" name="penalty" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td width="60" align="right">装修内容:</td>
                <td><input type="text" id="edit-decorationContent" name="decorationContent" class="wu-text easyui-validatebox"  /></td>
            </tr>
        </table>
    </form>
</div>

<!-- 审批弹窗 -->
<div id="approval-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="approval-form" method="post">
       <input type="hidden" name="id" id="approval-id">
        <table>
              <tr>
                <td width="60" align="right">审批状态:</td>
                <td>
                	<select id="approval-statu" name="statu" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">已作废</option>
            			<option value="1">待审批</option>
            			<option value="2">审批通过</option>
            			<option value="3">待验收</option>
            			<option value="4">验收通过</option>
		            </select>
                </td>
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
	* 审批记录
	*/
	function approval(){
		var validate = $("#approval-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#approval-form").serialize();
		$.ajax({
			url:'approval',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#approval-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
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
            	$("#edit-bail").val(item.bail);
				$("#edit-penalty").val(item.penalty);
            	$("#edit-decorationContent").val(item.decorationContent);
            }
        });
	}	
	
	/**
	* 打开审批窗口
	*/
	function openApproval(){
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
		$('#approval-dialog').dialog({
			closed: false,
			modal:true,
            title: "审批装修信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: approval
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#approval-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#approval-id").val(item.id);
            	$("#approval-statu").combobox('setValue',item.statu);
            }
        });
	}	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {content:$("#search-name").val()};
		$('#data-datagrid').datagrid('reload',option);
	});
	
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
			{ field:'house.name',title:'住房名称',width:100,formatter:function(value,row,index){
				return row.house.name;
			}},
			{ field:'user.username',title:'申请人',width:100,formatter:function(value,row,index){
				return row.user.username;
			}},
			{ field:'applicationDate',title:'申请日期',width:100,formatter:function(value,row,index){
				return format(value);
			}},
			{ field:'bail',title:'装修保证金',width:100,sortable:true},
			{ field:'penalty',title:'违约金',width:100,sortable:true},
			{ field:'statu',title:'状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '已作废';
					}
					case 1:{
						return '待审批';
					}
					case 2:{
						return '审批通过';
					}
					case 3:{
						return '待验收';
					}
					case 4:{
						return '已验收';
					}
				}
				return value;
			}},
			{ field:'decorationContent',title:'装修内容',width:150,sortable:true},
			{ field:'approvalTime',title:'审批日期',width:100,formatter:function(value,row,index){
				if(row.approvalTime!=null){
					return format(value);
				}
				return '无';
			}},
			{ field:'examiner.name',title:'审批人',width:100,formatter:function(value,row,index){
				if(row.examiner!=null){
					return row.examiner.name;
				}
				return '无';
			}},
			{ field:'remark',title:'标识',width:100,sortable:true},
		
		]]
	});
</script>