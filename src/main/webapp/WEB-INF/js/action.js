/**
 * Created by zhaoxin on 14-12-21.
 */
function editCourse(){
    var it=$("table tr")
    for(var i=0; i < it.length;i++){
        var obj=it.eq(i);
        obj.append("<td style='"+"text-align: center;'"+">abc<"+"/td>");
    }
}