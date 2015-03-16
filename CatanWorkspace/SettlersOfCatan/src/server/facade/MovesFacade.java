package server.facade;

import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * The Class MovesFacade implements all the different move commands that can be issued to the server
 *
 */
public class MovesFacade {
  
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
  public void sendChat(int playerIndex, String content){
    
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
  public void rollNumber(int playerIndex, int number){
    
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
  public void robPlayer(int playerIndex, int victimIndex, HexLocation location){
    
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
  public void finishTurn(int playerIndex){
    
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
  public void buyDevCard(int playerIndex){
    
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
  public void yearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2){
    
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
  public void roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2){
    
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
  public void soldier(int playerIndex, int victimIndex, HexLocation location){
    
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
  public void monopoly(int playerIndex, ResourceType resource){
	  
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
  public void monument(int playerIndex){
    
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
  public void buildRoad(int playerIndex, EdgeLocation location, boolean free){
    
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
  public void buildSettlement(int playerIndex, VertexLocation location, boolean free){
    
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
  public void buildCity(int playerIndex, VertexLocation location, boolean free){
    
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
  public void offerTrade(TradeOffer offer){
    
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
  public void accepTrade(int playerIndex, boolean willAccept){
    
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
  public void maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource){
    
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
  public void discardCards(int playerIndex, ResourceList cards){
    
  }
}
