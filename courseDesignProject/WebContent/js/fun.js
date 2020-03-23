function newUser() {
    $('#dlg').dialog('open').dialog('setTitle', 'New User');
    $('#fm').form('clear');
    url = "Save";
}

function saveUser() {
    $('#fm').submit();
    }

