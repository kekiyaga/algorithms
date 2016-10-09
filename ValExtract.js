var infoObj = 
{
	"prop1": 
	{
		"param1":"values",
		"param2":"value2",
		"param3":"value3"
	},
	"prop2": 
	{
		"param1":"have",
		"param2":"value2",
		"param3":"value3"
	},
	"prop3": 
	{
		"param1":"been",
		"param2":"value2",
		"param3":"value3"
	},
	"prop4": 
	{
		"param1":"extracted",
		"param2":"value2",
		"param3":"value3"
	}
}

for (var property in infoObj) {
	alert(infoObj[property]["param1"]);
}