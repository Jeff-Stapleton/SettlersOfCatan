package server.facade;

import org.apache.log4j.Logger;
import server.ServerGame;
import server.command.*;
import server.handlers.MovesDiscardCardsHandler;
import shared.CanCan;
import shared.CatanModel;
import shared.MessageLine;
import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.TurnType;
import shared.comm.ServerException;
import shared.comm.serialization.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * The Class MovesFacade implements all the different move commands that can be issued to the server
 *
 */
public class MovesFacade 
{
	private static final Logger log = Logger.getLogger(MovesFacade.class);
	/**
	 * Executes "Send Chat", send the instigator's message to be broadcast to all other players
	 *
	 * @pre player is logged in and joined a game
	 * @post completes the send chat
	 * 
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * 
	 */


	private ServerGame serverGame;
	private CatanModel catanModel;


	public MovesFacade(ServerGame serverGame)
	{
		this.serverGame = serverGame; 
		catanModel = serverGame.getModel();
	}


	/**
	 * Executes "Send Chat", send the instigator's message to be broadcast to all other players
	 *
	 * @pre player is logged in and joined a game
	 * @post completes the send chat
	 * 
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * 
	 */
	public void sendChat(SendChatRequest request)
	{
		SendChatCommand command = new SendChatCommand(request);
		command.execute(catanModel);

		serverGame.getCommandList().add(request);
	}

	/**
	 * Executes "Roll Number", Randomly choose 2 numbers between 1 and 6, then sums them together.
	 *
	 * @pre It is the correct turn/state
	 * @post completes the roll number
	 * 
	 * @param playerIndex the index of the player rolling the dice
	 * @param number the number the player rolled (This is stupid)
	 */
	public void rollNumber(RollNumberRequest request) throws ServerException
	{
		if (request.getNumber() < 1 || request.getNumber() > 12)
			throw new ServerException("Invalid number");
		
		if (catanModel.getTurnTracker().getStatus() != TurnType.ROLLING)
			throw new ServerException("It's not the rolling phase");
		RollNumberCommand command = new RollNumberCommand(request);
		command.execute(catanModel);	  	  

		String action = "rolled a " + request.getNumber();
		gameHistoryMessage(request.getPlayerIndex(), action);

		serverGame.getCommandList().add(request);
	}

	/**
	 * Executes "Rob Player", player picks from a list of robbable player.  The selected player
	 * must give the player one card at random.
	 *
	 * @pre The victim is built on the hex and has at least one card 
	 * @post completes the rob player
	 * 
	 * @param playerIndex the player doing the robbing
	 * @param victimIndex the player being robbed from
	 * @param location the location to move the robber to
	 */
	public void robPlayer(RobPlayerRequest request) throws ServerException
	{
		if (catanModel.getTurnTracker().getStatus() != TurnType.ROBBING)
			throw new ServerException("It's not the robbing phase");
		
		if (request.getPlayerIndex() == catanModel.getTurnTracker().getCurrentTurn())
		{
			RobPlayerCommand command = new RobPlayerCommand(request);
			command.execute(catanModel);

			String action;
			if (request.getVictimIndex() < 0){
				action = "robbed no one";
			}
			else{
				action = "robbed " + catanModel.getPlayers()[request.getVictimIndex()].getName();
			}
			gameHistoryMessage(request.getPlayerIndex(), action);		  

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't rob because it's not their turn");
		}
	}

	/**
	 * Executes "Finish Turn", ends the players turn and allows the next player to 
	 * perform his or her turn.
	 *
	 * @pre Its is indeed the players turn and no other actions ie "trading" are currently pending
	 * @post completes the finish turn
	 * 
	 * @param playerIndex the index of the player ending their turn
	 */
	public void finishTurn(FinishTurnRequest request) throws ServerException
	{
		if (request.getPlayerIndex() == catanModel.getTurnTracker().getCurrentTurn())
		{
			FinishTurnCommand command = new FinishTurnCommand(request);
			command.execute(catanModel);

			String action = "ended their turn";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't finish turn because it isn't their turn");
		}
	}

