/**
 * Created by mayn on 2018/3/15.
 */
function f1() {
    var a = 8;
    var b = 9;
    let n = 5;
    if (true) {
        let n = 10;
        let b = 18;
        var a = 20
    }

    console.log(a); // 20
    console.log(b); // 9
    console.log(n); // 5
}
f1();