

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TTTGUI extends Application {

	private boolean playable = true;
	private boolean turnX = true;
	private boolean xWin=false;
	private boolean oWin=false;
	private boolean isTwoPlayer=false;
	private Tile[][] board = new Tile[3][3];
	private List<Combo> combos = new ArrayList<>();


	private Pane root = new Pane();


	private Pane createContent() {
		root.getChildren().clear();
		playable=true;
		xWin=false;
		oWin=false;
		turnX=true;
		root.setPrefSize(600, 600);
		combos.clear();

		//create the tic tac toe board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Tile tile = new Tile();
				tile.setTranslateX(j * 200); //setTranslate is a method of StackPane
				tile.setTranslateY(i * 200); //putting the tiles in place.

				root.getChildren().add(tile);

				board[j][i] = tile;
			}
		}

		// horizontal
		for (int y = 0; y < 3; y++) {
			combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
		}

		// vertical
		for (int x = 0; x < 3; x++) {
			combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
		}

		// diagonals
		combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
		combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

		return root;
	}


	private void checkState() {

		//stop the game if X or O has made 3 in a row.
		for (Combo combo : combos) {
			if (combo.isComplete()) {
				playable = false;
				if(combo.tiles[0].getValue().equals("X"))
					xWin=true;
				else oWin=true;

				
			
				playWinAnimation(combo);
				//board[2][2].getScene().setRoot(root);
				
			//	board[2][2].getScene().setRoot(endMenu());
			return;
				
		
			}
		
		}

		boolean isFull=true;

		//stop the game if the board is full.
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(!board[i][j].getValue().isEmpty()) 
					continue;
				else  isFull=false;

			}
		}

		if(isFull) {
			
			playFullAnimation();
			return;
		}
		//otherwise if its not the player's turn, the computer moves.

		if(!isTwoPlayer&&!turnX) 
			makeMove();


	}

	private void makeMove() {

		Random r=new Random();
		if (!playable)
			return;

		if (!turnX) {
			for(Combo c:combos) {


				int countO=0;

				for(int i=0;i<3;i++) {
					
			 if(c.tiles[i].getValue()=="O")
						countO++;
				}

				if(countO==2) {
					for(int j=0;j<3;j++){
						if(c.tiles[j].getValue().isEmpty()) {
							c.tiles[j].drawO();
							turnX=true;
							checkState();
							return;
						}

					}}

			}
			for(Combo c:combos) {

				int countX=0;
		

				for(int i=0;i<3;i++) {
					if(c.tiles[i].getValue()=="X")
						countX++;
			
				}

				if(countX==2) {
					for(int j=0;j<3;j++){
						if(c.tiles[j].getValue().isEmpty()) {
							c.tiles[j].drawO();
							turnX=true;
							checkState();
							return;
						}

					}}
			}

			while(true) {
				int i=r.nextInt(3);
				int j=r.nextInt(3);
				if(board[i][j].getValue().isEmpty()) {
					board[i][j].drawO();
					turnX=true;
					checkState();
					return;
				}
			}         
		}
	}


	private void playWinAnimation(Combo combo) {
		Line line = new Line();
		line.setStartX(combo.tiles[0].getCenterX());
		line.setStartY(combo.tiles[0].getCenterY());
		line.setEndX(combo.tiles[0].getCenterX());
		line.setEndY(combo.tiles[0].getCenterY());

		root.getChildren().add(line);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
				new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
				new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
		timeline.play();
		timeline.setOnFinished(e->{line.getScene().setRoot(endMenu());});
		
	}
	private void playFullAnimation() {
	
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
		timeline.play();
		timeline.setOnFinished(e->{
		board[0][0].getScene().setRoot(endMenu());});
		
	}


	//returns a LAYOUT.
	private Parent startMenu() {


		Text text=new Text("Welcome to Tic-Tac-Toe!");
		Label label=new Label("Select a game mode:");
		label.setFont(new Font(25));
		text.setFont(new Font(50));

		Button twoPlayer=new Button("1. Two Player");
		Button vsAI=new Button("2. Player vs. AI");


		isTwoPlayer=false;
		twoPlayer.setPrefSize(100, 100);
		vsAI.setPrefSize(100, 100);


		VBox start=new VBox(15);
		HBox hi=new HBox(20);
		hi.getChildren().addAll(twoPlayer,vsAI);
		hi.setAlignment(Pos.CENTER);
		start.setAlignment(Pos.CENTER);
		start.getChildren().addAll(text,label,hi);
		//clicking one of the two buttons triggers a new window popup.
		twoPlayer.setOnMouseClicked(e->{

			isTwoPlayer=true;
			twoPlayer.getScene().setRoot(createContent()); //CRITICAL. (the scene of the root that the button is on)


		});

		vsAI.setOnMouseClicked(e->{
			twoPlayer.getScene().setRoot(createContent()); //CRITICAL. (the scene of the root that the button is on)

		});


		return start;

	}

	//returns a root that can be used as parameters for window
	private Parent endMenu() {

		Text text;
		if(oWin)
			text=new Text("O's win!");
		else if(xWin)
			text=new Text("X's win!");
		else
			text=new Text("It's a draw!");
		
		text.setFont(Font.font(83));

		Button choice1=new Button("Play Again");
		Button choice2=new Button("Quit");

		choice1.setOnAction(e->{
			choice1.getScene().setRoot(startMenu());});

		choice2.setOnAction(e->{Platform.exit();});


		VBox box=new VBox(20);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(text,choice1,choice2);

		return box;
	}




	//this object represents all the combinations of 3 (rows,cols,diagonals) on the TTT board
	class Combo {
		private Tile[] tiles; //array of 3 tiles
		public Combo(Tile... tiles) {
			this.tiles = tiles;
		}

		//checks to see if X or O has made 3 in a row yet.
		public boolean isComplete() {
			if (tiles[0].getValue().isEmpty())
				return false;

			return tiles[0].getValue().equals(tiles[1].getValue())
					&& tiles[0].getValue().equals(tiles[2].getValue());

		}

	}


	class Tile extends StackPane {
		private Text text = new Text(); //x or o

		public Tile() {
			//styling the tile
			Rectangle border = new Rectangle(200, 200);
			border.setFill(null); //white square with black border
			border.setStroke(Color.BLACK);

			text.setFont(Font.font(72));

			setAlignment(Pos.CENTER); //position elements (namely X or O) in center of tile
			getChildren().addAll(border, text);

			//when tile is clicked by user...
			setOnMouseClicked(event -> {
				if (!playable||(!isTwoPlayer&&!turnX))
					return;

				//if Tile is occupied, do nothing.
				if(!getValue().isEmpty())
					return;
				if(isTwoPlayer) {
					if(turnX) {
						drawX();
						turnX=false;
						
					}
					else {
						drawO();
						turnX=true;
					
					}

				}
				if(!isTwoPlayer) {
					if(turnX) {
						drawX();
						turnX=false;
						
					}
				}

				checkState();   
			
			});
		}

		public double getCenterX() {
			return getTranslateX() + 100;
		}

		public double getCenterY() {
			return getTranslateY() + 100;
		}

		public String getValue() {
			return text.getText();
		}

		private void drawX() {
			text.setText("X");
		}

		private void drawO() {
			text.setText("O");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene=new Scene(startMenu());
		primaryStage.setWidth(600);
		primaryStage.setHeight(600);
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}