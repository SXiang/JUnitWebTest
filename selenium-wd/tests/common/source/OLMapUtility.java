package common.source;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.source.OLMapEntities.Indication;
import surveyor.scommon.source.Coordinates;

public class OLMapUtility {

	private static final String BREADCRUMB_COLOR_RED = "#E31A1C";
	private static final String BREADCRUMB_COLOR_GRAY = "#666666";
	private static final String BREADCRUMB_COLOR_BLACK = "#000000";
	private static final String BREADCRUMB_COLOR_BLUE = "#0000ff";
	private static final String CROSSHAIR_WHITE_PNG = "crosshair-white.png";
	private static final String CROSSHAIR_PNG = "crosshair.png";
	private static final String CROSSHAIR_GRAY_PNG = "crosshair-gray.png";

	private static final double DEFAULT_MAP_RESOLUTION = 0.29858214173896974F;

	public enum IconColor {
		Red,
		White,
		Gray
	}

	public enum BreadcrumbColor {
		Red,
		Blue,
		Black,
		Gray
	}

	private static final String GET_FIRST_INDICATION_NODE_PIXEL_FUNCTION_JS = "function getFirstIndicationNodePixels(){"
			+ "var pixelX=-1;var pixelY=-1;if(lastConstellation){for(var i=0;i<lastConstellation.nodes.length;i++){"
			+ "node=lastConstellation.nodes[i];if(node.type=='indication' && node.fixed == false){"
			+ "var mapExtent=surveyormap.getView().calculateExtent(surveyormap.getSize());"
			+ "var mapOrigin=surveyormap.getPixelFromCoordinate([mapExtent[0],mapExtent[3]]);"
			+ "var delta=getOriginDelta(lastExtent,mapOrigin);pixelX=(node.x-(delta[0]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);"
			+ "pixelY=(node.y-(delta[1]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);return[pixelX,pixelY];}}};"
			+ "return[pixelX,pixelY];};";

	private static final String GET_FIRST_3300_VISIBLE_INDICATION_NODE_PIXEL_FUNCTION_JS = "function getFirstVisible3300IndicationNodePixels(gasType){"
			+ "var pixelX=-1;var pixelY=-1;var isIndicationsSwitchOn=showIndications;if(lastConstellation&&isIndicationsSwitchOn){"
			+ "for(var i=0;i<lastConstellation.nodes.length;i++){node=lastConstellation.nodes[i];if(node&&node.type=='indication' && node.fixed==false){"
			+ "if(((gasType=='NaturalGas')&&(node.Disposition==1))||((gasType=='NotNaturalGas')&&(node.Disposition==2))||"
			+ "((gasType=='PossibleNaturalGas')&&(node.Disposition==3))||((gasType=='VehicleExhaust')&&(node.Disposition==4))){"
			+ "var mapExtent=surveyormap.getView().calculateExtent(surveyormap.getSize());"
			+ "var mapOrigin=surveyormap.getPixelFromCoordinate([mapExtent[0],mapExtent[3]]);"
			+ "var delta=getOriginDelta(lastExtent,mapOrigin);pixelX=(node.x-(delta[0]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);"
			+ "pixelY=(node.y-(delta[1]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);"
			+ "if((pixelX>0.0)&&(pixelY>80.0)){return[pixelX,pixelY];}}}}};return[pixelX,pixelY];};";

	private static final String GET_INDICATION_NODE_PIXEL_FUNCTION_JS = "function getIndicationNodePixels(epoch,lat,lon){"
			+ "var pixelX=-1;var pixelY=-1;if(lastConstellation){lastConstellation.nodes.forEach(function(node){if(node.type=='indication' && node.fixed == false){"
			+ "if((node.epochTime==epoch)&&(node.lat==lat)&&(node.lon==lon)){"
			+ "var mapExtent=surveyormap.getView().calculateExtent(surveyormap.getSize());"
			+ "var mapOrigin=surveyormap.getPixelFromCoordinate([mapExtent[0],mapExtent[3]]);"
			+ "var delta=getOriginDelta(lastExtent,mapOrigin);pixelX=(node.x-(delta[0]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);"
			+ "pixelY=(node.y-(delta[1]*ol.has.DEVICE_PIXEL_RATIO))/(ol.has.DEVICE_PIXEL_RATIO);"
			+ "return{pixelX,pixelY};}}});};return [pixelX,pixelY];};";

