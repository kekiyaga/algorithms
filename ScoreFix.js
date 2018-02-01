function scoreFix (score, scale, sum) {
	
	var scoreType = typeof score == "number";
	var scaleType = typeof scale == "number";
	var sumType = typeof sum == "number";
	
	var allNumBool = scoreType && scaleType && sumType;
	var scoreGTScale = score > scale;
	var argSumBool = (score == undefined) || (sum == undefined) || (scale == undefined);
	
	if (argSumBool) {
		return "You have entered too few arguments.  Enter number three arguments.  ";
	} else if (allNumBool == false && argSumBool == false) {
		return "You have entered a non-number value.  Enter number values only.  "
	} else if (scoreGTScale) {
		return "Your score exceeds your scale.  Try again.  ";
	} else {
		for (var a = 0; a < 5; a++) {
			var scale = scale;
			var score = score;
			var sumNum = sum;
			
			var scaleHund = (100/scale);
			
			var scoreHund = score * scaleHund;
			var scaleHund = scale * scaleHund;
			
			var prcnt = (scoreHund / scaleHund) * 100;
			
			var scoreDiv = prcnt * sumNum;
			
			return prcnt + " | " + sumNum + " | " + scoreDiv;
		}
	}
}

console.log(scoreFix(4,5,33));
console.log(scoreFix(4,"k",33));
console.log(scoreFix(4,33));
console.log(scoreFix(4,3,33));