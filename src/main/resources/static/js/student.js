function f1(n){
    $(".main").attr("hidden","true")
    console.log(n)
    switch (n) {
        case 0:
            $("#d0").removeAttr("hidden");
            break;
        case 1:
            $("#d1").removeAttr("hidden");
            break;
        case 2:
            $("#d2").removeAttr("hidden");
            break;
        case 3:
            $("#d3").removeAttr("hidden");
            break;
        case 4:
            $("#d4").removeAttr("hidden");
            break;
        case 5:
            $("#d5").removeAttr("hidden");
            break;
        case 6:
            $("#d6").removeAttr("hidden");
            break;
    }

}
