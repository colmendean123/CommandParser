package cs350f20project.controller.cli.parser;



import java.awt.List;
import java.util.Arrays;
import java.util.LinkedList;

import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.PointLocator;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectBridge;
import cs350f20project.controller.command.creational.CommandCreatePowerCatenary;
import cs350f20project.controller.command.creational.CommandCreatePowerPole;
import cs350f20project.controller.command.creational.CommandCreatePowerStation;
import cs350f20project.controller.command.creational.CommandCreateTrackBridgeFixed;
import cs350f20project.controller.command.creational.CommandCreateTrackCrossing;
import cs350f20project.controller.command.creational.CommandCreateTrackCrossover;
import cs350f20project.controller.command.creational.CommandCreateTrackCurve;
import cs350f20project.controller.command.creational.CommandCreateTrackEnd;
import cs350f20project.controller.command.creational.CommandCreateTrackLayout;
import cs350f20project.controller.command.creational.CommandCreateTrackRoundhouse;
import cs350f20project.controller.command.creational.CommandCreateTrackStraight;
import cs350f20project.controller.command.creational.CommandCreateTrackSwitchTurnout;
import cs350f20project.controller.command.creational.CommandCreateTrackSwitchWye;
import cs350f20project.controller.command.meta.CommandMetaDoExit;
import cs350f20project.controller.command.structural.CommandStructuralCommit;
import cs350f20project.datatype.Angle;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;
import cs350f20project.datatype.Latitude;
import cs350f20project.datatype.Longitude;

public class CommandParser {
 
  //private CoordinatesDelta deltaOrigin=new CoordinatesDelta(1,1);
  
