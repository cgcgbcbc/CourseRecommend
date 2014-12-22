/**
 * Created by zhaoxin on 14-12-21.
 */
var editing=false;
var courseAndScoure=new Object();
var courseIdMap=new Object();
var coursIdBuffer=new Object();
var page=0;
var username
function pre(){
    for(var k in coursIdBuffer){
        delete (coursIdBuffer[k]);
    }
    var it=document.getElementById("courestable");
    var rows=it.rows;
    while(rows.length>1){
        rows[1].parentNode.removeChild(rows[1]);
    }
    page=page-1;
    if(page < 0)
        page+=50;
    $.ajax({
        type: "GET",
        url: "/ajax?action=allcourses&page="+page+"&username="+username,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var t=document.getElementById("courestable");
            t.rows[0].cells[1].innerHTML="添加";
            var it=$("table");
            var strs=msg.split("\n");
            for(var i=0; i<strs.length-1; i++){
                var str=strs[i].split(";");
                coursIdBuffer[str[0]]=str[1];
                if(courseAndScoure[strs[i]] == null){
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default' value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
                else{
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default' disabled value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
            }
        }
    });
};
function next(){
    for(var k in coursIdBuffer){
        delete (coursIdBuffer[k]);
    }
    var it=document.getElementById("courestable");
    var rows=it.rows;
    while(rows.length>1){
        rows[1].parentNode.removeChild(rows[1]);
    }
    page=page+1;
    if(page > 50)
        page-=50;
    $.ajax({
        type: "GET",
        url: "/ajax?action=allcourses&page="+page+"&username="+username,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var t=document.getElementById("courestable");
            t.rows[0].cells[1].innerHTML="添加";
            var it=$("table");
            var strs=msg.split("\n");
            for(var i=0; i<strs.length-1; i++){
                var str=strs[i].split(";");
                coursIdBuffer[str[0]]=str[1];
                if(courseAndScoure[strs[i]] == null){
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default' value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
                else{
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default' disabled value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
            }
        }
    });
};
function load() {
    username=document.getElementById("username").innerHTML;

    $.ajax({
        type: "GET",
        url: "/ajax?action=mycourse&page=0&username="+username,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var strs=msg.split("\n");
            for(var i=0; i < strs.length-1;i++){
                var s=strs[i].split(";");
                courseAndScoure[s[0]]=s[1];
                courseIdMap[s[0]]=s[2];
            }
            reload();
            loadStep();
        }
    });

};
function reload(){
    for(var k in coursIdBuffer){
        delete (coursIdBuffer[k]);
    }
    var it=document.getElementById("courestable");
    var rec=document.getElementById("reccourse");
    it.rows[0].cells[0].innerHTML="课程名称";
    rec.setAttribute("class","");
    var rows=it.rows;
    rows[0].cells[1].innerHTML="成绩";
    document.getElementById("pages").style.display="none";
    if(rows[0].cells.length > 2)
        rows[0].cells[2].parentNode.removeChild(rows[0].cells[2]);
    while(rows.length>1){
        rows[1].parentNode.removeChild(rows[1]);
    }
    var it=$("table");
    for(var k in courseAndScoure){
        it.append("<tr><td style='text-align: center;'>"+k+"</td> <td style='text-align: center;width: 60px;'>" +
        " <input type='text' value='"+courseAndScoure[k]+"' style='text-align:center;width: 60px;'  ondblclick='editScoure(this)' onblur='editover(this)'" +
        " readOnly='true'> </td>");
    }
}

function addCourse(){
    if(editing){
        cancelEdit();
    }
    document.getElementById("pages").style.display="";
    var it=document.getElementById("courestable");
    it.parentNode.childNodes[3].innerHTML="全部课程";
    it.rows[0].cells[0].innerHTML="课程名称";
    var rows=it.rows;
    while(rows.length>1){
        rows[1].parentNode.removeChild(rows[1]);
    }
    $.ajax({
        type: "GET",
        url: "/ajax?action=allcourses&page=0&username="+username,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var t=document.getElementById("courestable");
            t.rows[0].cells[1].innerHTML="添加";
            var it=$("table");
            var strs=msg.split("\n");
            for(var i=0; i<strs.length-1; i++){
                var str=strs[i].split(";");
                coursIdBuffer[str[0]]=str[1];
                if(courseAndScoure[strs[i]] == null){
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default'  value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
                else{
                    it.append("<tr><td style='text-align: center;'>"+str[0]+"</td> <td style='text-align: center;width: 60px;'>" +
                    " <input type='button' class='btn btn-default' disabled value='添加' style='text-align:center;width: 60px;'  onclick='addAcourse(this)'" +
                    " readOnly='true'> </td>");
                }
            }
            //alert("Data Saved: " + msg);
        }
    });
    var my=document.getElementById("mycourse");
    var add=document.getElementById("addcourse");
    var rec=document.getElementById("reccourse");
    rec.setAttribute("class","");
    my.setAttribute("class","");
    add.setAttribute("class","active");
};
function addAcourse(o){
    courseAndScoure[o.parentNode.parentNode.childNodes[0].childNodes[0].data]=60;
    courseIdMap[o.parentNode.parentNode.childNodes[0].childNodes[0].data]=coursIdBuffer[o.parentNode.parentNode.childNodes[0].childNodes[0].data];
    o.disabled=true;
    loadStep();
}
function editScoure(o){
    if(editing)
        o.readOnly=false;
}
function editover(o){
    o.readOnly=true;
    courseAndScoure[o.parentNode.parentNode.childNodes[0].childNodes[0].data]= o.value;
}
function deleteline(o){

    var obj=o.parentNode.parentNode;
    delete (courseAndScoure[obj.childNodes[0].childNodes[0].data]);
    delete (courseIdMap[obj.childNodes[0].childNodes[0].data]);
    obj.parentNode.removeChild(obj);
    loadStep();

}
function editCourse(){
    cancelEdit();
    if(!editing){
        var it=$("table tr")
        var obj=it.eq(0);
        obj.append("<td id='td0' class='warning' style='"+"text-align: center;width:60px;'"+">删除<"+"/td>");
        for(var i=1; i < it.length;i++){
            var obj=it.eq(i);
            obj.append("<td id='td"+i+"'style='"+"text-align: center;width:60px;'"+"><a href='#' onclick='deleteline(this)' class='"+"glyphicon glyphicon-remove'"+"><"+"/a><"+"/td>");
        }
        editing=true;
        var my=document.getElementById("mycourse");
        var edit=document.getElementById("editcourse");
        var add=document.getElementById("addcourse");
        my.setAttribute("class","");
        edit.setAttribute("class","active");
        add.setAttribute("class","");
    }
}
function cancelEdit(){
    reload();
    editing=false;
    var it=document.getElementById("courestable");
    it.parentNode.childNodes[3].innerHTML="我的课程";
    var my=document.getElementById("mycourse");
    var edit=document.getElementById("editcourse");
    var add=document.getElementById("addcourse");
    my.setAttribute("class","active");
    add.setAttribute("class","");
    edit.setAttribute("class","");

}
function recCourse(){
    var my=document.getElementById("mycourse");
    var rec=document.getElementById("reccourse");
    var edit=document.getElementById("editcourse");
    var add=document.getElementById("addcourse");
    my.setAttribute("class","");
    add.setAttribute("class","");
    edit.setAttribute("class","");
    rec.setAttribute("class","active");
    document.getElementById("pages").style.display="none";
    var it=document.getElementById("courestable");
    var rows=it.rows;
    while(rows.length > 1){
        rows[1].parentNode.removeChild(rows[1]);
    }
    if(rows[0].cells.length > 2)
        rows[0].cells[2].parentNode.removeChild(rows[0].cells[2]);
    var courses="";
    for(var k in courseIdMap){
        courses+=courseIdMap[k]+";"+courseAndScoure[k]+":";
    }
    $.ajax({
        type: "POST",
        url: "/ajax?courses="+courses,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var t=document.getElementById("courestable");
            t.rows[0].cells[1].innerHTML="基于相似度的推荐";
            t.rows[0].cells[1].style.width="";
            t.rows[0].cells[0].innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;简单推荐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            var it=$("table");
            var strs=msg.split(":");
            var str1=strs[0].split(";");
            var str2=strs[1].split(";");
            if(str1.length < str2.length)
                for(var i=str1.length; i < str2.length;i++)
                    str1[i]="";
            for(var i=0; i<str1.length-1 || i < str2.length-1; i++){

                it.append("<tr><td style='text-align: center;'>"+str1[i]+"</td>"  +
                "<td style='text-align: center;'>"+str2[i]+"</td></tr>");
            }

        }
    });
}
function loadStep(){
    var courses="";
    for(var k in courseIdMap){
        courses+=courseIdMap[k]+";"+courseAndScoure[k]+":";
    }

    $.ajax({
        type: "POST",
        url: "/distance?courses="+courses,
        contentType: "text/plain; charset=utf-8",
        data: "",
        success: function (msg) {
            var strs=msg.split(":");
            for(var i=1; i <=5; i++){
                document.getElementById("step"+i).innerHTML="第"+i+"步找到"+strs[i-1]+"人"
            }
            var num=237;
            for(var i=0; i < 5; i++){
                num-=parseInt(strs[i]);
            }
            document.getElementById("step"+6).innerHTML="还有"+num+"人没找到";
        }
    });
}