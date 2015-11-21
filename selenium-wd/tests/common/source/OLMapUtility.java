package common.source;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OLMapUtility {

	private static final String CROSSHAIR_WHITE_PNG = "crosshair-white.png";
	private static final String CROSSHAIR_PNG = "crosshair.png";
	private static final String CROSSHAIR_GRAY_PNG = "crosshair-gray.png";

	public enum IconColor {
		Red,
		White,
		Gray
	}

	private static final String IS_ICON_PRESENT_JS_FUNCTION = "function isIconPresent(imgFileName){var found=false;var CAR_ICON_SRC='/content/images/'+imgFileName;"
			+ "try{layers=map.getLayers();if(layers){for(var i=0;i<layers.getLength();i++){layer=layers.item(i);"
			+ "if(layer&&layer.getVisible&&layer.getVisible()){source=layer.getSource();if(source){if(source.getFeatures){features=source.getFeatures();"
			+ "if(features){for(var j=0;j<layers.getLength();j++){if(features[j]&&features[j].getStyle){style=features[j].getStyle();"
			+ "if(style){img=style.getImage();if(img){src=img.getSrc();if(src){if(src.toLowerCase()==CAR_ICON_SRC){found=true;}}}}}}}}}}}}}"
			+ "catch(err){found=false;};return found;};";
	
	private static final String IS_LISAS_PRESENT_JS_FUNCTION = "function isLisasPresent(){var found=false;try{layer=lisaLayer;"
			+ "if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();if(style&&style.getFill&&style.getStroke){"
			+ "fill=style.getFill();stroke=style.getStroke();if(fill&&stroke&&fill.getColor&&stroke.getColor&&stroke.getWidth){"
			+ "fcolor=fill.getColor();scolor=stroke.getColor();swidth=stroke.getWidth();if((fcolor==lisaLayerColor)&&(scolor==lisaLayerOutlineColor)&&("
			+ "swidth==lisaOutlineLineWidth)){found=true;}}}}}catch(err){found=false;};return found;};";
	
	private static final String GET_LISA_COORDINATES_JS_FUNCTION = "function getLisaCoordinates(){var lisaCoord=new Array();"
			+ "try{if(lisaLayer&&lisaLayer.getSource){sources=lisaLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry&&geometry.getCoordinates){lisaCoord.push(geometry.getCoordinates());}}}}}};}catch(err){};return lisaCoord;};";
	
	private static final String IS_BOUNDARIES_PRESENT_JS_FUNCTION = "function isBoundariesPresent(){var found=false;try{layer=boundaryLayer;"
			+ "if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();if(style&&style.getStroke){stroke=style.getStroke();"
			+ "if(stroke&&stroke.getColor&&stroke.getWidth){scolor=stroke.getColor();swidth=stroke.getWidth();"
			+ "if((scolor==boundryColor)&&(swidth==boundryLineWidth)){found=true;}}}}}catch(err){found=false;};return found;};";

	private static final String GET_BOUNDARIES_GEOMETRY_COORDINATES_JS_FUNCTION = "function getBoundariesCoordinates(){var boundariesCoord=new Array();"
			+ "try{if(boundariesLayer&&boundariesLayer.getSource){sources=boundariesLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry&&geometry.getCoordinates){boundariesCoord.push(geometry.getCoordinates());}}}}}};}catch(err){};return boundariesCoord;};";
	
	private static final String IS_ASSETS_PRESENT_JS_FUNCTION = "function isAssetsPresent(){var found=false;try{layer=assetLayer;"
			+ "if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();if(style&&style.getStroke){stroke=style.getStroke();"
			+ "if(stroke&&stroke.getColor&&stroke.getWidth){scolor=stroke.getColor();swidth=stroke.getWidth();"
			+ "if((scolor==assetColor)&&(swidth==assetMainLineWidth)){found=true;}}}}}catch(err){found=false;};return found;};";
	
	private static final String GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION = "function getAssetsCoordinates(){var assetsCoord=new Array();"
			+ "try{if(assetLayer&&assetLayer.getSource){sources=assetLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry&&geometry.getCoordinates){assetsCoord.push(geometry.getCoordinates());}}}}}};}catch(err){};return assetsCoord;}";
	
	private static final String CONCENTRATION_CHART_DATA_FUNCTION = "function isConcentrationChartDataShownOnMap(percentRectHeight,percentWhitePixelsToSeek){"
			+ "var chartIsShown=false;try{cc_ctx=$('#graph_mini')[0].getContext('2d');height=$('#graph_mini').height();width=$('#graph_mini').width();"
			+ "var rectHeight=height*percentRectHeight/100;var imgData=cc_ctx.getImageData(0,height-rectHeight,width,rectHeight);var len=imgData.data.length;"
			+ "var points=len*percentWhitePixelsToSeek/100;for(var i=0;i<len;i++){if(imgData.data[i]==255){points--;"
			+ "if(points<0){chartIsShown=true;}}}}catch(err){chartIsShown=false;};return chartIsShown;}";
	
	private static final String GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION = "function getConcentrationChartImageData(){var imgData;"
			+ "try{cc_ctx=$('#graph_mini')[0].getContext('2d');height=$('#graph_mini').height();width=$('#graph_mini').width();"
			+ "imgData=cc_ctx.getImageData(0,0,width,height).data;}catch(err){imgData=null;};return imgData;}";
	
	private static final String IS_FOV_PRESENT_JS_FUNCTION = "function isFOVPresent(){var found=false;try{layer=fovLayer;"
			+ "if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();if(style&&style.getFill){fill=style.getFill();"
			+ "if(fill.getColor){fcolor=fill.getColor();if((fcolor==fovLayerColor)){found=true;}}}}}catch(err){found=false;};return found;};";	

	private static final String GET_FOV_GEOMETRY_COORDINATES_FUNCTION = "function getFOVCoordinates(){var fovCoord=new Array();"
			+ "if(fovLayer){if(fovLayer.getSource){sources=fovLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry.getCoordinates){coordArray=geometry.getCoordinates();if(coordArray){fovCoord.push(coordArray);}}}}}}}};"
			+ "return fovCoord;};";
	
	private static final String IS_BREADCRUMBS_PRESENT_JS_FUNCTION = "function isBreadCrumbPresent(){var found=false;var fillColorMatch=false;var strokeColorMatch=false;"
			+ "try{layer=breadCrumbLayer;if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();"
			+ "if(style&&style.getStroke&&style.getFill){fill=style.getFill();stroke=style.getStroke();if(fill&&fill.getColor&&stroke&&stroke.getColor&&stroke.getWidth){"
			+ "fcolor=fill.getColor();scolor=stroke.getColor();swidth=stroke.getWidth();breadCrumbModeColors.forEach(function(element,index,array){"
			+ "if(element==fcolor){fillColorMatch=true;};if(element==scolor){strokeColorMatch=true;};});if((fillColorMatch)&&(strokeColorMatch)&&(swidth==breadCrumbLineWidth)){"
			+ "found=true;}}}}}catch(err){found=false;};return found;};";

	private static final String GET_BREADCRUMB_GEOMETRY_COORDINATES_FUNCTION = "function getBreadCrumbCoordinates(){var breadcrumbCoord=new Array();"
			+ "try{if(breadCrumbLayer&&breadCrumbLayer.getSource){sources=breadCrumbLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry&&geometry.getCoordinates){breadcrumbCoord.push(geometry.getCoordinates());}}}}}};}catch(err){};return breadcrumbCoord;};";
	
	private static final String IS_INDICATIONS_PRESENT_JS_FUNCTION = "function isIndicationsShownOnMap(){"
			+ "var isIndicationsSwitchOn=showIndications;var indLinksCount=getIndicationLinksCount();"
			+ "var indNodesCount=getIndicationNodesCount();var isLinksShownOnMap=true;lastConstellation.links.forEach(function(d){"
			+ "if(d.source.type=='indication'){x1=d.source.x;y1=d.source.y;x2=d.target.x;y2=d.target.y;if(!x1||!y1||!x2||!y2){"
			+ "isLinksShownOnMap=false;}}});var isNodesShownOnMap=true;lastConstellation.nodes.forEach(function(d){if(d.type=='indication'){"
			+ "if(!d.lat||!d.lon){isNodesShownOnMap=false;}}});return isIndicationsSwitchOn&&isLinksShownOnMap&&isNodesShownOnMap&&"
			+ "(indLinksCount>0)&&(indNodesCount>0);};";
	
	private static final String GET_INDICATION_LINK_COUNT_JS_FUNCTION = "function getIndicationLinksCount(){var linksCnt=0;if(lastConstellation){"
			+ "lastConstellation.links.forEach(function(d){linksCnt++;});};return linksCnt;};";

	private static final String GET_INDICATION_NODES_COUNT_JS_FUNCTION = "function getIndicationNodesCount(){var nodesCnt=0;"
			+ "if(lastConstellation){lastConstellation.nodes.forEach(function(d){if(!d.fixed){nodesCnt++;}});};return nodesCnt;};";
	
	private static final String GET_INDICATION_NODES_TEXT_JS_FUNCTION = "function getIndicationNodesText(){var text='';var nodeCnt=0;"
			+ "if(lastConstellation){lastConstellation.nodes.forEach(function(d){if(d.text){if(nodeCnt==0){text=d.text;}else{text+=','+d.text;};nodeCnt++;}});};"
			+ "return text;};";
	
	private static final String IS_ICON_PRESENT_JS_FUNCTION_CALL = "isIconPresent('%s');";

	private static final String IS_LISAS_PRESENT_JS_FUNCTION_CALL = "isLisasPresent();";
	private static final String GET_LISA_COORDINATES_JS_FUNCTION_CALL = "getLisaCoordinates();";
	
	private static final String IS_BOUNDARIES_PRESENT_JS_FUNCTION_CALL = "isBoundariesPresent();";
	private static final String GET_BOUNDARIES_GEOMETRY_COORDINATES_JS_FUNCTION_CALL = "getBoundariesCoordinates();";
	
	private static final String IS_ASSETS_PRESENT_JS_FUNCTION_CALL = "isAssetsPresent();";
	private static final String GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION_CALL = "getAssetsCoordinates();";
	
	private static final String IS_BREADCRUMBS_PRESENT_JS_FUNCTION_CALL = "isBreadCrumbPresent();";
	private static final String GET_BREADCRUMB_GEOMETRY_COORDINATES_FUNCTION_CALL = "getBreadCrumbCoordinates();";
	
	private static final String CONCENTRATION_CHART_DATA_FUNCTION_CALL = "isConcentrationChartDataShownOnMap(5,50);";   // look for 50% white pixels in bottom 5% of the chart
	private static final String GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION_CALL = "getConcentrationChartImageData();";
	
	private static final String IS_INDICATIONS_PRESENT_JS_FUNCTION_CALL = "isIndicationsShownOnMap();";
	private static final String GET_INDICATION_LINK_COUNT_JS_FUNCTION_CALL = "getIndicationLinksCount();";
	private static final String GET_INDICATION_NODES_COUNT_JS_FUNCTION_CALL = "getIndicationNodesCount();";
	private static final String GET_INDICATION_NODES_TEXT_JS_FUNCTION_CALL = "getIndicationNodesText();";
	
	private static final String IS_FOV_PRESENT_JS_FUNCTION_CALL = "getFOVCoordinates();";
	private static final String GET_FOV_GEOMETRY_COORDINATES_FUNCTION_CALL = "getFOVCoordinates();";
	
	private WebDriver driver;

	public OLMapUtility(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	 * Checks if LISA is shown on the Map. 
	 * Check the LISA layer properties. Also checks flatCoordinates on Lisa are present.
	 * Returns true if both are present, false otherwise.
	 * 
	 */
	public boolean isLISAShownOnMap() {
		boolean islisaPresent = false;
		boolean arelisaCoordsPresent = false;
		Object lisaPresent = ((JavascriptExecutor)this.driver).executeScript(IS_LISAS_PRESENT_JS_FUNCTION + IS_LISAS_PRESENT_JS_FUNCTION_CALL);
		if (lisaPresent.toString().equalsIgnoreCase("true")) {
			islisaPresent = true;
		}
		
		List<Object> lisaCoords = getLISACoordinates();
		if (lisaCoords != null && lisaCoords.size() > 0) {
			arelisaCoordsPresent = true;
		}
		return (islisaPresent && arelisaCoordsPresent);
	}

	/*
	 * Returns geometry coordinates for LISAs as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getLISACoordinates() {
		Object lisaCoordinates = ((JavascriptExecutor)this.driver).executeScript(GET_LISA_COORDINATES_JS_FUNCTION + GET_LISA_COORDINATES_JS_FUNCTION_CALL);
		return (List<Object>)lisaCoordinates;
	}

	/*
	 * Checks if Indications is shown on the Map. 
	 * Returns true if Indications is shown on the map, false otherwise.
	 * 
	 */
	public boolean isIndicationsShownOnMap() {
		Object indicationsPresent = ((JavascriptExecutor)this.driver).executeScript(GET_INDICATION_LINK_COUNT_JS_FUNCTION +
				GET_INDICATION_NODES_COUNT_JS_FUNCTION + IS_INDICATIONS_PRESENT_JS_FUNCTION + IS_INDICATIONS_PRESENT_JS_FUNCTION_CALL);
		if (indicationsPresent.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Returns number of Indication links shown on the Map. 
	 * 
	 */
	public Integer getIndicationsLinksCount() {
		Object indicationsLinksCount = ((JavascriptExecutor)this.driver).executeScript(GET_INDICATION_LINK_COUNT_JS_FUNCTION + 
				GET_INDICATION_LINK_COUNT_JS_FUNCTION_CALL);
		return Integer.valueOf(indicationsLinksCount.toString());
	}

	/*
	 * Returns number of Indication nodes shown on the Map. 
	 * 
	 */
	public Integer getIndicationsNodesCount() {
		Object indicationsNodesCount = ((JavascriptExecutor)this.driver).executeScript(GET_INDICATION_NODES_COUNT_JS_FUNCTION + 
				GET_INDICATION_NODES_COUNT_JS_FUNCTION_CALL);
		return Integer.valueOf(indicationsNodesCount.toString());
	}
	
	/*
	 * Returns a comma-seperated values of nodes texts shown on the Map. 
	 * 
	 */
	public String getIndicationsNodesText() {
		Object indicationsNodesText = ((JavascriptExecutor)this.driver).executeScript(GET_INDICATION_NODES_TEXT_JS_FUNCTION + 
				GET_INDICATION_NODES_TEXT_JS_FUNCTION_CALL);
		return indicationsNodesText.toString();
	}

	/*
	 * Checks if concentration chart is shown on the Map. 
	 * Returns true if concentration chart is shown on the map, false otherwise.
	 * 
	 */
	public boolean isConcentrationChartDataShowingOnMap() {
		Object concChartPresent = ((JavascriptExecutor)this.driver).executeScript(CONCENTRATION_CHART_DATA_FUNCTION + CONCENTRATION_CHART_DATA_FUNCTION_CALL);
		if (concChartPresent.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
	
	/*
	 * Returns image data for Concentration Chart as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getConcentrationChartImageData() {
		Object concChartImgData = ((JavascriptExecutor)this.driver).executeScript(GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION + GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION_CALL);
		return (List<Object>)concChartImgData;
	}

	/*
	 * Checks if breadcrumbs is shown on the Map. 
	 * Check the Breadcrumb layer properties. Also checks flatCoordinates on breadcrumb are present.
	 * Returns true if both are present, false otherwise.
	 * 
	 */
	public boolean isBreadcrumbShownOnMap() {
		boolean isBreadcrumbPresent = false;
		boolean areBreadcrumbCoordsPresent = false;
		Object breadcrumbPresent = ((JavascriptExecutor)this.driver).executeScript(IS_BREADCRUMBS_PRESENT_JS_FUNCTION + IS_BREADCRUMBS_PRESENT_JS_FUNCTION_CALL);
		if (breadcrumbPresent.toString().equalsIgnoreCase("true")) {
			isBreadcrumbPresent = true;
		}
		
		List<Object> breadcrumbCoords = getBreadCrumbCoordinates();
		if (breadcrumbCoords != null && breadcrumbCoords.size() > 0) {
			areBreadcrumbCoordsPresent = true;
		}
		return (isBreadcrumbPresent && areBreadcrumbCoordsPresent);
	}

	/*
	 * Returns geometry coordinates for BreadCrumbs as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getBreadCrumbCoordinates() {
		Object breadcrumbCoordinates = ((JavascriptExecutor)this.driver).executeScript(GET_BREADCRUMB_GEOMETRY_COORDINATES_FUNCTION + GET_BREADCRUMB_GEOMETRY_COORDINATES_FUNCTION_CALL);
		return (List<Object>)breadcrumbCoordinates;
	}

	/*
	 * Checks if FOV is shown on the Map. 
	 * Check the FOV layer properties. Also checks coordinates on FOV are present.
	 * Returns true if both are present, false otherwise.
	 * 
	 */
	public boolean isFOVShownOnMap() {
		boolean isFOVPresent = false;
		boolean areFOVCoordsPresent = false;
		Object FOVPresent = ((JavascriptExecutor)this.driver).executeScript(IS_FOV_PRESENT_JS_FUNCTION + IS_FOV_PRESENT_JS_FUNCTION_CALL);
		if (FOVPresent.toString().equalsIgnoreCase("true")) {
			isFOVPresent = true;
		}
		
		List<Object> FOVCoords = getFOVCoordinates();
		if (FOVCoords != null && FOVCoords.size() > 0) {
			areFOVCoordsPresent = true;
		}
		return (isFOVPresent && areFOVCoordsPresent);
	}

	/*
	 * Returns geometry coordinates for FOV as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getFOVCoordinates() {
		Object fovCoordinates = ((JavascriptExecutor)this.driver).executeScript(GET_FOV_GEOMETRY_COORDINATES_FUNCTION + GET_FOV_GEOMETRY_COORDINATES_FUNCTION_CALL);
		return (List<Object>)fovCoordinates;
	}
	
	/*
	 * Checks if Asset is shown on the Map. 
	 * Check the Asset layer properties. Also checks coordinates on Asset are present.
	 * Returns true if both are present, false otherwise.
	 * 
	 */
	public boolean isAssetShownOnMap() {
		boolean isAssetPresent = false;
		boolean areAssetCoordsPresent = false;
		Object assetPresent = ((JavascriptExecutor)this.driver).executeScript(IS_ASSETS_PRESENT_JS_FUNCTION + IS_ASSETS_PRESENT_JS_FUNCTION_CALL);
		if (assetPresent.toString().equalsIgnoreCase("true")) {
			isAssetPresent = true;
		}
		
		List<Object> assetCoords = getAssetCoordinates();
		if (assetCoords != null && assetCoords.size() > 0) {
			areAssetCoordsPresent = true;
		}
		return (isAssetPresent && areAssetCoordsPresent);
	}

	/*
	 * Returns geometry coordinates for FOV as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAssetCoordinates() {
		Object assetsCoordinates = ((JavascriptExecutor)this.driver).executeScript(GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION + GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION_CALL);
		return (List<Object>)assetsCoordinates;
	}
	
	/*
	 * Checks if Boundaries is shown on the Map. 
	 * Check the Boundaries layer properties. Also checks coordinates on Boundaries are present.
	 * Returns true if both are present, false otherwise.
	 * 
	 */
	public boolean isBoundariesShownOnMap() {
		boolean isBoundariesPresent = false;
		boolean areBoundariesCoordsPresent = false;
		Object BoundariesPresent = ((JavascriptExecutor)this.driver).executeScript(IS_BOUNDARIES_PRESENT_JS_FUNCTION + IS_BOUNDARIES_PRESENT_JS_FUNCTION_CALL);
		if (BoundariesPresent.toString().equalsIgnoreCase("true")) {
			isBoundariesPresent = true;
		}
		
		List<Object> BoundariesCoords = getBoundariesCoordinates();
		if (BoundariesCoords != null && BoundariesCoords.size() > 0) {
			areBoundariesCoordsPresent = true;
		}
		return (isBoundariesPresent && areBoundariesCoordsPresent);
	}
	
	/*
	 * Returns geometry coordinates for Boundaries as a List<Object>.
	 * NOTE: List<Object> could have nested List<Object>. 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getBoundariesCoordinates() {
		Object BoundariesCoordinates = ((JavascriptExecutor)this.driver).executeScript(GET_BOUNDARIES_GEOMETRY_COORDINATES_JS_FUNCTION + GET_BOUNDARIES_GEOMETRY_COORDINATES_JS_FUNCTION_CALL);
		return (List<Object>)BoundariesCoordinates;
	}

	/*
	 * Checks whether CrossHair icon of the specified color is overlaid on the map. 
	 * Returns true if the specified icon is overlaid, false otherwise. 
	 */
	public boolean isCrossHairIconShownOnMap(IconColor color) {
		String jsScript = IS_ICON_PRESENT_JS_FUNCTION;
		switch (color)
		{
		case Gray:
			jsScript += String.format(IS_ICON_PRESENT_JS_FUNCTION_CALL, CROSSHAIR_GRAY_PNG);
			break;
		case Red:
			jsScript += String.format(IS_ICON_PRESENT_JS_FUNCTION_CALL, CROSSHAIR_PNG);
			break;
		case White:
			jsScript += String.format(IS_ICON_PRESENT_JS_FUNCTION_CALL, CROSSHAIR_WHITE_PNG);
			break;
		default:
			jsScript += String.format(IS_ICON_PRESENT_JS_FUNCTION_CALL, CROSSHAIR_PNG);
			break;
		}
		Object iconFound = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (iconFound.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
}
