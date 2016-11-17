var array = [];
var scoDivArr = [];
var sortArr = [];

console.log("randomly generated array containing a movie, array index, average score, # of reviewers, and scoreDiv");
console.log("columns: movie name, index, average score, # of reviews, scoreDiv");
console.log("");

for (var i = 0; i < 5; i++) {
	var score = 80 + ((Math.random()/10) * 100);
	var sum = 200 + (Math.random() * 100) | 0;
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

var jsonObj = {};

var add = function(num) {
	jsonObj["item" + (num + 1).toString()] = {
		movie_name: sortArr[num][0],
		index: sortArr[num][1],
		avg_score: sortArr[num][2],
		reviewer_count: sortArr[num][3],
		score_div: sortArr[num][4]
	};
};

for (var z = 0; z < sortArr.length; z++) {
	add(z);
}

for (var num in jsonObj) {
	var movie = jsonObj[num]["movie_name"];
	var scoreObj = jsonObj[num]["avg_score"];
	var sumObj = jsonObj[num]["reviewer_count"];
	
	console.log(movie + " had an average score of " + scoreObj + " from " + sumObj + " reviewers, scoring " + jsonObj[num]["score_div"] + " when weighing the average score and number of reviews the film received.");
};

console.log("");
console.log("sorted JSON object");
console.log("");
console.log(JSON.stringify(jsonArr));