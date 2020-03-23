function doSearch() {
    var $ByName = $('#searchByName');
    var ByName = $ByName.val();
    var $se = $('#se');
    var se = $se.val();
    //alert(start);
   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
    window.location.href="/courseDesignProject/searchservlt?ByName="+ByName+"&se="+se;
}

function doSearchex() {
    var $startt = $('#starttime');
    var start = $startt.val();
    var $endt = $('#endtime');
    var end = $endt.val();
    //alert(start);
   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
    window.location.href="/courseDesignProject/searchexservlt?starttime="+start+"&endtime="+end;
}


function doSearchin() {
    var $startt = $('#starttime');
    var start = $startt.val();
    var $endt = $('#endtime');
    var end = $endt.val();
    //alert(start);
   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
    window.location.href="/courseDesignProject/searchinservlt?starttime="+start+"&endtime="+end;
}

function doSearchinventory() {
    var codev=$('#code').combobox('getValue');
  alert(codev);
   //alert("/courseDesignProject/searchservlt?ByName="+ByName);
    window.location.href="/courseDesignProject/invenservlet.java?code="+codev;
}


