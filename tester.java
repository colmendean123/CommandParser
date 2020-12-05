package cs350f20project.controller.cli.parser;
import java.util.Arrays;
import java.util.LinkedList;

import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;
import cs350f20project.controller.command.A_Command;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;
import cs350f20project.datatype.Latitude;
import cs350f20project.datatype.Longitude;

public class tester {

	public static void main(String[] args) {
		
		Controller controller=new Controller();
		CommandLineInterface command=new CommandLineInterface(controller);
		ActionProcessor action=new ActionProcessor(command);
		MyParserHelper parser=new MyParserHelper(action);
		Latitude la=new Latitude(1);
		Longitude lo=new Longitude(1);
		String[] text=("Do Break id").split(" ");
		String commandText=Arrays.toString(text);
	    commandText="COMMIT";
		CommandParser obj=new CommandParser(parser, commandText);
		
		cs350f20project.Startup obj1=new cs350f20project.Startup();
		obj.setID("id");
		String id=obj.getID();
		LinkedList p=new LinkedList();
		obj.setPoles(p);
	    
		CoordinatesWorld coordinates_world=new CoordinatesWorld(la,lo);
		obj.setWorldCoordinates(coordinates_world);
		
		CoordinatesDelta coordinates_delta1=new CoordinatesDelta(1,1);
		obj.setCoordinatesDelta1(coordinates_delta1);
		CoordinatesDelta coordinates_delta2=new CoordinatesDelta(1,1);
		obj.setCoordinatesDelta2(coordinates_delta2);
		double number=1;
		obj.setNumber(number);
		String id1="z";
		obj.setID1(id1);
		String id2="zzz";
		obj.setID2(id2);
		String idn="zz";
		obj.setIDn(idn);
		double number1=1;
		obj.setNumber1(number1);
		double number2=1;
		obj.setNumber2(number2);
		CoordinatesDelta coordinates_delta3=new CoordinatesDelta(1,1);
		obj.setCoordinatesDelta3(coordinates_delta3);
		CoordinatesDelta coordinates_delta4=new CoordinatesDelta(1,1);
		obj.setCoordinatesDelta4(coordinates_delta4);
		String[] command44=("CREATE TRACK "+id+"REFERENCE "+coordinates_world+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2).split(" ");
		String[] command43=("CREATE TRACK CURVE "+id+"REFERENCE "+coordinates_world+"DELTA START "+coordinates_delta1+"END"+coordinates_delta2+"DISTANCE ORIGIN "+number).split(" ");
		String[] command22=("CREATE POWER CATENARY "+id1+"WITH POLES "+idn+"+").split(" ");
		String[] command49=("CREATE TRACK SWITCH WYE "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2+"DISTANCE ORIGIN "+number1+"DELTA START "+coordinates_delta3+"END "+coordinates_delta4+"DISTANCE ORIGIN"+number2).split(" ");
		commandText=Arrays.toString(command43);
		//commandText=Arrays.toString(command22);
		obj= new CommandParser(parser, commandText);
		obj.parse();
		 
	    commandText=Arrays.toString(command44);
	    obj=new CommandParser(parser,commandText);
	     obj.parse();
	     
	     commandText=Arrays.toString(command49);
	     obj.parse();
	     commandText="COMMIT";
	     obj=new CommandParser(parser, commandText);
	     obj.parse();
	    
	}
	

}
