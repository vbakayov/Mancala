//global vars
var sequence_pit=1;
var player_one_pits= document.querySelectorAll('.row.player-one .pit');
var player_two_pits= document.querySelectorAll('.row.player-two .pit');
var stores= document.querySelectorAll('.store');
var playerTurn="playerOne"


var onPitClick = function() {

    var pit = this.getAttribute("sequence");
    if(this.innerHTML != "0") //pit with zero seeds
        ajaxCall(pit,playerTurn);

};

var playerTurnSwitch= function (playerOneAgain, playerTwoAgain) {

    if(playerTurn == "playerOne" && !(playerOneAgain === 'true')){
        switchEventListener("playerTwo");
        playerTurn="playerTwo";
        switchCoursor(playerTurn);
    }
    else if(playerTurn == "playerTwo" && !(playerTwoAgain === 'true')){
        switchEventListener("playerOne");
        playerTurn="playerOne";}
        switchCoursor(playerTurn);


};

function ajaxCall(positionClicked, playerTurn) {
    $.post('makemove', { position: positionClicked, player : playerTurn},
        function(returnedData){

            returnedData = JSON.parse(returnedData);
            stores[1].innerHTML= returnedData[1];
            stores[0].innerHTML= returnedData[3];

            var player1 = returnedData[0];
            for (var i = 0; i < player1.length; i++) {
                player_one_pits[i].innerHTML=returnedData[0][i];
                player_two_pits[i].innerHTML=returnedData[2][i];

            }

            var playersTurn =returnedData[4];
            playerTurnSwitch(playersTurn.player1Again,playersTurn.player2Again);

            var endGame =returnedData[5];
            if(endGame.endGame == "1"){
                alert("Game Finished; Thank you for playing");
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

function switchCoursor (playerTurn){
    for(var i =0; i<player_two_pits.length;i++){
        if(playerTurn == "playerTwo"){
            player_two_pits[i].style.cursor="pointer";
            player_one_pits[i].style.cursor="cell";
            document.querySelector('.current-player').textContent = "two";
        }
        else{
            player_one_pits[i].style.cursor="pointer";
            player_two_pits[i].style.cursor="cell";
            document.querySelector('.current-player').textContent = "one";
        }
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
