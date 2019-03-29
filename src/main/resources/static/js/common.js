function addCategory(id) {
    var amount = $('#' + id).val();
    if (amount == null || 0) {
        amount = 1;
    }
    var JSONObject = {
        "categoryId": id,
        "quantity": amount
    };
    var jsonData = JSON.stringify(JSONObject);
    $.ajax({
        url: '/category',
        method: 'post',
        data: jsonData,
        dataType: "text",
        contentType: "application/json",
        success: function (data) {
            if (data == "success") {
                alert("카테고리가 추가되었습니다");
            } else if (data == "fail") {
                alert("오류가 발생했습니다.");
            }
        },
        error: function (data) {
            alert("통신실패. 다시 시도해주시길 바랍니다.");
        },
        timeout: 3000
    });
}

function tagKeyUp(key,value){
    var keyword = $("#"+value.id).val().replace(",","");
    var tagId = $("#"+value.id);

    if(key.keyCode == 188 || key.keyCode == 32 || key.keyCode == 13){
        if(keyword.length > 2) {
            tagId.before("<p class='d-inline-block p-2 border'>"+keyword+'<span type="button" class="close tag-close" onclick="javascript:removeTagItem(this)">x</span></p>');
            tagId.val('');
        }
    }
}

function removeTagItem(value){
    $(value).closest('p').remove();
    $(value).remove();
}

function tagKeyDown(key,value){
    var keyword = $("#"+value.id).val().replace(",","");
    var tagId = $("#"+value.id);
    if(key.keyCode == 8 && (keyword == '' || keyword == null || keyword == undefined)){
        tagId.prev().remove();
    }
}

function showTagModal(id){
    var cnt = $("#tagModal"+id).length;
    //console.log(cnt);
    if(cnt == 0) {
        $(".container").append(
            '<div class="modal fade" id="tagModal' + id + '" role="dialog">' +
                '<div class="modal-dialog" id="tagModal-dialog' + id + '">' +
                    '<div class="modal-content">' +
                        '<div class="modal-body">' +
                            '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
                                '<h5 class="title">태그편집</h5>' +
                                '<div class="form-group">' +
                                    '<ul class="edit-tag-list w-75">' +
                                        '<li class="token-input-input-token w-100" id="addTagItem'+id+'">' +
                                            '<input type="text" onkeydown="tagKeyDown(event,this)" onkeyup="tagKeyUp(event,this)" class="border-0" style="outline:none;" id="tagText' + id + '" name="tagName">' +
                                        '</li>' +
                                    '</ul>' +
                                    '<button type="button" onclick="javascript:addTagItem(' + id + ')" class="btn btn-primary">저장</button>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>'
        );
    }
    $('#tagModal'+id).modal('show');
}

function findLinksByTag(id){
    $('ul > li').removeClass('active');
    $('#tag'+id).addClass('active');

    $('.card-list').remove();

    var tagName = $('#tag'+id).data('name');
    var categoryId = $('#selectCategory option:selected').val();

    var JSONObject= {
        "tagName" : tagName,
        "categoryId" : categoryId
    };
    var jsonData = JSON.stringify( JSONObject );

    $.ajax({
        url : '/api/tag/search',
        method : 'post',
        data : jsonData,
        dataType: "text",
        contentType: "application/json",
        success : function (data) {
            var jsonData = JSON.parse(data);
            console.log(jsonData);
            for(var i=0; i<jsonData.length; i++) {
                $("#card-row").append(
                    '<div class="col-md-4 mb-4 card-list">' +
                        '<div class="card mb-4 shadow-sm links">'+
                           '<svg class="bd-placeholder-img card-img-top" width="100%" height="225">' +
                                '<title>Placeholder</title>'+
                                '<rect width="100%" height="100%" fill="#55595c"></rect>'+
                                '<text x="36.5%" y="50%" fill="#eceeef" dy=".3em">Thumnail</text>'+
                           '</svg>'+
                           '<div class="card-body p-2">'+
                              '<p class="card-title m-2 d-flex">'+
                              '<a href="'+jsonData[i].url+'" class="link-title">'+jsonData[i].title+'</a>'+
                              '<p class="m-2" style="font-size:80%;">'+jsonData[i].regDate.substring(0,10)+'</p>'+
                              '</p>'+
                           '</div>'+
                           '<div class="card-body p-3" id="card-tag'+jsonData[i].id+'">'+
                           '</div>'+
                           '<div class="card-body p-2">'+
                               '<ul class="nav nav-pills nav-justified">'+
                                  '<li class="nav-item cursor-pointer" onclick="showTagModal('+jsonData[i].id+')">'+
                                    '<i class="fas fa-tag"></i>'+
                                  '</li>'+
                                  '<li class="nav-item cursor-pointer" data-toggle="modal"'+
                                  'data-target="#modal'+jsonData[i].id+'">'+
                                    '<i class="fas fa-pencil-alt"></i>'+
                                  '</li>'+
                                  '<li class="nav-item cursor-pointer" data-toggle="modal"'+
                                  'data-target="#deleteModal'+jsonData[i].id+'">'+
                                  '<i class="fas fa-trash-alt"></i>'+
                                  '</li>'+
                               '</ul>'+
                           '</div>'+
                        '</div>'+
                    '</div>')
                    for(var j=0; j<jsonData[i].tags.length; j++){
                        $("#card-tag"+jsonData[i].id).append(
                            '<input type="button" class="btn btn-outline-dark btn-sm" value="' + jsonData[i].tags[j].tagName + '">'
                        )
                    }
            }
        },
        error : function (data) {
            alert("통신실패. 다시 시도해주시길 바랍니다.");
        },
        timeout: 3000
    });
}

