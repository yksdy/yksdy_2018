/**
 * Created by mayn on 2018/3/15.
 */
 var a = [];
for (var i = 0; i < 10; i++) {
 //   console.log(i);
    if(i>4){
        a[2]();
    }
    a[i] = function () {
        console.log(i);
    };
}
a[6](); // 10

var a = [];
for (var i = 0; i < 10; i++) {
    a[i] =i;
}
console.log(a[6]); // 6

var a = [];
for (let i = 0; i < 10; i++) {
    a[i] = function () {
        console.log(i);
    };
}
a[6](); // 6

var a = [];
for (let i = 0; i < 10; i++) {
    a[i] =i;
}
console.log(a[6]); // 6

