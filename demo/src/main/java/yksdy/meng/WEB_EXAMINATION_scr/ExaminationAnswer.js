
function ExaminationAnswer() {
    this.getMaxSum = function(m) {
        return getMaxSumValue(m,m.length-1);
    }

    function getMaxSumValue(m,n){
        if(n == 0){
            return m[0];
        } else if (n==1){
            return Math.max(m[0],m[1]);
        } else {
            return Math.max(getMaxSumValue(m,n-2)+ m[n],getMaxSumValue(m,n-1));
        }
    }

}

//function ExaminationAnswer() {
//this.getMaxSum = function(arr) {
//	    let temp
//	    let len = arr.length
//	    let index = []
//	    for (let i = 0; i < len; i++) {
//	        index[i] = i
//	    }
//
//	    for (let i = 0; i < len; i++) {
//	        for (let j = 0; j < len - i; j++) {
//	            if (arr[j] < arr[j + 1]) {
//	                temp = arr[j]
//	                arr[j] = arr[j + 1]
//	                arr[j + 1] = temp
//	                temp = index[j]
//	                index[j] = index[j + 1]
//	                index[j + 1] = temp
//	            }
//	        }
//	    }
//	    function isexist(ind, exist) {
//	        for (let i = 0; i < exist.length; i++) {
//	            if (Math.abs(ind - exist[i]) <= 1) {
//	                return 1
//	            }
//	        }
//	        return 0
//	    }
//
//	    let exist = []
//	    let num = []
//	    let sum = 0
//	    sum += arr[0]
//	    num.push(arr[0])
//	    exist.push(index[0])
//	    for (let i = 1; i < len; i++) {
//	        if (isexist(index[i], exist) === 0) {
//	            sum += arr[i]
//	            num.push(arr[i])
//	            exist.push(index[i])
//	        }
//	    }
//	    return sum
//}
//}

module.exports = ExaminationAnswer;