	private static final String GET_INDICATIONS_ARRAY_FUNCTION_JS = "function getIndicationsArray(){"
			+ "var indicationsArray=new Array();lastConstellation.nodes.forEach(function(d){"
			+ "if(!d.fixed&&d.type=='indication'){var indicationStr=d.CH4+'|'+d.ClassificationConfidence+'|'+d.Disposition+'|'+"
			+ "d.Ethane+'|'+d.EthaneRatio+'|'+d.EthaneRatioSdev+'|'+d.amplitude+'|'+d.epochTime+'|'+d.index+'|'+d.lat+"
			+ "'|'+d.lon+'|'+d.text;indicationsArray.push(indicationStr)}});return indicationsArray;};";

	private static final String IS_FIELD_NOTES_DIALOG_SHOWN_FUNCTION = "function isFieldNotesDialogShown() { var shown = false; try { if (surveyormap) "
			+ "{ overlays = surveyormap.getOverlays(); for (var i = 0; i < overlays.getLength() ; i++) { overlay = overlays.item(i); "
			+ "if (overlay) { element = overlay.getElement(); if (element) { if (element.id == 'annotation_modal') "
			+ "{ return !(overlay.getPosition() == undefined); } } } } } } catch (err) { shown = false; }; return shown; };";

	private static final String IS_FIELD_NOTE_SHOWN_FUNCTION = "function isFieldNoteShownOnMap(note){var noteFound=false;"
			+ "var freshConstellation=JSON.parse(JSON.stringify(d3constellation));freshConstellation.nodes.forEach(function(d){"
			+ "if(d.type=='annotation'){if(!d.fixed){if(d.text==note){noteFound=true;}}}});return noteFound&&showAnnotations;};";

	private static final String IS_PEAK_INFO_POPUP_SHOWN_FUNCTION = "function isPeakInfoPopupShown(){"
			+ "var shown=false;try{if(surveyormap){overlays=surveyormap.getOverlays();for(var i=0;i<overlays.getLength();i++){"
			+ "overlay=overlays.item(i);if(overlay){element=overlay.getElement();if(element){if(element.id=='peakinfo_modal'){"
			+ "return!(overlay.getPosition()==undefined);}}}}}}catch(err){shown=false;};return shown;};";

	private static final String IS_ISOTOPIC_CAPTURE_RESULT_PRESENT_FUNCTION = "function isIsotopicCaptureResultPresent(result){"
			+ "var resultFound=false;var freshConstellation=JSON.parse(JSON.stringify(d3constellation));"
			+ "freshConstellation.nodes.forEach(function(d){if(d.type=='capture'&&!d.isRefGas){if(!d.fixed){"
			+ "if(d.text==result){resultFound=true;}}}});return resultFound&&showIsotopicAnalysis;};";

	private static final String IS_REFGAS_CAPTURE_RESULT_PRESENT_FUNCTION = "function isReferenceGasCaptureResultPresent(result){"
			+ "var resultFound=false;var freshConstellation=JSON.parse(JSON.stringify(d3constellation));"
			+ "freshConstellation.nodes.forEach(function(d){if(d.type=='capture'&&d.isRefGas){if(!d.fixed){"
			+ "if(d.text==result){resultFound=true;}}}});return resultFound&&showIsotopicAnalysisRefGas;};";

	private static final String IS_ICON_PRESENT_JS_FUNCTION = "function isIconPresent(imgFileName){"
			+ "var found=false;var CAR_ICON_SRC='/content/images/'+imgFileName;try{layers=surveyormap.getLayers();"
			+ "if(layers){for(var i=0;i<layers.getLength();i++){layer=layers.item(i);if(layer&&layer.getVisible&&layer.getVisible()){"
			+ "source=layer.getSource();if(source){if(source.getFeatures){features=source.getFeatures();if(features){"
			+ "for(var j=0;j<layers.getLength();j++){if(features[j]&&features[j].getStyle){style=features[j].getStyle();"
			+ "if(style){img=style.getImage();if(img){src=img.getSrc();if(src){if(src.toLowerCase()==CAR_ICON_SRC){"
			+ "found=true;}}}}}}}}}}}}}catch(err){found=false;};return found;};";

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

	private static final String IS_ASSETS_PRESENT_JS_FUNCTION = "function isAssetsPresent(){var found=false;"
			+ "try{if(showAssets){if(surveyormap.getView().getResolution()>assetLayerMaxResolution){found=false;}else{"
			+ "layer=assetLayer;if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();"
			+ "if(style&&style.getStroke){stroke=style.getStroke();if(stroke&&stroke.getColor&&stroke.getWidth){"
			+ "scolor=stroke.getColor();swidth=stroke.getWidth();if((scolor==assetColor)&&(swidth==assetMainLineWidth)){"
			+ "found=true;}}}}}}}catch(err){found=false;};return found;};";