  private LinkedList<String> idSubstations=new LinkedList<>();
  
  
  private Angle angle3=new Angle(1);
  private Angle angle1;
  private Angle angle2;
  private CoordinatesDelta coordinates_deltaE;    //2end
  private CoordinatesDelta coordinates_delta;     //2origins
  private CoordinatesDelta coordinates_delta3;
  private String id1;
  private String idn;
  private String id2;
  public static String id;
  private int integer;
  private Latitude latitude;
  private String string;
  private Longitude longitude;
  private CoordinatesDelta coordinates_delta1;
  private CoordinatesDelta coordinates_delta2;
  private double number1;
  private double number2;
  private CoordinatesDelta coordinates_delta4;
  private CoordinatesWorld coordinates_world;
  private double number=1;
  private double real;
  private TrackLocator START;
  private TrackLocator STOP;
  private String commandText;
  private MyParserHelper parserHelper;
  private CommandParserTokenManager tm;
  private PointLocator locater;
  private LinkedList<String> idpoles;
  private LinkedList<String> trackIDs;
	public CommandParser(MyParserHelper parserHelper, String commandText){
		this.parserHelper=parserHelper;
		this.commandText=commandText;
	}
	public CommandParser(CommandParserTokenManager tm) {
		this.tm=tm;
	}
	public void parse() {
    //need to go back and add $ 
	String[] x=("Do Break "+id).split(" ");  
	//40
	String[] command40=("CREATE TRACK BRIDGE "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2).split(" ");
	//41
	String[] command41=("CREATE TRACK CROSSING "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2).split(" ");
	//42
	String[] command42=("CREATE TRACK CROSSOVER "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2+"START "+coordinates_delta3+"END "+coordinates_delta4).split(" ");
	//#44
	 String[] command44=("CREATE TRACK "+id1+" REFERENCE "+coordinates_world+"'$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2).split(" ");

	//43    
	    String[] command43=("CREATE TRACK CURVE "+id1+"REFERENCE "+coordinates_world+"'$' "+id2+"DELTA START "+coordinates_delta1+"END"+coordinates_delta2+"DISTANCE ORIGIN "+number+" | "+"ORIGIN "+coordinates_delta3).split(" ");
    //45
        String[] command45=("CREATE TRACK LAYOUT "+id1+"WITH TRACKS "+idn+"+").split(" ");
    //46 ,
        String[] command46=("CREATE TRACK ROUNDHOUSE "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA ORIGIN "+coordinates_delta1+"ANGLE ENTRY "+angle1+"START "+angle2+"END "+angle3+"WITH "+integer+"SPURS LENGTH "+number1+"TURNTABLE LENGTH "+number2).split(" ");
    //47 ,
        String[] command47=("CREATE TRACK STRAIGHT "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2).split(" ");
    //48,
        String[] command48=("CREATE TRACK SWITCH TURNOUT "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"STRAIGHT DELTA START "+coordinates_delta1+"END "+coordinates_delta2+"CURVE DELTA START "+coordinates_delta3+"END "+coordinates_delta4+"DISTANCE ORIGIN "+number).split(" ");
   //49 
        String[] command49=("CREATE TRACK SWITCH WYE "+id1+"REFERENCE "+coordinates_world+" | '$' "+id2+"DELTA START "+coordinates_delta1+"END "+coordinates_delta2+"DISTANCE ORIGIN "+number1+"DELTA START "+coordinates_delta3+"END "+coordinates_delta4+"DISTANCE ORIGIN"+number2).split(" ");
   
	//22 
	    String[] command22=("CREATE POWER CATENARY "+id1+"WITH POLES "+idn+"+").split(" ");
	//23 
	    String[] command23=("CREATE POWER POLE "+id1+"ON TRACK "+id2+"DISTANCE "+number+"FROM "+"START | END").split(" ");
	  
	//24
	    String[] command24=("CREATE POWER STATION "+id1+"REFERENCE "+coordinates_world+"'$' "+id2+"DELTA "+coordinates_delta+"WITH SUBSTATION | SUBSTATIONS").split(" ");

	//25
	    String[] command25=("CREATE POWER SUBSTATION "+id1+"REFERENCE "+ coordinates_world+"'$' "+id1+"DELTA "+coordinates_delta+"WITH CATENARIES "+idn+"+").split(" "); 
	    
	    if(this.commandText.equals("COMMIT")) {
	    	A_Command command=new CommandStructuralCommit();
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(x))) {
			System.out.println(Arrays.toString(x));
			A_Command command=new CommandBehavioralBrake(x[2]);
			this.parserHelper.getActionProcessor().schedule(command);

		}
	   
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command44))) {              //44
	    	//
	    	A_Command command=new CommandCreateTrackEnd(id, locater);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    	//System.out.println(Arrays.toString(command44));
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command43))) {
	    	A_Command command=new CommandCreateTrackCurve(id, coordinates_world, coordinates_delta1, coordinates_delta2, number);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }

	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command22))) {
	    	A_Command command=new CommandCreatePowerCatenary(id, idpoles);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command23))) {
	    	A_Command command=new CommandCreatePowerPole(id, START);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }

	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command24))) {
	    	A_Command command=new CommandCreatePowerStation(id, coordinates_world, coordinates_delta1, idSubstations);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command25))) {
	    	A_Command command=new CommandCreatePowerStation(id, coordinates_world, coordinates_delta, idSubstations);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command42))) {
	    	A_Command command=new CommandCreateTrackCrossover(id, coordinates_world, coordinates_delta1, coordinates_delta2, coordinates_delta3, coordinates_delta4);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command41))) {
	    	A_Command command=new CommandCreateTrackCrossing(id, locater);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command40))) {
	    	A_Command command=new CommandCreateTrackBridgeFixed(id, locater);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command45))) {
	    	A_Command command=new CommandCreateTrackLayout(id,trackIDs);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command46))) {
	    	A_Command command=new CommandCreateTrackRoundhouse(id, coordinates_world, coordinates_delta1, angle1, angle2, angle3,integer,number1,number2 );
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command47))) {
	    	A_Command command=new CommandCreateTrackStraight(id, locater);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command48))) {
	    	A_Command command =new CommandCreateTrackSwitchTurnout(id, coordinates_world,coordinates_delta1, coordinates_delta2, coordinates_delta3, coordinates_delta4, coordinates_delta);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    else if(this.commandText.equalsIgnoreCase(Arrays.toString(command49))) {
	    	A_Command command =new CommandCreateTrackSwitchWye(id, coordinates_world, coordinates_delta1, coordinates_delta2, coordinates_delta3, coordinates_delta4,coordinates_deltaE,coordinates_delta);
	    	this.parserHelper.getActionProcessor().schedule(command);
	    }
	    
	        
	}
	
	public String getID() {
		return this.id;
	}
	public CoordinatesDelta getStartCoordinates() {
		return this.coordinates_delta1;
	}
	public CoordinatesDelta getEndCoordinates() {
		return this.coordinates_delta2;
	}
	public CoordinatesWorld getWorldCoordinates() {
		return this.coordinates_world;
	}
	public double getNumber() {
		return this.number;
	}
	public LinkedList getpoles() {
		return this.idpoles;
	}
	public String getId1() {
		return this.id1;
	}
	public String getIdn() {
		return idn;
	}
}
