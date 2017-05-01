
var page = "";
console.log("hoi");
function userNameExist(){
    var elementUserExist = document.getElementById('detailForm:userNameExist');
    elementUserExist.style.display = 'block';

    disabledBtnAdjust(elementUserExist);
}

function next() {
    if(page == ''){
        document.getElementById('detailForm:detail').style.display = 'none';
        document.getElementById('detailForm:detailAdjust').style.display = 'block';
        document.getElementById('detailForm:usernameAdjustPnl').style.display = 'none';
        document.getElementById('btnCancel').style.display = 'block';
        document.getElementById('btnAdjust').value = 'Aanpassingen verwerken';

        page = "details";
    }else if(page == 'details'){
        document.getElementById('detailForm:detailAdjust').style.display = 'none';
        document.getElementById('detailForm:usernameAdjustPnl').style.display = 'block';
        document.getElementById('btnAdjust').value = 'Username aanpassen';
        document.getElementById('btnAdjust').disabled = true;
        document.getElementById('btnCancel').value = 'Username zo laten';

        page = 'username';
    }else if(page == 'username'){
        var elementUserExist = document.getElementById('detailForm:userNameExist');
        disabledBtnAdjust(elementUserExist);

        back();

        page = "";
    }
}

function back(){
    document.getElementById('detailForm:detailAdjust').style.display = 'none';
    document.getElementById('detailForm:detail').style.display = 'block';
    document.getElementById('detailForm:usernameAdjustPnl').style.display = 'none';

    document.getElementById('btnAdjust').value = 'Bewerken';
    document.getElementById('btnAdjust').style.display = 'block';
    document.getElementById('btnCancel').style.display = 'none';

    page = "";
}

function disabledBtnAdjust(element){
    var btn = document.getElementById('btnAdjust');
    if(element.innerHTML == 'Username bestaat al!'){
        if(btn != 'undefined'){
            btn.disabled = true;
        }
    }else{
        if(btn != 'undefined'){
            btn.disabled = false;
        }
    }
}