	private static final String GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION = "function getAssetsCoordinates(){var assetsCoord=new Array();"
			+ "try{if(assetLayer&&assetLayer.getSource){sources=assetLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry&&geometry.getCoordinates){assetsCoord.push(geometry.getCoordinates());}}}}}};}catch(err){};return assetsCoord;};";

	private static final String CONCENTRATION_CHART_DATA_FUNCTION = "function isConcentrationChartDataShownOnMap(percentRectHeight,percentWhitePixelsToSeek){"
			+ "var chartIsShown=false;try{cc_ctx=$('#graph_mini')[0].getContext('2d');height=$('#graph_mini').height();width=$('#graph_mini').width();"
			+ "var rectHeight=height*percentRectHeight/100;var imgData=cc_ctx.getImageData(0,height-rectHeight,width,rectHeight);var len=imgData.data.length;"
			+ "var points=len*percentWhitePixelsToSeek/100;for(var i=0;i<len;i++){if(imgData.data[i]==255){points--;"
			+ "if(points<0){chartIsShown=true;}}}}catch(err){chartIsShown=false;};return chartIsShown;};";

	private static final String GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION = "function getConcentrationChartImageData(){var imgData;"
			+ "try{cc_ctx=$('#graph_mini')[0].getContext('2d');height=$('#graph_mini').height();width=$('#graph_mini').width();"
			+ "imgData=cc_ctx.getImageData(0,0,width,height).data;}catch(err){imgData=null;};return imgData;};";

	private static final String IS_FOV_PRESENT_JS_FUNCTION = "function isFOVPresent(){var found=false;try{layer=fovLayer;"
			+ "if(layer&&layer.getVisible&&layer.getVisible()&&layer.getStyle){style=layer.getStyle();if(style&&style.getFill){fill=style.getFill();"
			+ "if(fill.getColor){fcolor=fill.getColor();if((fcolor==fovLayerColor)){found=true;}}}}}catch(err){found=false;};return found;};";

	private static final String GET_FOV_GEOMETRY_COORDINATES_FUNCTION = "function getFOVCoordinates(){var fovCoord=new Array();"
			+ "if(fovLayer){if(fovLayer.getSource){sources=fovLayer.getSource();if(sources.getFeatures){features=sources.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){if(features[i]&&features[i].getGeometry){geometry=features[i].getGeometry();"
			+ "if(geometry.getCoordinates){coordArray=geometry.getCoordinates();if(coordArray){fovCoord.push(coordArray);}}}}}}}};return fovCoord;};";

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

	private static final String MATCH_BREADCRUMB_COLOR_JS_FUNCTION = "function matchBreadCrumbColor(color){"
			+ "var fillColorFound=false;try{if(sourceBreadCrumbLayer){layer=sourceBreadCrumbLayer;features=layer.getFeatures();"
			+ "if(features){for(var i=0;i<features.length;i++){feature=features[0];style=feature.getStyle();"
			+ "if(style&&style.getStroke&&style.getFill){fill=style.getFill();stroke=style.getStroke();"
			+ "if(fill&&fill.getColor&&stroke&&stroke.getColor){fillColorFound=true;fcolor=fill.getColor();"
			+ "scolor=stroke.getColor();if(fcolor!=color||scolor!=color){return false;}}}}}}}catch(err){fillColorFound=false;};"
			+ "return fillColorFound;};";

	private static final String IS_INDICATIONS_PRESENT_JS_FUNCTION = "function isIndicationsShownOnMap(){"
			+ "var isIndicationsSwitchOn=showIndications;var indLinksCount=getIndicationLinksCount();"
			+ "var indNodesCount=getIndicationNodesCount();var isLinksShownOnMap=true;lastConstellation.links.forEach(function(d){"
			+ "if(d.source.type=='indication'){x1=d.source.x;y1=d.source.y;x2=d.target.x;y2=d.target.y;if(!x1||!y1||!x2||!y2){"
			+ "isLinksShownOnMap=false;}}});var isNodesShownOnMap=true;lastConstellation.nodes.forEach(function(d){if(d.type=='indication'){"
			+ "if(!d.lat||!d.lon){isNodesShownOnMap=false;}}});return isIndicationsSwitchOn&&isLinksShownOnMap&&isNodesShownOnMap&&"
			+ "(indLinksCount>0)&&(indNodesCount>0);};";

