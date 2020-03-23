$('basic').datagrid({
	columns : [ [ //添加列  
	              {
	                  field : '备件名称', //绑定数据源ID  
	                  title : '备件名称', //显示列名称
	                  align : 'center', //内容在列居中
	                  width : 100 //列的宽度
	              },
	              {
	                  field : '备件编码',
	                  title : '备件编码',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '备件单位',
	                  title : '备件单位',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '存放库区',
	                  title : '存放库区',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '备件单价',
	                  title : '备件单价',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '备件质量',
	                  title : '备件质量',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '备件规格',
	                  title : '备件规格',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '安全库存',
	                  title : '安全库存',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '原始库存',
	                  title : '原始库存',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '初始年需求量',
	                  title : '初始年需求量',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '订货成本',
	                  title : '订货成本',
	                  align : 'center',
	                  width : 100
	              },
	              {
	                  field : '单位库存成本',
	                  title : '单位库存成本',
	                  align : 'center',
	                  width : 100
	              },
	          ] ],
	          pagination : true, //开启分页
	          striped:true,
	          loadMsg:"正在加载数据…",
	          singleSelect:true ,
	          rownumbers:true
	         // url : 'DB', //获取数据地址
	        //  loadFilter : pagerFilter, //①调用分页函数
})