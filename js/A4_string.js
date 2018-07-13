/**
 * Created by mayn on 2018/3/17.
 */
var str = "Hello world!";

str.startsWith("Hello") // true
str.endsWith("!") // true
str.includes("o") // true

str.startsWith("world", 6) // true
str.endsWith("Hello", 5) // true
console.log(str.includes("Hello", 6)) // false

//repeat()返回一个新字符串，表示将原字符串重复n次
var str = "x";
str.repeat(3) // "xxx"
var str1 = "hello";
str1.repeat(2) // "hellohello"

//模板字符中，支持字符串插值
let first = 'hubwiz';
let last = '汇智网';
document.write(`Hello ${first} ${last}!`);
//模板字符串可以包含多行：
let multiLine = `
    This is
    a string
    with multiple
    lines`;
document.write(multiLine);

//标签模板
var a = 5;
var b = 10;
tag`Hello ${ a + b } world ${ a * b }`;
//下面是tag函数的一种写法，以及运行结果。
var a = 5;
var b = 10;
function tag(s, v1, v2) {
    document.write(s[0]);
    document.write(s[1]);
    document.write(v1);
    document.write(v2);
    return "OK";
}
tag`Hello ${ a + b } world ${ a * b}`

//若使用String.raw 作为模板字符串的前缀，则模板字符串可以是原始(raw)的。反斜线也不再是特殊字符，\n 也不会被解释成换行符：
let raw = String.raw`Not a newline: \n`;
document.write(raw === 'Not a newline: \\n'); // true

