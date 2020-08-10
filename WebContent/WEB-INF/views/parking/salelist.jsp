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
<!-- 新增销售信息窗口-->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
	<input type="hidden" name="user.id" value="${admin.id}">
        <table>
           <tr>
                <td width="60" align="right">产权人:</td>
                <td><input type="text" name="user.name" value="${admin.username}" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写住房编码'" /></td>
            </tr>
         <tr>
                <td align="right">车位编号:</td>
                <td>
                	<select name="parking.id" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0"></option>
		                <c:forEach items="${Parking}" var="park">
		                	<c:if test="${park.statu==0}">
		                		<option value="${park.id}">编号：${park.id} - 租：${park.prelease_prices} - 售：${park.presale_prices}</option>
		                	</c:if>
		                		
		                </c:forEach>
		            </select>
                </td>
            </tr>
            
             <tr>
                <td width="60" align="right">销售方式:</td>
                <td>
                	<select name="statu" class="easyui-combobox" panelHeight="auto" style="width:268px">
            			<option value="1">租赁</option>
            			<option value="2">销售</option>
		            </select>
                </td>
            </tr>
            
            <tr>
                <td width="60" align="right">联系方式:</td>
                <td><input type="text" name="contact" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写建筑面积'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">车牌号:</td>
                <td><input type="text" name="license_number" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写使用面积'" /></td>
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
			url:'addsale',
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
					url:'deletesale',
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
            	$("#edit-houseId").val(item.houseId);
				$("#edit-name").val(item.name);
            	$("#edit-floor-area").val(item.floor_area);
            	$("#edit-usable-floor-area").val(item.usable_floor_area);
            	$("#edit-statu").combobox('setValue',item.statu);
            	$("#edit-userId").val(item.user.id);
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
		url:'../../admin/parking/salelist',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
		fit:true,
		columns:[[
			/*{checkbox:true}   是否显示勾选框*/
			{ field:'saleId',title:'销售编号编号',width:100,sortable:true},/*sortable是否支持排序*/
			{ field:'user.username',title:'产权人',width:100,formatter:function(value,row,index){
				if(row.user!=null){
					return row.user.username;
				}
				return '无';
			}},/*sortable是否支持排序*/
			{ field:'parking.id',title:'车位编号',width:100,formatter:function(value,row,index){
				if(row.parking!=null){
					return row.parking.id;
				}
				return '无';
			}},
			{ field:'parking.statu',title:'销售方式',width:100,formatter:function(value,row,index){
				if(row.parking!=null){
					if(row.parking.statu==1){
						return "租赁";
					}
					if(row.parking.statu==2){
						return "销售";
					}
					
				}
				return '无';
			}},
			{ field:'contact',title:'联系电话',width:100,sortable:true},/*sortable是否支持排序*/
			{ field:'license_number',title:'车牌号',width:100,sortable:true},
			{ field:'sale_date',title:'销售时间',width:200,formatter:function(value,row,index){
				return format(value);
			}},
		]]
	});
	
</script>