// JavaScript Document
function selectTag(showContent,selfObj){
	var tabs = selfObj.parentNode.parentNode.getElementsByTagName("li");	
	for(var i=0;i<tabs.length;i++)
	{
		tabs[i].className = "";
		if(i== showContent)
		tabs[i].className = "selectTag";
	}
	var options = selfObj.parentNode.parentNode.parentNode.parentNode.children[1];
	
	for(var i=0;i<options.children.length;i++)
	{  
		options.children[i].className = "tagContent";
		if(i== showContent)
		{
			options.children[i].className = "tagContent selectTag";
		}
	}
}



