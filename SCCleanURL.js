var scURL = "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/322221206&color=f2346d&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false"

var regEx = /\/\/api.soundcloud.com\//;
var scRegEx = /\/\d+\W/;
var endChar = /\W$/;
var cleanSCURL;

var splitStr = scRegEx.exec(scURL.replace(endChar, ""));
var scSplit = scURL.split(scRegEx);
var scLink = scSplit[0] + splitStr;

cleanSCURL = scLink;

console.log(cleanSCURL);