	private static final String IS_3300_INDICATIONS_PRESENT_JS_FUNCTION = "function is3300IndicationsShownOnMap(){"
			+ "var isIndicationsSwitchOn=showIndications;var indLinksCount=getIndicationLinksCount();var indNodesCount=getIndicationNodesCount();"
			+ "var ind_ctx=$('canvas.ol-unselectable')[0].getContext('2d');var lnkCnt=lastConstellation.links.length;var isLinksShownOnMap=false;"
			+ "lastConstellation.links.forEach(function(d){if(d.source.type=='indication'){x1=d.source.x;y1=d.source.y;x2=d.target.x;y2=d.target.y;"
			+ "cpoint=getCenterPoint(x1,y1,x2,y2);pixel=getPixelFromLatLong(d.source.lat,d.source.lon);"
			+ "point1ImgData=ind_ctx.getImageData(pixel[0],pixel[1],1,1);pixel=getPixelFromLatLong(d.target.lat,d.target.lon);"
			+ "point2ImgData=ind_ctx.getImageData(pixel[0],pixel[1],1,1);p=getPixelAfterTransform(d,cpoint.x,cpoint.y);"
			+ "point3ImgData=ind_ctx.getImageData(pixel[0],pixel[1],1,1);isLinksShownOnMap=(point1ImgData!=null)&&(point2ImgData!=null)&&(point3ImgData!=null);"
			+ "if(!isLinksShownOnMap){return false;}}});var isNodesShownOnMap=false;var imgData;lastConstellation.nodes.forEach(function(d){"
			+ "if(d.type=='indication'){if(d.fixed){pixel=getPixelFromLatLong(d.lat,d.lon);imgData=ind_ctx.getImageData(pixel[0],pixel[1],1,1);"
			+ "rgbaArray=getRGBAValues(indicationAnchorFillColor);isNodesShownOnMap=(rgbaArray!=null);}else{var isValidNodeDisposition=false;"
			+ "if(d.Disposition==1||d.Disposition==2||d.Disposition==3||d.Disposition==4){isValidNodeDisposition=true;} "
			+ "isNodesShownOnMap=isValidNodeDisposition;} if(!isNodesShownOnMap){return false;}}});"
			+ "return isIndicationsSwitchOn&&isLinksShownOnMap&&isNodesShownOnMap&&(indLinksCount>0)&&(indNodesCount>0);};";

	private static final String GET_INDICATION_LINK_COUNT_JS_FUNCTION = "function getIndicationLinksCount(){var linksCnt=0;if(lastConstellation){"
			+ "lastConstellation.links.forEach(function(d){linksCnt++;});};return linksCnt;};";

	private static final String GET_INDICATION_NODES_COUNT_JS_FUNCTION = "function getIndicationNodesCount(){var nodesCnt=0;"
			+ "if(lastConstellation){lastConstellation.nodes.forEach(function(d){if(!d.fixed){nodesCnt++;}});};return nodesCnt;};";

	private static final String GET_INDICATION_NODES_TEXT_JS_FUNCTION = "function getIndicationNodesText(){var text='';var nodeCnt=0;"
			+ "if(lastConstellation){lastConstellation.nodes.forEach(function(d){if(d.text){if(nodeCnt==0){text=d.text;}else{text+=','+d.text;};nodeCnt++;}});};"
			+ "return text;};";

	private static final String GET_MAP_RESOLUTION_JS_FUNCTION = "function getMapResolution(){return surveyormap.getView().getResolution();};";
	private static final String GET_MAP_ZOOMLEVEL_JS_FUNCTION = "function getMapZoomLevel(){return surveyormap.getView().getZoom();};";

	private static final String GET_3300_INDICATION_NODES_COUNT_JS_FUNCTION = "function get3300IndicationNodesCount(gasType) { "
			+ "var nodesCnt = 0; var isIndicationsSwitchOn = showIndications; if (lastConstellation && isIndicationsSwitchOn) { "
			+ "lastConstellation.nodes.forEach(function (d) { if (!d.fixed) { if (((gasType == 'NaturalGas') && (d.Disposition == 1)) || "
			+ "((gasType == 'NotNaturalGas') && (d.Disposition == 2)) || ((gasType == 'PossibleNaturalGas') && (d.Disposition == 3)) || "
			+ "((gasType == 'VehicleExhaust') && (d.Disposition == 4))) { nodesCnt++; } } }); }; return nodesCnt; };";

	private static final String GET_3300_INDICATION_NODES_TEXT_JS_FUNCTION = "function get3300IndicationNodesText(gasType) { "
			+ "var text = ''; var nodeCnt = 0; var isIndicationsSwitchOn = showIndications; if (lastConstellation && isIndicationsSwitchOn) { "
			+ "lastConstellation.nodes.forEach(function (d) { if (d.text) { if (((gasType == 'NaturalGas') && (d.Disposition == 1)) || "
			+ "((gasType == 'NotNaturalGas') && (d.Disposition == 2)) || ((gasType == 'PossibleNaturalGas') && (d.Disposition == 3)) || "
			+ "((gasType == 'VehicleExhaust') && (d.Disposition == 4))) { if (nodeCnt == 0) { text = d.text; } else { text += ',' + d.text; }; "
			+ "nodeCnt++; } } }); }; return text; };";

