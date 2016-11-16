var array = [];
var scoDivArr = [];
var sortArr = [];

console.log("randomly generated array containing a movie, array index, average score, # of reviewers, and scoreDiv");
console.log("columns: movie name, index, average score, # of reviews, scoreDiv");
console.log("");

for (var i = 0; i < 5; i++) {
	var score = Math.random() * 100;
	var sum = Math.pow(3, Math.random() * 10) | 0;
	var scoreDiv = (sum/(1/score)) / (1/score);
	array.push(["movie" + (i + 1).toString(), i, score, sum, scoreDiv]);
	
	scoDivArr.splice(i, 0, array[i][4]);
	scoDivArr.sort(function(a, b) {
  		return a - b;
	});

	console.log(array[i]);
}

for (var x = 0; x <= scoDivArr.length + 1; x++) {
	for (var y = 0; y < scoDivArr.length; y++) {
		if (array[y][4] == scoDivArr[scoDivArr.length - x] && array[y][4] != scoDivArr[scoDivArr.length - x + 1]) {
			sortArr.push(array[y]);
		}
	}	
}

console.log("");
console.log("sorted in ascending order based on right-most column (column 5 or array[#][4])");
console.log("columns: movie name, index, average score, # of reviews, scoreDiv");
console.log("");

for (var z = 0; z < sortArr.length; z++) {
	console.log(sortArr[z]);
}