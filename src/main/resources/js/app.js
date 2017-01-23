//global vars
var sequence_pit=1;
var player_one_pits= document.querySelectorAll('.row.player-one .pit');
var player_two_pits= document.querySelectorAll('.row.player-two .pit');
var stores= document.querySelectorAll('.store');
var playerTurn="playerOne"

var onPitClick = function() {
    var attribute = this.getAttribute("sequence");
    ajaxCall(attribute,playerTurn);
    if(playerTurn == "playerOne"){
        switchEventListener("playerTwo");
        playerTurn="playerTwo"
    }else if(playerTurn == "playerTwo"){
        switchEventListener("playerOne");
        playerTurn="playerOne"
    }

};


function ajaxCall(positionClicked, playerTurn) {
    $.post('makemove', { position: positionClicked, player : playerTurn},
        function(returnedData){
            console.log(returnedData);
            returnedData = JSON.parse(returnedData);
            stores[1].innerHTML= returnedData[1];
            stores[0].innerHTML= returnedData[3];
            var player1 = returnedData[0];
            for (var i = 0; i < player1.length; i++) {
                var value = player1[i];
                player_one_pits[i].innerHTML=returnedData[0][i];
                player_two_pits[i].innerHTML=returnedData[2][i];
                console.log(value)
            }

        });
}
function init(classname) {
    for (var i = 0; i < classname.length; i++) {
        classname[i].setAttribute("sequence", sequence_pit.toString());
        if(sequence_pit <6) {classname[i].addEventListener('click', onPitClick, false);}
        sequence_pit++;
    }

}

function switchEventListener(playerTurn) {
    if(playerTurn== "playerOne"){
        for (var i = 0; i < player_two_pits.length; i++) {
            player_two_pits[i].removeEventListener('click', onPitClick);
            player_one_pits[i].addEventListener('click', onPitClick);

        }
    }
    if(playerTurn== "playerTwo"){
        for (var i = 0; i < player_one_pits.length; i++) {
            player_one_pits[i].removeEventListener('click', onPitClick);
            player_two_pits[i].addEventListener('click', onPitClick);

        }

    }

}
init(player_one_pits);
init(player_two_pits);