	private static final String IS_MAP_VIEW_SHOWN = "return (mapLayer.getSource() == sourceBingRoads);";
	private static final String IS_SATELLITE_VIEW_SHOWN = "return (mapLayer.getSource() == sourceBingArialWithStreets);";

	private static final String IS_FIELD_NOTES_DIALOG_SHOWN_JS_FUNCTION_CALL = "return isFieldNotesDialogShown();";
	private static final String IS_FIELD_NOTE_SHOWN_JS_FUNCTION_CALL = "return isFieldNoteShownOnMap('%s');";

	private static final String IS_PEAK_INFO_POPUP_SHOWN_JS_FUNCTION_CALL = "return isPeakInfoPopupShown();";

	private static final String IS_ISOTOPIC_CAPTURE_RESULT_PRESENT_FUNCTION_CALL = "return isIsotopicCaptureResultPresent('%s');";
	private static final String IS_REFGAS_CAPTURE_RESULT_PRESENT_FUNCTION_CALL = "return isReferenceGasCaptureResultPresent('%s');";

	private static final String IS_ICON_PRESENT_JS_FUNCTION_CALL = "return isIconPresent('%s');";

	private static final String IS_LISAS_PRESENT_JS_FUNCTION_CALL = "return isLisasPresent();";
	private static final String GET_LISA_COORDINATES_JS_FUNCTION_CALL = "return getLisaCoordinates();";

	private static final String IS_BOUNDARIES_PRESENT_JS_FUNCTION_CALL = "return isBoundariesPresent();";
	private static final String GET_BOUNDARIES_GEOMETRY_COORDINATES_JS_FUNCTION_CALL = "return getBoundariesCoordinates();";

	private static final String IS_ASSETS_PRESENT_JS_FUNCTION_CALL = "return isAssetsPresent();";
	private static final String GET_ASSETS_GEOMETRY_COORDINATES_FUNCTION_CALL = "return getAssetsCoordinates();";

	private static final String IS_BREADCRUMBS_PRESENT_JS_FUNCTION_CALL = "return isBreadCrumbPresent();";
	private static final String GET_BREADCRUMB_GEOMETRY_COORDINATES_FUNCTION_CALL = "return getBreadCrumbCoordinates();";
	private static final String MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL = "return matchBreadCrumbColor('%s');";

	private static final String CONCENTRATION_CHART_DATA_FUNCTION_CALL = "return isConcentrationChartDataShownOnMap(5,10);";   // look for 10% white pixels in bottom 5% of the chart
	private static final String GET_CONCENTRATION_CHART_IMAGE_DATA_FUNCTION_CALL = "return getConcentrationChartImageData();";

	private static final String GET_INDICATIONS_ARRAY_FUNCTION_JS_CALL = "return getIndicationsArray();";

	private static final String IS_INDICATIONS_PRESENT_JS_FUNCTION_CALL = "return isIndicationsShownOnMap();";
	private static final String IS_3300_INDICATIONS_PRESENT_JS_FUNCTION_CALL = "return is3300IndicationsShownOnMap();";

	private static final String GET_INDICATION_LINK_COUNT_JS_FUNCTION_CALL = "return getIndicationLinksCount();";
	private static final String GET_INDICATION_NODES_COUNT_JS_FUNCTION_CALL = "return getIndicationNodesCount();";
	private static final String GET_INDICATION_NODES_TEXT_JS_FUNCTION_CALL = "return getIndicationNodesText();";

	private static final String GET_3300_INDICATION_NODES_COUNT_JS_FUNCTION_CALL = "return get3300IndicationNodesCount('%s');";
	private static final String GET_3300_INDICATION_NODES_TEXT_JS_FUNCTION_CALL = "return get3300IndicationNodesText('%s');";

	private static final String IS_FOV_PRESENT_JS_FUNCTION_CALL = "return isFOVPresent();";
	private static final String GET_FOV_GEOMETRY_COORDINATES_FUNCTION_CALL = "return getFOVCoordinates();";

	private static final String GET_INDICATION_NODE_PIXEL_FUNCTION_CALL = "return getIndicationNodePixels(%s,%s,%s);";
	private static final String GET_FIRST_INDICATION_NODE_PIXEL_FUNCTION_CALL = "return getFirstIndicationNodePixels();";

