package com.x.ws.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.x.ws.generated.BookMyFlightService;
import com.x.ws.generated.RequestFlight;
import com.x.ws.generated.ResponseFlight;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "com.x.ws.generated.BookMyFlightService")
public class BookMyFlightServiceImpl implements BookMyFlightService {

	private static final Logger logger = Logger
			.getLogger(BookMyFlightServiceImpl.class);

	@WebMethod(operationName = "BookFlight", action = "http://com.x.service/BookMyFlightService/BookFlight")
	@WebResult(name = "ResponseFlight", targetNamespace = "http://com.x.service/ResponseFlight", partName = "parameters")
	public ResponseFlight bookFlight(
			@WebParam(name = "RequestFlight", targetNamespace = "http://com.x.service/RequestFlight", partName = "parameters") RequestFlight parameters) {

		String flDestLoc, flFromLoc = null;
		Boolean flToDestExist = null;
		Boolean directFlightExist = null;
		ResponseFlight flResp = new ResponseFlight();

		flFromLoc = parameters.getFromLoc();
		flDestLoc = parameters.getDestLoc();

		ArrayList<String> lst_locn_from = new ArrayList<String>();
		ArrayList<String> lst_locn_to = new ArrayList<String>();
		ArrayList<String> lst_paths = new ArrayList<String>();

		lst_locn_to.add("b"); // Details of "location to" can be uploaded by quering to database. I am hardcoding as per paths given in diagram
		lst_locn_to.add("a");
		lst_locn_to.add("c");
		lst_locn_to.add("f");
		lst_locn_to.add("e");
		lst_locn_to.add("d");

		lst_locn_from.add("h");// Details of "location from" can be uploaded by quering to database. I am hardcoding as per paths given in diagram
		lst_locn_from.add("b");
		lst_locn_from.add("e");
		lst_locn_from.add("a");
		lst_locn_from.add("c");
		lst_locn_from.add("d");

		lst_paths.add("hb");//Distinct Paths Details of "flight paths" can be uploaded by quering to database. I am hardcoding as per paths given in diagram
		lst_paths.add("ba");
		lst_paths.add("bc");
		lst_paths.add("eb");
		lst_paths.add("af");
		lst_paths.add("ce");
		lst_paths.add("cd");
		lst_paths.add("de");

		flToDestExist = Boolean.valueOf(checkFlightToDestExist(flDestLoc,
				lst_locn_to));
		if (!flToDestExist.booleanValue()) {
			flResp.setErrorMsg("No Flights to Destination Present");
			return flResp;
		}
		directFlightExist = Boolean.valueOf(checkForDirectFlight(flFromLoc,
				flDestLoc, lst_paths));
		String pathSuggested;
		if (directFlightExist.booleanValue()) {
			pathSuggested = (new StringBuilder()).append(flFromLoc)
					.append(flDestLoc).toString();
			flResp.setFlPathSug(pathSuggested);
			return flResp;
		} else {
			
			ArrayList<String> listPath = getPaths(flFromLoc, lst_paths,
					lst_locn_to);

			StringBuilder sb = new StringBuilder();
			int count =1;
			first:
			for (int i = 0; i < listPath.size(); i++) {
				ArrayList<String> rootList = new ArrayList<String>();
				rootList.add(listPath.get(i));
				String path = listPath.get(i);
				//String frmLoc = path.substring(0, 1); // returns from location in path
				String destLoc = path.substring(1); // //returns dest location in path
				if (destLoc.equalsIgnoreCase(flDestLoc)) {
					for (int p = 0; p < rootList.size(); p++) {
						sb.append(rootList.get(p));
					}
					flResp.setFlPathSug(sb.toString());
					return flResp;
				} else {
					String response = processNextPath(destLoc,flDestLoc,lst_paths,lst_locn_to,rootList);
					if(response.equals("")){
						count++;
						continue first;
					}
					sb.append(response);
					count++;
					if(count ==listPath.size()){
						sb.append(":");
					}
					//flResp.setFlPathSug(response);
					
					//return flResp;
				}

			}
			String finalPath = getFinalPath(sb.toString());
			flResp.setFlPathSug(finalPath);
			return flResp;
		}

		
	}

	private String getFinalPath(String string) {
		
		ArrayList<String> lstPath = new ArrayList<String>();
		ArrayList<Integer> lstLen = new ArrayList<Integer>();
		StringTokenizer str = new StringTokenizer(string,":");
		int minLen =0;
		while(str.hasMoreTokens()){
			String strVal = "";
			strVal = str.nextToken();
			lstPath.add(strVal);
			lstLen.add(strVal.length());
		}
		minLen = Collections.min(lstLen);
		for(int i =0 ;i<lstPath.size(); i++){
			if(lstPath.get(i).length() == minLen){
				return lstPath.get(i).toString();
			}
		}
	  return null;
	}

