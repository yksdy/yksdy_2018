
//function Examination() {
//    this.getMaxSum = function(m) {
//        var a = m[0], b = m[1],n = m.length;
//        for (var i = 0; i < n - 2;) {
//            a = a + m[i + 1];
//            aa = a + m[i + 1 + 1];
//            if (a < aa) {
//                a = aa;
//                i = i + 1 + 1;
//            }
//            else
//                i = i + 1;
//        }
//
//        for (var i =1; i < n - 2;) {
//            b = b + m[i + 1 + 1];
//            bb = b + m[i + 1 + 1 + 1];
//            if (b < bb) {
//                b = bb;
//                i = i + 1 + 1 + 1;
//            }
//            else
//                i = i + 1 + 1;
//        }
//        if (a < b)
//            sum = b;
//        else
//            sum = a;
//        return sum;
//    }
//}



function Examination() {
	 this.getMaxSum =	function(arr) {
		  //
		  let result = 0
		  let dp = []
		  let np = []
		  dp[0] = arr[0]
		  np[0] = 0
		  let len = arr.length
		  for(let i = 1; i < len; i++){
		    dp[i] = np[i - 1] + arr[i]
		    np[i] = Math.max(dp[i - 1], np[i - 1])
		  }

		  result = Math.max(dp[len - 1], np[len - 1])

		  return result
		}
}

module.exports = Examination;