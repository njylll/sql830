function f1(n){
    $(".main").attr("hidden","true")
    console.log(n)
    switch (n) {
        case 0:
            $("#d0").removeAttr("hidden");
            $('#d0').attr('src', $('#d0').attr('src'));
            break;
        case 1:
            $("#d1").removeAttr("hidden");
            $('#d1').attr('src', $('#d1').attr('src'));
            break;
        case 2:
            $("#d2").removeAttr("hidden");
            $('#d2').attr('src', $('#d2').attr('src'));
            break;
        case 3:
            $("#d3").removeAttr("hidden");
            $('#d3').attr('src', $('#d3').attr('src'));
            break;
        case 4:
            $("#d4").removeAttr("hidden");
            $('#d4').attr('src', $('#d4').attr('src'));
            break;
        case 5:
            $("#d5").removeAttr("hidden");
            $('#d5').attr('src', $('#d5').attr('src'));
            break;
        case 6:
            $("#d6").removeAttr("hidden");
            $('#d6').attr('src', $('#d6').attr('src'));
            break;
        case 7:
            $("#d7").removeAttr("hidden");
            $('#d7').attr('src', $('#d7').attr('src'));
            break;
        case 8:
            $("#d8").removeAttr("hidden");
            $('#d8').attr('src', $('#d8').attr('src'));
            break;
        case 9:
            $("#d9").removeAttr("hidden");
            $('#d9').attr('src', $('#d9').attr('src'));
            break;
    }

}