	/**
	 * Executes "Buy Dev Card", subtracts the resources from the instigator,
	 * adds a dev card to the players hand, and decrease from the bank of dev cards
	 *
	 * @pre The player has enough resources, enough card and is the proper phase/turn
	 * @post completes the buy dev card
	 * 
	 * @param playerIndex the player buying the dev card
	 */
	public void buyDevCard(BuyDevCardRequest request) throws ServerException
	{
		if (CanCan.canBuyDevCard(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getDeck(), catanModel.getTurnTracker()))
		{
			BuyDevCardCommand command = new BuyDevCardCommand(request);
			command.execute(catanModel);

			String action = "bought a DevCard";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't buy devCard because it's not the players turn, the player can't afford it, or there aren't any more devcards");
		}
	}

	/**
	 * Executes "Year of Plenty Dev Card", removes the dev card from the players hand.  Awards 
	 * the player any two resources of his/her choosing
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play year of plenty card
	 * 
	 * @param playerIndex the player playing the dev card
	 * @param resource1 the resource the player wants
	 * @param resource2 the resource the player wants
	 */
	public void yearOfPlenty(YearOfPlentyRequest request) throws ServerException
	{
		if (CanCan.canUseYearOfPlenty(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			YearOfPlentyCommand command = new YearOfPlentyCommand(request);
			command.execute(catanModel);
			String action = " played Year Of Plenty";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't play yearOfPlenty card because player doesn't have the card or it's not their turn");
		}
	}

	/**
	 * Executes "Road Building Dev Card", removes the dev card from the players hand.  Awards the 
	 * player with the ability to place 2 free roads
	 *
	 * @pre The player does in deed have the card and it the correct turn/state and has enough roads
	 * @post completes the play road building card
	 * 
	 * @param playerIndex the index of the player building the road
	 * @param spot1 the location for a road being built
	 * @param spot2 the location for the second road being built
	 */
	public void roadBuilding(RoadBuildingRequest request) throws ServerException
	{
		if (CanCan.canUseRoadBuilder(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			RoadBuildingCommand command = new RoadBuildingCommand(request);
			command.execute(catanModel); 
			String action = "played Road Building";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't play roadBuilding card because player doesn't have the card or it's not their turn");
		}
	}

	/**
	 * Executes "Soldier Dev Card", removes the dev card from the players hand.  Awards the player 
	 * allowing him or her to move the robber and rob a player
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play soldier card
	 * 
	 * @param playerIndex the player playing the dev card
	 * @param victimIndex the victim of the dev card
	 * @param location the robber location
	 */
	public void soldier(SoldierRequest request) throws ServerException
	{
		if (CanCan.canUseSoldier(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			SoldierCommand command = new SoldierCommand(request);
			command.execute(catanModel);
			String action = "played a Soldier";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't play soldier card because player doesn't have the card or it's not their turn");
		}
	}

	/**
	 * Executes "Monopoly Dev Card", removes the dev card from the players hand.  Takes
	 * every resource, of a specified type, from all other players and adds them to the player
	 * hand
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play monopoly card
	 * 
	 * @param playerIndex the player playing the Monopoly card
	 * @param resource the resource the player wishes to steal
	 */
	public void monopoly(MonopolyRequest request) throws ServerException
	{
		if (CanCan.canUseMonopoly(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			MonopolyCommand command = new MonopolyCommand(request);
			command.execute(catanModel);
			String action = "played Monopoly";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't play soldier card because player doesn't have the card or it's not their turn");
		}
	}

	/**
	 * Executes "Monument Dev Card", removes the dev card from the players hand.  Awards the player 
	 * one victory point.
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play monument card
	 * 
	 * @param playerIndex the player playing the dev card
	 */
	public void monument(MonumentRequest request) throws ServerException
	{
		if (CanCan.canUseMonument(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			MonumentCommand command = new MonumentCommand(request);
			command.execute(catanModel);
			String action = "played a Monument";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't play monument card because player doesn't have the card or it's not their turn");
		}
	}

	/**
	 * Executes "Build Road", subtracts the resources from the instigator
	 * subtracts a road from unbuilt road pool and places it on a edge location
	 *
	 * @pre The player has enough resources, enough roads and is a valid edge location
	 * @post completes the build road
	 * 
	 * @param playerIndex the player building the road
	 * @param location the location the player is building the road
	 * @param free whether this road is free or not (This is stupid)
	 */
	public void buildRoad(BuildRoadRequest request) throws ServerException
	{		
		EdgeLocation road = new EdgeLocation(request.getRoadLocation().getX(), request.getRoadLocation().getY(), EdgeDirection.fromString(request.getRoadLocation().getDirection()));

		if(CanCan.canBuildRoad(catanModel.getPlayers()[request.getPlayerIndex()], road, catanModel.getTurnTracker(), catanModel.getMap()))
		{
			BuildRoadCommand command = new BuildRoadCommand(request);
			command.execute(catanModel);

			String action = "built a road";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't build Road because it's not the player's turn, they do not have the resources, or it's an invalid road location.");
		}
	}

	/**
	 * Executes "Build Settlement", subtracts the resources from the instigator
	 * subtracts a city from unbuilt settlement pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough settlements and is a valid vertex location
	 * @post completes the build settlement
	 * 
	 * @param playerIndex the index of the player building the settlement
	 * @param location the location of the settlement
	 * @param free whether the settlement is free or not (Why?)
	 */
	public void buildSettlement(BuildSettlementRequest request) throws ServerException
	{
		VertexLocation settlement = new VertexLocation(request.getVertexLocation().getX(), request.getVertexLocation().getY(), VertexDirection.fromString(request.getVertexLocation().getDirection()));

		if(CanCan.canBuildSettlement(catanModel.getPlayers()[request.getPlayerIndex()], settlement, catanModel.getTurnTracker(), catanModel.getMap()))
		{
			BuildSettlementCommand command = new BuildSettlementCommand(request);
			command.execute(catanModel);
			String action = "built a Settlement";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't build Settlement because it's not the player's turn, they do not have the resources, or it's an invalid settlement location.");
		}	  
	}

	/**
	 * Executes "Build City", subtracts the resources from the instigator
	 * subtracts a city from unbuilt city pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough cities and is a valid vertex location
	 * @post completes the build city
	 * 
	 * @param playerIndex the player building the city
	 * @param location the location of the city bing built
	 * @param free whether the city will be free (I still don't get this...)
	 */
	public void buildCity(BuildCityRequest request) throws ServerException
	{
		VertexLocation city = new VertexLocation(request.getVertexLocation().getX(), request.getVertexLocation().getY(), VertexDirection.fromString(request.getVertexLocation().getDirection()));

		if(CanCan.canBuildCity(catanModel.getPlayers()[request.getPlayerIndex()], city, catanModel.getTurnTracker(), catanModel.getMap()))
		{
			BuildCityCommand command = new BuildCityCommand(request);
			command.execute(catanModel);
			String action = "built a City";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't build City because it's not the player's turn, they do not have the resources, or it's an invalid city location.");
		}
	}

	/**
	 * Executes the "Offer Trade", Player specifies what he/she is will to give in exchanged for 
	 * his/her specified offer.  Player identifies which player to trade with.
	 *
	 * @pre The player has the resources in questions and is the correct turn/state
	 * @post completes the offer trade
	 * 
	 * @param offer the offer of trade
	 */
	public void offerTrade(OfferTradeRequest request) throws ServerException
	{
		if (CanCan.canOfferTrade(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getPlayers()[request.getReceiver()], catanModel.getTurnTracker(), request.getOffer()))
		{
			OfferTradeCommand command = new OfferTradeCommand(request);
			command.execute(catanModel);
			String action = "offered trade to " + catanModel.getPlayers()[request.getReceiver()].getName();
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't offer trade because they don't have enough reasons, the receiver doesn't have enough resources, or it's not their turn.");
		}
	}

	/**
	 * Executes the "Accept Trade", exchanges the resources between the instigator and
	 * the investigator
	 *
	 * @pre is a valid trade offer
	 * @post completes the domestic trade
	 * 
	 * @param playerIndex the index of the player accepting the trade
	 * @param willAccept whether the player accepts or rejects the offer
	 */
	public void acceptTrade(AcceptTradeRequest request) throws ServerException
	{
		if (catanModel.getTradeOffer() == null)
		{
			throw new ServerException("Player can't accept trade because trade offer is null or the trade offer is meant for someone else.");
		}
		else
		{
			AcceptTradeCommand command = new AcceptTradeCommand(request);
			command.execute(catanModel);
			String action;
			if (request.getWillAccept()){
				action = "accepted trade";
			}
			else{
				action = "rejected trade";
			}
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
	}

	/**
	 * Executes "Maritime Trade", subtracts the given resources from the instigator
	 * and add the get resources to the instigator. 
	 *
	 * @pre Bank and player have enough resources, players is built on a port and it correct turn/state
	 * @post completes the maritime trade
	 * 
	 * @param playerIndex the player doing the maritime trade
	 * @param ratio the ratio to trade the resources at
	 * @param inputResource the input resource type
	 * @param outputResource the output resource type
	 */
	public void maritimeTrade(MaritimeTradeRequest request) throws ServerException
	{
		ResourceList maritimeOffer = new ResourceList(0, 0, 0, 0, 0);
		ResourceType resource = null;
		log.debug(resource);
		switch (request.getInputResource())
		{
		case "wood" 	: maritimeOffer.setWood(request.getRatio());	resource = ResourceType.WOOD;	break;
		case "brick" 	: maritimeOffer.setBrick(request.getRatio());	resource = ResourceType.BRICK;	break;
		case "ore" 		: maritimeOffer.setOre(request.getRatio());		resource = ResourceType.ORE;	break;
		case "wheat" 	: maritimeOffer.setWheat(request.getRatio());	resource = ResourceType.WHEAT;	break;
		case "sheep" 	: maritimeOffer.setSheep(request.getRatio());	resource = ResourceType.SHEEP;	break;
		}
		log.debug(resource);
		switch (request.getOutputResource())
		{
		case "wood" 	: maritimeOffer.setWood(-1);			break;
		case "brick" 	: maritimeOffer.setBrick(-1);			break;
		case "ore" 		: maritimeOffer.setOre(-1);				break;
		case "wheat" 	: maritimeOffer.setWheat(-1);			break;
		case "sheep" 	: maritimeOffer.setSheep(-1);			break;
		}
		log.debug(resource);
		if (CanCan.canMaritimeTrade(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker(), maritimeOffer, resource, catanModel.getBank(), catanModel.getMap().getPorts(), catanModel.getMap()))
		{
			log.debug("Set Up Command");
			MaritimeTradeCommand command = new MaritimeTradeCommand(request);
			log.debug("Execute Command");
			command.execute(catanModel);

			String action = "used Maritime Trade";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			log.debug("Failed CanCan");
			throw new ServerException("Player can't do maritime trade because either he or the bank don't have the resources, he's not on the right port, or it's not their turn.");
		}
	}

	/**
	 * Executes "Discard Card", removes the assigned card(s) from the players hand
	 *
	 * @pre The player has the assigned cards to discard
	 * @post completes the discard card
	 * 
	 * @param playerIndex the player discarding the card
	 * @param cards the cards the player is discarding
	 */
	public void discardCards(DiscardCardsRequest request) throws ServerException
	{
		if (CanCan.canDiscardCards(catanModel.getPlayers()[request.getPlayerIndex()], catanModel.getTurnTracker()))
		{
			DiscardCardCommand command = new DiscardCardCommand(request);
			command.execute(catanModel);

			String action = "discarded";
			gameHistoryMessage(request.getPlayerIndex(), action);

			serverGame.getCommandList().add(request);
		}
		else
		{
			throw new ServerException("Player can't discard because it's not their turn or they have already discarded");
		}
	}

	private void gameHistoryMessage(int sourceIndex, String action){
		String sourceName = catanModel.getPlayers()[sourceIndex].getName();
		catanModel.getLog().addLine(new MessageLine(sourceName, sourceName + " " + action));
	}
}