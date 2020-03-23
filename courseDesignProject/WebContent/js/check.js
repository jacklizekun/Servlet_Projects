
	function CheckInput(){
		var myform=document.myform;
		var a=myform.name.value;
		
		//alert(a.length);
		//判断备件名称是否为空
		if(a.length<1){

			alert("备件名称为必填项！");
			document.getElementById("name").focus();//光标定位到文本框b
			return;
		}
		myform.submit();	
		}

	
