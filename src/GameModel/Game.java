package GameModel;
/*
Code created by Josh Braza
*/

import java.util.Stack;

import javax.swing.JOptionPane;

import CardModel.*;
import Interfaces.GameConstants;
import View.UNOCard;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//import static Interfaces.UNOConstants.WILD;

public class Game implements GameConstants {

	private Player[] players;
	private boolean isOver;
	private int GAMEMODE;

	private PC pc;
	private Dealer dealer;
	private Stack<UNOCard> cardStack;

	public Game(int mode){

		GAMEMODE = mode;
		//Create players
		String name = (GAMEMODE==MANUAL) ? JOptionPane.showInputDialog("Player 1") : "PC";
		String name2 = JOptionPane.showInputDialog("Player 2");

//		ColorSelectionWindow colorSelection = new ColorSelectionWindow();
//		String selectedPalette = colorSelection.getSelectedPalette();

		if(GAMEMODE==vsPC)
			pc = new PC();

		Player player1 = (GAMEMODE==vsPC) ? pc : new Player(name);
		Player player2 = new Player(name2);

		playBackgroundMusic("D:\\Area de trabalho\\Aula\\MS28S\\Projeto\\uno-ms28s\\src\\Sounds\\Run-Amok_chosic.com_.wav");

		player2.toggleTurn();				//Initially, player2's turn

		players = new Player[]{player1, player2};

		//Create Dealer
		dealer = new Dealer();
		cardStack = dealer.shuffle();
		dealer.spreadOut(players);

		isOver = false;
	}

	private void playBackgroundMusic(String audioFilePath) { //som de fundo
		try {
			// Carrega o arquivo de música de fundo
			File audioFile = new File(audioFilePath);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

			// Cria um Clip para reproduzir a musica de fundo
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			//Controla o volume da musica
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);

			// Reproduz a música em loop
			clip.loop(Clip.LOOP_CONTINUOUSLY);

			// Inicia a reprodução
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	private void playAudio(String audioFilePath) { //pescar carta
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Carregue o arquivo de áudio
					File audioFile = new File(audioFilePath);
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

					// Crie um Clip para reproduzir o áudio
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);

					// Reproduza o áudio
					clip.start();

					// Libere os recursos do Clip quando a reprodução terminar
					clip.addLineListener(new LineListener() {
						@Override
						public void update(LineEvent event) {
							if (event.getType() == LineEvent.Type.STOP) {
								clip.close();
							}
						}
					});
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void playCardSound() { //jogar carta
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Carregue o arquivo de som da carta
					File audioFile = new File("D:\\Area de trabalho\\Aula\\MS28S\\Projeto\\uno-ms28s\\src\\Sounds\\depositphotos_414403158-track-short-recording-footstep-dry-grass.wav");
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

					// Crie um Clip para reproduzir o som
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);

					// Reproduza o som
					clip.start();

					// Libere os recursos do Clip quando a reprodução terminar
					clip.addLineListener(new LineListener() {
						@Override
						public void update(LineEvent event) {
							if (event.getType() == LineEvent.Type.STOP) {
								clip.close();
							}
						}
					});
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public Player[] getPlayers() {
		return players;
	}

	public UNOCard getCard() {
		return dealer.getCard();
	}

	public void removePlayedCard(UNOCard playedCard) {

		for (Player p : players) {
			if (p.hasCard(playedCard)){
				p.removeCard(playedCard);
				playCardSound();

				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infoPanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}else if(p.getTotalCards()>2){
					p.setSaidUNOFalse();
				}
			}
		}
	}

	//give player a card
	public void drawCard(UNOCard topCard) {

		boolean canPlay = false;

		for (Player p : players) {
			if (p.isMyTurn()) {
				UNOCard newCard = getCard();
				p.obtainCard(newCard);
				canPlay = canPlay(topCard, newCard);

				if(pc.isMyTurn() && canPlay){
					playPC(topCard);
					canPlay = true;
				}
			}
		}

		playAudio("D:\\Area de trabalho\\Aula\\MS28S\\Projeto\\uno-ms28s\\src\\Sounds\\depositphotos_431797418-track-heavily-pushing-releasing-spacebar-keyboard.wav");

		if (!canPlay)
			switchTurn();
	}

	public void switchTurn() {
		for (Player p : players) {
			p.toggleTurn();
		}
		whoseTurn();
	}

	//Draw cards x times
	public void drawPlus(int times) {
		for (Player p : players) {
			if (!p.isMyTurn()) {
				for (int i = 1; i <= times; i++)
					p.obtainCard(getCard());
			}
		}
	}

	//response whose turn it is
	public void whoseTurn() {

		for (Player p : players) {
			if (p.isMyTurn()){
				infoPanel.updateText(p.getName() + "'s Turn");
				System.out.println(p.getName() + "'s Turn");
			}
		}
		infoPanel.setDetail(playedCardsSize(), remainingCards());
		infoPanel.repaint();
	}

	//return if the game is over
	public boolean isOver() {

		if(cardStack.isEmpty()){
			isOver= true;
			return isOver;
		}

		for (Player p : players) {
			if (!p.hasCards()) {
				isOver = true;
				break;
			}
		}

		return isOver;
	}

	public int remainingCards() {
		return cardStack.size();
	}

	public int[] playedCardsSize() {
		int[] nr = new int[2];
		int i = 0;
		for (Player p : players) {
			nr[i] = p.totalPlayedCards();
			i++;
		}
		return nr;
	}

	//Check if this card can be played
	private boolean canPlay(UNOCard topCard, UNOCard newCard) {

		// Color or value matches
		if (topCard.getColor().equals(newCard.getColor())
				|| topCard.getValue().equals(newCard.getValue()))
			return true;
			// if chosen wild card color matches
		else if (topCard.getType() == WILD)
			return ((WildCard) topCard).getWildColor().equals(newCard.getColor());

			// suppose the new card is a wild card
		else if (newCard.getType() == WILD)
			return true;

		// else
		return false;
	}

	//Check whether the player said or forgot to say UNO
	public void checkUNO() {
		for (Player p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infoPanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}
			}
		}
	}

	public void setSaidUNO() {
		for (Player p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 2) {
					p.saysUNO();
					infoPanel.setError(p.getName() + " said UNO");
				}
			}
		}
	}

	public boolean isPCsTurn(){
		if(pc.isMyTurn()){
			return true;
		}
		return false;
	}

	//if it's PC's turn, play it for pc
	public void playPC(UNOCard topCard) {

		if (pc.isMyTurn()) {
			boolean done = pc.play(topCard);

			if(!done)
				drawCard(topCard);
		}
	}
}