/**
 * Created by mayn on 2018/3/15.
 */
// const 声明的是常量，一旦声明，值将是不可变的
const PI = 3.1415;
PI // 3.1415

PI = 3;
PI // 3.1415

const PI = 3.1;
PI // 3.1415


//const 也具有块级作用域
if (true) {
    const max = 5;
}
document.write(max);  // ReferenceError 常量MAX在此处不可得

//const 不能变量提升（必须先声明后使用）
if (true) {
    document.write(MAX); // ReferenceError
    const MAX = 5;
}


// const 不可重复声明
var message = "Hello!";
let age = 25;

// 以下两行都会报错
const message = "Goodbye!";
const age = 30;

//const 指令指向变量所在的地址，所以对该变量进行属性设置是可行的（未改变变量地址），如果想完全不可变化（包括属性），那么可以使用冻结。
const C1 = {};
C1.a = 1;
document.write(C1.a); // 1
C1 = {};  // 报错  重新赋值，地址改变

//冻结对象，此时前面用不用const都是一个效果
const C2 = Object.freeze({});
C2.a = 1; //Error,对象不可扩展
document.write(C2.a);