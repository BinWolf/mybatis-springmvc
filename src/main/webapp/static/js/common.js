/**
 * Created by Seven on 15/7/14.
 */
/**
 *
 * @param pro_id
 * @param proName
 * @param city_id
 * @param city_name
 * @param useAlias
 */
function provinceOnChange(pro_id, proName, city_id, city_name,useAlias) {
    var pro_idVal = $(pro_id).val();
    if (pro_idVal == "") {
        $(city_id).html("<option value=''>-选择-</option>");
        $(city_name).val("");
        return;
    }
    var proNameVal = $(pro_id).find("option:selected").text();
    $(proName).val(proNameVal);
    var data1 = {
        type: "1",
        pro_id: pro_idVal
    };
    $.ajax({
        url: "common/getAreaList",
        type: "post",
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        data: data1,
        success: function (data) {
            var city = "<option value=''>-选择-</option>";
            for (var i = 0; i < data.length; i++) {
                if(useAlias){
                    city = city + "<option value='" + data[i].area_id + "'>" + data[i].area_alia + "</option>";
                }else{
                    city = city + "<option value='" + data[i].area_id + "'>" + data[i].area_name + "</option>";

                }
            }

            $(city_id).html(city);

            //通过cit_name字段的值 回写城市选中
            if ($(city_name).val() != "") {
                $(city_id).find("option:contains('" + $(city_name).val() + "')").attr("selected", "selected");
            }else{
                $(city_id).find("option:first").attr("selected", "selected");
            }
            cityOnChange(city_id, city_name);
        },
        error: function (e) {
            alert("出错");
        }
    });
}
function selectFromValue(id,name){
    if ($(name).val() != "") {
        $(id).find("option:contains('" + $(name).val() + "')").attr("selected", "selected");
    }
}
/**
 * city列表变化给cityname字段赋值
 * @param city_id
 * @param city_name
 */
function cityOnChange(city_id,city_name){
    if ($(city_id).val() == "") {
        $(city_name).val("");
        return;
    }
    var cityName = $(city_id).find("option:selected").text();
    $(city_name).val(cityName);
}


function rotateImg(e,deg){
    $e=$(e);
    if(typeof deg == "undefined")deg = 90;
    var odeg = $e.data("odeg");

    if(typeof odeg == "undefined")odeg = 0;
    adeg = odeg+deg;

    $e.data("odeg",adeg);

    $e.css("webkitTransform","rotate("+adeg+"deg)");
    $e.css("MozTransform","rotate("+adeg+"deg)");
    $e.css("msTransform","rotate("+adeg+"deg)");
    $e.css("OTransform","rotate("+adeg+"deg)");
    $e.css("transform","rotate("+adeg+"deg)");
}