function form_submit(){
	document.getElementById("login").submit();
}
function form_reset(){
	document.getElementById("login").reset();
}
function reloadcode(){
    var verify=document.getElementById('safecode');
    verify.setAttribute('src','images/checkcodes.png?'+Math.random());
}
function deleteSelected() {
    var url="/backend/channeleDelete?";
    var cs=document.getElementsByName("cid")
    for (var i=0;i<cs.length;i++){
        if (cs[i].checked){
            var cid=cs[i].value;
            url+="cid"+cid+"&"
        }
    }
    location=url;

}