	private String processNextPath(String destLoc, String flDestLoc, ArrayList<String> lst_paths, ArrayList<String> lst_locn_to, ArrayList<String> rootList) {
		
		StringBuilder sb = new StringBuilder();
				
			second:
			do{
				ArrayList<String> listPth1 = getPaths(destLoc, lst_paths,
						lst_locn_to);
				
					String path1 =null;
					
					if(listPth1.size()==0){
						return "";
					}
					
					for(int h=0 ;h<listPth1.size(); h++){
						String pat = listPth1.get(h);
						String dest = pat.substring(1);
						if(dest.equals(flDestLoc)){
							path1 = pat;
							rootList.add(path1);
							break second;
						}
					}
									
					
					if(listPth1.size() > 1){
						ArrayList<String> rootFromList = new ArrayList<String>();
						for(int q =0; q<rootList.size(); q++){
							rootFromList.add(rootList.get(q).substring(0,1));
						}
						path1 = getSinglePath(listPth1,flDestLoc,rootFromList,lst_locn_to,lst_paths);
					}else{
						path1 = listPth1.get(0);
					}
				
					String destLoc1 = path1.substring(1); 
					if (destLoc1.equalsIgnoreCase(flDestLoc)) {
						rootList.add(path1);
						break second;
					}else{
						rootList.add(path1);
						destLoc = destLoc1;
						//flag =true;
						continue second;
					}
		    }while(true);
				
		for(int y =0; y<rootList.size(); y++){
			sb.append(rootList.get(y));
		}
		String path = sb.toString();
		
		return path;
	}

	
	private String getSinglePath(ArrayList<String> listPth1, String flDestLoc, ArrayList<String> rootFromList, ArrayList<String> lst_locn_to, ArrayList<String> lst_paths) {
		
		
		//Identify FromLoc already exist in path or not. This helps which path needs to be included and which one not.
		String str,path = null;
		boolean pathSug = false;
		ArrayList<String> lstPassed = new ArrayList<String>();
		ArrayList<String> lstP = new ArrayList<String>();
		for(int n =0 ;n<listPth1.size(); n++){
			path =null;
			str =null;
			path = listPth1.get(n);
			str = path.substring(1);
				pathSug = rootFromList.contains(str); // if true means fromLoc already exist in rootPath so dont add the path
				if(!pathSug){
					lstPassed.add(path);
				}
			
		}
		// Check if multiple paths still exists, then get the next connecting flight from these paths and so on
		if(lstPassed.size()>1){

			for(int q=0; q<lstPassed.size(); q++){
				String vpath = lstPassed.get(q);
				//String frmLoc = vpath.substring(0,1);
				String desLoc = vpath.substring(1);
				ArrayList<String> alPath = getPaths(desLoc,lst_paths,lst_locn_to);
				second:
				for(int r=0; r<alPath.size(); r++){
					String value = null;
					String dest =null;
					value = alPath.get(r);
					dest = value.substring(1);
					if(dest.equals(flDestLoc)){
						lstP.add(vpath);
						break second;
					}else{
						for(int u = 0 ; u<lst_locn_to.size(); u++){
							if( lst_paths.contains(dest+lst_locn_to.get(u))){
								lstP.add(vpath);
								break second;
							}
						}
					}
					
				}
			}
			
			return lstP.get(0).toString();
			  
		}else{
			return lstPassed.get(0).toString();
		}
		
	}

	private ArrayList<String> getPaths(String flFromLoc,
			ArrayList<String> lst_paths, ArrayList<String> lst_locn_to) {

		ArrayList<String> list = new ArrayList<String>();
		for (int j = 0; j < lst_locn_to.size(); j++) {
			if (lst_paths.contains((flFromLoc + lst_locn_to.get(j)).trim())) {
				list.add((flFromLoc + lst_locn_to.get(j)).trim());
			}
		}
		return list;
	}

	private boolean checkForDirectFlight(String flFromLoc, String flDestLoc,
			ArrayList<String> lst_paths) {
		try {
			String path = (new StringBuilder()).append(flFromLoc)
					.append(flDestLoc).toString();
			path = path.trim();
			return lst_paths.contains(path);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private boolean checkFlightToDestExist(String flDestLoc,
			ArrayList<String> lst_locn_to) {
		try {
			if (lst_locn_to.contains(flDestLoc))
				return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
//Only for testing
	/*public static void main(String... args) {

		BookMyFlightServiceImpl impl = new BookMyFlightServiceImpl();
		RequestFlight reqFl = new RequestFlight();
		ResponseFlight resFlight = new ResponseFlight();
		reqFl.setFromLoc("b");
		reqFl.setDestLoc("e");
		resFlight = impl.bookFlight(reqFl);
		System.out.println("Response Flight:" + resFlight.getFlPathSug());
	}*/

}