	private static final String GET_MAP_RESOLUTION_JS_FUNCTION_CALL = "return getMapResolution();";
	private static final String GET_FIRST_3300_VISIBLE_INDICATION_NODE_PIXEL_FUNCTION_CALL = "return getFirstVisible3300IndicationNodePixels('%s');";

	private static final String GET_MAP_ZOOMLEVEL_JS_FUNCTION_CALL = "return getMapZoomLevel();";

	private WebDriver driver;

	public OLMapUtility(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verifies that the map resolution is correct for the specified zoom level.
	 * @param zoomLevel - zoom level.
	 *   to get default map resolution use level=0
	 *   for Zoom-In specify +ve value (for eg. 1,2,3). In this case resolution mutliplier is 0.5x, 0.25x, 0.125x, etc.
	 *   for Zoom-Out specify -ve value (for eg. -1,-2,-3). In this case resolution mutliplier is 2x, 4x, 8x, etc.
	 */
	public boolean isMapResolutionCorrect(int zoomLevel) {
		double precision = 0.00000001D;
		double currentMapResolution = getMapResolution();
		double expectedMapResolution = DEFAULT_MAP_RESOLUTION;
		double exponent = (-1) * zoomLevel;
		if (zoomLevel != 0) {
			expectedMapResolution = Math.pow(2, exponent) * DEFAULT_MAP_RESOLUTION;
		}
		return (Math.abs(expectedMapResolution - currentMapResolution) < precision);
	}

	/**
	 * Get the current map resolution.
	 */
	private double getMapResolution() {
		String functionCall = GET_MAP_RESOLUTION_JS_FUNCTION + GET_MAP_RESOLUTION_JS_FUNCTION_CALL;
		Log.info("Calling javascript function -> " + functionCall);
		return (double)((JavascriptExecutor)this.driver).executeScript(functionCall);
	}

	/**
	 * Get the current map zoom level.
	 */
	public int getMapZoomLevel() {
		String functionCall = GET_MAP_ZOOMLEVEL_JS_FUNCTION +  GET_MAP_ZOOMLEVEL_JS_FUNCTION_CALL;
		Log.info("Calling javascript function -> " + functionCall);
		return (int)(long)(((JavascriptExecutor)driver).executeScript(functionCall));
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
	public boolean is3300IndicationsShownOnMap() {
		Object indicationsPresent = ((JavascriptExecutor)this.driver).executeScript(GET_INDICATION_LINK_COUNT_JS_FUNCTION +
				GET_INDICATION_NODES_COUNT_JS_FUNCTION + IS_INDICATIONS_PRESENT_JS_FUNCTION + IS_INDICATIONS_PRESENT_JS_FUNCTION_CALL);
		if (indicationsPresent.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
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
	 * Returns number of Indication nodes shown on the Map for the specified gasType.
	 *
	 */
	public Integer get3300IndicationsNodesCount(String gasType) {
		Object indicationsNodesCount = ((JavascriptExecutor)this.driver).executeScript(GET_3300_INDICATION_NODES_COUNT_JS_FUNCTION +
				String.format(GET_3300_INDICATION_NODES_COUNT_JS_FUNCTION_CALL, gasType));
		return Integer.valueOf(indicationsNodesCount.toString());
	}

	/*
	 * Returns a comma-seperated values of nodes texts shown on the Map for the specified gasType.
	 *
	 */
	public String get3300IndicationsNodesText(String gasType) {
		Object indicationsNodesText = ((JavascriptExecutor)this.driver).executeScript(GET_3300_INDICATION_NODES_TEXT_JS_FUNCTION +
				String.format(GET_3300_INDICATION_NODES_TEXT_JS_FUNCTION_CALL, gasType));
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
	 * Checks whether breadcrumb is shown on the map with the specified color.
	 * Returns true if the specified color breadcrumb is found, false otherwise.
	 */
	public boolean isBreadcrumbShownOnMap(BreadcrumbColor color) {
		String jsScript = MATCH_BREADCRUMB_COLOR_JS_FUNCTION;
		switch (color)
		{
		case Gray:
			jsScript += String.format(MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL, BREADCRUMB_COLOR_GRAY);
			break;
		case Red:
			jsScript += String.format(MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL, BREADCRUMB_COLOR_RED);
			break;
		case Black:
			jsScript += String.format(MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL, BREADCRUMB_COLOR_BLACK);
			break;
		case Blue:
			jsScript += String.format(MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL, BREADCRUMB_COLOR_BLUE);
			break;
		default:
			jsScript += String.format(MATCH_BREADCRUMB_COLOR_JS_FUNCTION_CALL, BREADCRUMB_COLOR_GRAY);
			break;
		}
		Object colorMatch = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (colorMatch.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
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
		Object BoundariesPresent = ((JavascriptExecutor)this.driver).executeScript(IS_BOUNDARIES_PRESENT_JS_FUNCTION + IS_BOUNDARIES_PRESENT_JS_FUNCTION_CALL);
		if (BoundariesPresent.toString().equalsIgnoreCase("true")) {
			isBoundariesPresent = true;
		}
		return isBoundariesPresent;

		/*
		boolean areBoundariesCoordsPresent = false;
		List<Object> BoundariesCoords = getBoundariesCoordinates();
		if (BoundariesCoords != null && BoundariesCoords.size() > 0) {
			areBoundariesCoordsPresent = true;
		}
		return (isBoundariesPresent && areBoundariesCoordsPresent);
		*/
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

	/*
	 * Checks whether field notes dialog overlay is shown on the map.
	 * Returns true if field dialog notes overlay is shown on the map, false otherwise.
	 * NOTE: Use this method to check if overlay is present in OLMap layer.
	 */
	public boolean isFieldNotesDialogOverlayPresent() {
		String jsScript = IS_FIELD_NOTES_DIALOG_SHOWN_FUNCTION + IS_FIELD_NOTES_DIALOG_SHOWN_JS_FUNCTION_CALL;
		Object fieldNotesDialogShown = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (fieldNotesDialogShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks whether field notes is shown on the map.
	 * Returns true if field notes is shown on the map, false otherwise.
	 */
	public boolean isFieldNoteShown(String fieldNote) {
		String jsScript = IS_FIELD_NOTE_SHOWN_FUNCTION + String.format(IS_FIELD_NOTE_SHOWN_JS_FUNCTION_CALL, fieldNote);
		Object fieldNoteShown = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (fieldNoteShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks whether peak info popup is shown on the map.
	 * Returns true if peak info popup is shown on the map, false otherwise.
	 */
	public boolean isPeakInfoPopupShown() {
		String jsScript = IS_PEAK_INFO_POPUP_SHOWN_FUNCTION + IS_PEAK_INFO_POPUP_SHOWN_JS_FUNCTION_CALL;
		Object peakInfoPopupShown = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (peakInfoPopupShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks whether specified Isotopic Capture result is shown on the map.
	 * Returns true if specified Isotopic Capture result is shown on the map, false otherwise.
	 */
	public boolean isIsotopicCaptureResultPresent(String result) {
		String jsScript = IS_ISOTOPIC_CAPTURE_RESULT_PRESENT_FUNCTION + String.format(IS_ISOTOPIC_CAPTURE_RESULT_PRESENT_FUNCTION_CALL, result);
		Object captureResultShown = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (captureResultShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks whether specified Reference Gas Capture result is shown on the map.
	 * Returns true if specified Reference Gas Capture result is shown on the map, false otherwise.
	 */
	public boolean isRefGasCaptureResultPresent(String result) {
		String jsScript = IS_REFGAS_CAPTURE_RESULT_PRESENT_FUNCTION + String.format(IS_REFGAS_CAPTURE_RESULT_PRESENT_FUNCTION_CALL, result);
		Object captureResultShown = ((JavascriptExecutor)this.driver).executeScript(jsScript);
		if (captureResultShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks if map view is shown on the Map.
	 * Returns true if map view is shown on the map, false otherwise.
	 *
	 */
	public boolean isMapViewShown() {
		Object isMapViewShown = ((JavascriptExecutor)this.driver).executeScript(IS_MAP_VIEW_SHOWN);
		if (isMapViewShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks if satellite view is shown on the Map.
	 * Returns true if satellite view is shown on the map, false otherwise.
	 *
	 */
	public boolean isSatelliteViewShown() {
		Object isSatelliteViewShown = ((JavascriptExecutor)this.driver).executeScript(IS_SATELLITE_VIEW_SHOWN);
		if (isSatelliteViewShown.toString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/*
	 * Clicks on the specified indication on the Map.
	 * If more than one indications are found the first indication matching the parameters is clicked.
	 * Returns true if indication was found and clicked, false otherwise.
	 *
	 */
	@SuppressWarnings("unchecked")
	public boolean clickIndicationOnMap(String canvasXPath, float indicationEpochTime,
			float indicationLatitude, float indicationLongitude) {
		String functionCall = GET_INDICATION_NODE_PIXEL_FUNCTION_JS +
		String.format(GET_INDICATION_NODE_PIXEL_FUNCTION_CALL, String.valueOf(indicationEpochTime),
				String.valueOf(indicationLatitude), String.valueOf(indicationLongitude));

		Log.info("Calling javascript function -> " + functionCall);

		List<Object> indNodePixel = (List<Object>)((JavascriptExecutor)this.driver).executeScript(functionCall);

		if (indNodePixel != null && indNodePixel.size() == 2) {
			long px = (long)indNodePixel.get(0);
			long py = (long)indNodePixel.get(1);
			return clickAtPixel(canvasXPath, (int)px, (int)py);
		}
		return false;
	}

	/*
	 * Gets list of indications on the Map as an object array.
	 *
	 */
	@SuppressWarnings("unchecked")
	public Set<OLMapEntities.Indication> getIndicationsArray() {
		String functionCall = GET_INDICATIONS_ARRAY_FUNCTION_JS + GET_INDICATIONS_ARRAY_FUNCTION_JS_CALL;
		Set<OLMapEntities.Indication> indications = null;
		Log.info("Calling javascript function -> " + functionCall);
		List<Object> indNodes = (List<Object>)((JavascriptExecutor)this.driver).executeScript(functionCall);
		indications = indNodes.stream().map(n -> {
				String nodeStr = n.toString();
				List<String> nodeProps = RegexUtility.split(nodeStr, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
				return new OLMapEntities().new Indication(nodeProps.get(0), nodeProps.get(1), nodeProps.get(2), nodeProps.get(3), nodeProps.get(4),
						nodeProps.get(5), nodeProps.get(6), nodeProps.get(7), Integer.valueOf(nodeProps.get(8)),
						Double.valueOf(nodeProps.get(9)), Double.valueOf(nodeProps.get(10)),
						nodeProps.get(11));
			})
			.collect(Collectors.toSet());
		return indications;
	}

	/*
	 * Clicks on the first indication in the lastConstellation nodes List on the Map.
	 * Returns true if indication was found and clicked, false otherwise.
	 *
	 */
	@SuppressWarnings("unchecked")
	public boolean clickFirstIndicationOnMap(String canvasXPath) {
		return clickFirst3300IndicationOnMap(canvasXPath, null);
	}

	/*
	 * Clicks on the first indication in the lastConstellation nodes List on the Map for the matching gasType.
	 * If no 'gasType' is specified we revert to old way of determining the indication node pixel.
	 * Returns true if indication was found and clicked, false otherwise.
	 *
	 */
	@SuppressWarnings("unchecked")
	public boolean clickFirst3300IndicationOnMap(String canvasXPath, String gasType) {
		String functionCall = GET_FIRST_3300_VISIBLE_INDICATION_NODE_PIXEL_FUNCTION_JS +
				String.format(GET_FIRST_3300_VISIBLE_INDICATION_NODE_PIXEL_FUNCTION_CALL, gasType);
		if (gasType == null) {
			// If no GasType specified, revert to older indication node determination and clicking code.
			functionCall = GET_FIRST_INDICATION_NODE_PIXEL_FUNCTION_JS + GET_FIRST_INDICATION_NODE_PIXEL_FUNCTION_CALL;
		}

		Log.info("Calling javascript function -> " + functionCall);
		List<Object> indNodePixel = (List<Object>)((JavascriptExecutor)this.driver).executeScript(functionCall);

		if (indNodePixel != null && indNodePixel.size() == 2) {
			if (indNodePixel.get(0) instanceof Long)
		    {
				long px = (long)indNodePixel.get(0);
				long py = (long)indNodePixel.get(1);
				Log.info(String.format("Got values x=%s, y=%s", String.valueOf(px), String.valueOf(py)));
				return clickAtPixel(canvasXPath, (int)px, (int)py);
		    }
		    else if (indNodePixel.get(0) instanceof Double)
		    {
				double px = (double)indNodePixel.get(0);
				double py = (double)indNodePixel.get(1);
				Log.info(String.format("Got values x=%s, y=%s", String.valueOf(px), String.valueOf(py)));
				return clickAtPixel(canvasXPath, (int)px, (int)py);
		    }
		}
		return false;
	}

	/*
	 * Clicks at the specified pixel on the canvas element.
	 *
	 */
	public boolean clickAtPixel(String canvasXPath, int x, int y) {
		WebElement canvas = driver.findElement(By.xpath(canvasXPath));
		if (canvas != null && canvas.isDisplayed()) {
			Log.info(String.format("Found canvas element for XPath-'%s'", canvasXPath));
		}

		Log.info(String.format("Clicking at pixel=[%d,%d] on canvas", x, y));
		Actions builder = new Actions(driver);
		builder.moveToElement(canvas, x, y)
			.click()
			.perform();

		return true;
    }
}
