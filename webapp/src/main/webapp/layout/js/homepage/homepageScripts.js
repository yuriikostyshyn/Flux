function showPage(pagePath) {
	location.replace(pagePath);
	}
function extendTransaction(rowId, imageId){
	
	STYLE_ATTRIBUTE_NAME = "style";
	IMAGE_SOURCE_ATTRIBUTE_NAME = "src";
	VISIBILITY_VISIBLE_STYLE_VALUE = "visibility: visible;";
	VISIBILITY_COLLAPSE_STYLE_VALUE = "visibility: collapse;";
	NARROW_BUTTON_IMAGE_PATH = "layout/images/narrow_button.png";
	EXTEND_BUTTON_IMAGE_PATH = "layout/images/extend_button.png";
	
	row = document.getElementById(rowId);
	image = document.getElementById(imageId);
	
	if(row.getAttribute(STYLE_ATTRIBUTE_NAME) == VISIBILITY_COLLAPSE_STYLE_VALUE){
		row.setAttribute(STYLE_ATTRIBUTE_NAME,VISIBILITY_VISIBLE_STYLE_VALUE);
		image.setAttribute(IMAGE_SOURCE_ATTRIBUTE_NAME, NARROW_BUTTON_IMAGE_PATH);
	}else {	
		row.setAttribute(STYLE_ATTRIBUTE_NAME,VISIBILITY_COLLAPSE_STYLE_VALUE);
		image.setAttribute(IMAGE_SOURCE_ATTRIBUTE_NAME, EXTEND_BUTTON_IMAGE_PATH);
		}
	}