function _change() {
	$("#vCode").attr("src", "/KunKunBookStore/VerifyCodeServlet?" + new Date().getTime());
}