function addTagItem(id){
    var tags = new Array();
    $("#addTagItem"+id+" p").each(function(){
        tags.push($(this).text().replace("x",""));
    });
    var JSONObject = {
        "id": id,
        "tags": tags
    };
    var jsonData = JSON.stringify(JSONObject);
    $.ajax({
        url: '/api/tag/add',
        method: 'post',
        data: jsonData,
        dataType: "text",
        contentType: "application/json",
        success: function (data) {
            if (data == "success") {
                alert("태그추가 성공!!");
            } else if(data == "false"){
                alert("태그추가 실패!!");
            }
        },
        error: function (data) {
            alert("통신실패. 다시 시도해주시길 바랍니다.");
        },
        timeout: 3000
    });
}

function moveUrl(url){
    if(url == 1) {
        document.location.href = "/cart/list";
    } else if(url == 2){
        document.location.href="/main";
    }
}

function showModal(option){
    var id = option;
    $('.category').remove();
    $.ajax({
        url : '/api/account/category/'+id,
        method : 'post',
        dataType: "text",
        success : function (data) {
            var jsonData = JSON.parse(data);
            for(var i=0; i<jsonData.length; i++) {
                $("#categorySelect").append('<option class="category" value=' + jsonData[i].id + '>' + jsonData[i].name + '</option>');
                //console.log(jsonData[0]);
            }
            $('#urlModal').modal('show')
        },
        error : function (data) {
            alert("통신실패. 다시 시도해주시길 바랍니다.");
        },
        timeout: 3000
    });
}

function selectChange(){
    var id = $('#selectCategory option:selected').val();
    document.location.href="/link/view/"+id;
}

function showSearch(){
    var search = $("#search").hasClass('d-none');
    var value = $("#search").val();

    if(search){
        $("#search").addClass("d-block");
        $("#search").removeClass("d-none");
    } else if(value == null || value == "" || value.length == 0){
        $("#search").addClass("d-none");
        $("#search").removeClass("d-block");
    } else {
        $("#search-btn").attr("type","submit");
    }
}

function openZipCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            if(data.jibunAddress !== ''){
                roadAddr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
        }
    }).open();
}

function checkEmail(){
    var id = $("#email").val();
    if(id.length == 0 || id == null){
        alert("이메일을 2글자 이상 입력해주세요")
        return;
    }
    $.ajax({
        url : '/api/account/checkEmail/'+id,
        method : 'post',
        dataType: "text",
        success : function (data) {
            if(data == "success"){
                $("#check-id").attr('class','btn btn-success');
                alert("중복되는 아이디가 없습니다");
            } else if(data == "duplicate"){
                $("#check-id").attr('class','btn btn-danger');
                alert("중복되는 아이디가 있습니다");
            } else {

            }
        },
        error : function (data) {
            alert("통신실패. 다시 시도해주시길 바랍니다.");
        },
        timeout: 3000
    });
}

