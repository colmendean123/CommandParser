package cs350f20project.controller.cli.parser;

import java.util.Arrays;

import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.PointLocator;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectBridge;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectRoundhouse;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectSwitch;
import cs350f20project.controller.command.creational.CommandCreateStockCarBox;
import cs350f20project.controller.command.creational.CommandCreateStockCarCaboose;
import cs350f20project.controller.command.creational.CommandCreateStockCarFlatbed;
import cs350f20project.controller.command.creational.CommandCreateStockCarPassenger;
import cs350f20project.controller.command.creational.CommandCreateStockCarTank;
import cs350f20project.controller.command.creational.CommandCreateStockCarTender;
import cs350f20project.controller.command.creational.CommandCreateStockEngineDiesel;
import cs350f20project.controller.command.creational.CommandCreateTrackBridgeDraw;
import cs350f20project.controller.command.meta.CommandMetaDoExit;
import cs350f20project.controller.command.structural.CommandStructuralCouple;
import cs350f20project.controller.command.structural.CommandStructuralLocate;
import cs350f20project.datatype.Angle;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;

public class CommandParser 
{
	private String commandText;
	private MyParserHelper parserHelper;
	private String id, id1, id2;
	private int number;
	private String position;
	private boolean isFacingStartElseEnd;
	private TrackLocator trackLocator;
	private CoordinatesWorld coordinates_world;
	private CoordinatesDelta coordinates_delta1;
	private CoordinatesDelta coordinates_delta2;
	private Angle angle;
	private PointLocator pointLocator;
	
	
	private String[] command2 = ("DO BRAKE " + id).split(" ");
	private String[] command6 = ("DO SELECT DRAWBRIDGE " + id + "POSITION (UP | DOWN) ").split(" ");
	private String[] command7 = ("DO SELECT ROUNDHOUSE " + id + "POSITION " + angle + "( CLOCKWISE | COUNTERCLOCKWISE )").split(" ");
	private String[] command8 = ("DO SELECT SWITCH " + id + "PATH ( PRIMARY | SECONDARY )").split(" ");
	private String[] command28 = ("CREATE STOCK CAR " + id + "AS BOX ").split(" ");
	private String[] command29 = ("CREATE STOCK CAR " + id + "AS CABOOSE ").split(" ");
	private String[] command30 = ("CREATE STOCK CAR " + id + "AS FLATBED ").split(" ");
	private String[] command31 = ("CREATE STOCK CAR " + id + "AS PASSENGER ").split(" ");
	private String[] command32 = ("CREATE STOCK CAR " + id + "AS TANK ").split(" ");
	private String[] command33 = ("CREATE STOCK CAR " + id + "AS TENDER ").split(" ");
	private String[] command34 = ("CREATE STOCK ENGINE " + id1 + "AS DIESEL ON TRACK " + id2 + "DISTANCE "  + number + "FROM ( START | END ) FACING ( START | END ) ").split(" ");
	private String[] command39 = ("CREATE TRACK BRIDGE " + id1 + "REFERENCE " + coordinates_world + " | '$' " + id2 + "DELTA START " + coordinates_delta1 + "END " + coordinates_delta2).split(" ");
	private String[] command61 = ("COUPLE STOCK " + id1 + "AND " + id2).split(" ");
	private String[] command62 = ("LOCATE STOCK " + id1 + "ON TRACK " + id2 +  "DISTANCE " + number + "FROM ( START | END ) ").split(" ");
	
	public CommandParser(MyParserHelper parserHelper, String commandText)
	{
		this.commandText = commandText;
		this.parserHelper = parserHelper;
	}
	
	
	public void parse()
	{
		if (this.commandText.equalsIgnoreCase("@exit"))
		{
			A_Command command = new CommandMetaDoExit();
			this.parserHelper.getActionProcessor().schedule(command);
		}
	
		//COMMAND 2
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command2)))
		{
			A_Command command = new CommandBehavioralBrake(id);//id should be the last part of the commandText string
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 6
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command6)))
		{
			A_Command command = new CommandBehavioralSelectBridge(position, true);//position should be either up or down(whichever is provided in commandText)
			this.parserHelper.getActionProcessor().schedule(command);			  //boolean is isUpElseDown
		}
		
		//COMMAND 7
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command7)))
		{
			A_Command command = new CommandBehavioralSelectRoundhouse(commandText, null, false);// string is the id of the roundhouse, angle is which angle in degrees it will turn
			this.parserHelper.getActionProcessor().schedule(command);							//boolean will be isClockwise
		}
		
		//COMMAND 8
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command8)))
		{
			A_Command command = new CommandBehavioralSelectSwitch(commandText, false);// the id of the path and the boolean should be isPrimaryElseSecondary
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 28
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command28)))
		{
			A_Command command = new CommandCreateStockCarBox(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 29
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command29)))
		{
			A_Command command = new CommandCreateStockCarCaboose(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 30
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command30)))
		{
			A_Command command = new CommandCreateStockCarFlatbed(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 31
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command31)))
		{
			A_Command command = new CommandCreateStockCarPassenger(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 32
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command32)))
		{
			A_Command command = new CommandCreateStockCarTank(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 33
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command33)))
		{
			A_Command command = new CommandCreateStockCarTender(id);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 34
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command34)))
		{
			A_Command command = new CommandCreateStockEngineDiesel(id,trackLocator, isFacingStartElseEnd);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 39
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command39)))
		{
			A_Command command = new CommandCreateTrackBridgeDraw(id, pointLocator, angle);
			this.parserHelper.getActionProcessor().schedule(command);
		}
		
		//COMMAND 61
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command61)))
		{
			A_Command command = new CommandStructuralCouple(id, id1);
			this.parserHelper.getActionProcessor().schedule(command);
		}

		//COMMAND 62
		else if (this.commandText.equalsIgnoreCase(Arrays.toString(command62)))
		{
			A_Command command = new CommandStructuralLocate(id, trackLocator);
			this.parserHelper.getActionProcessor().schedule(command);
		}
	}
}