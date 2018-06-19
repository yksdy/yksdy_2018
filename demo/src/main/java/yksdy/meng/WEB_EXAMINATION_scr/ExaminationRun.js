var Examination = require('./Examination');
var ExaminationAnswer = require("./ExaminationAnswer")
var e = new Examination();
var a = new ExaminationAnswer();

function test(data){

    var ev = e.getMaxSum(data);
    var av = a.getMaxSum(data);
    if (ev != av){
        console.log("错误! 期望"+av+", 实际"+ev + ", 测试数据:" + JSON.stringify(data) );
    } else {
        console.log("正确! 期望"+av+", 实际"+ev +", 测试数据:" + JSON.stringify(data) );
    }
}

test([ 1, 6, 9,6]);
test([ 1, 9, 1,3]);
test([ 1, 9, 1,3, 6, 8,1,1 ]);
test([ 1, 9, 2,3, 6, 8,9,1 ]);
test([ 1, 9, 8,100, 100, 9,1,0 ]);
test([ 1, 9, 8,100, 100, 9,1, 500,490,0,236 ]);
//test([1,9,8,1,2]);
//test([2,9,8,1,1]);