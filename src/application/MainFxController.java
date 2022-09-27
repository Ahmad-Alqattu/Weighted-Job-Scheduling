package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class MainFxController implements Initializable {

	@FXML
	private Label path;

	@FXML
	private Label profit;
	@FXML
	private Button read;
	@FXML
	private GridPane grid;
	@FXML
	private TextField Dir1;

	@FXML
	private TableColumn<Project, String> EndingC;

	@FXML
	private TableColumn<?, ?> ProjectC;

	@FXML
	private TableColumn<Project, String> RewardC;

	@FXML
	private TableColumn<Project, Integer> StartingC;

	@FXML
	private TableView<Project> projTable;

	@FXML
	private ListView<String> l1;
	@FXML
	private ListView<String> l0;

	@FXML
	private ListView<String> l2;

	@FXML
	private ListView<String> l3;
	@FXML
	private ScrollPane scroll;
	@FXML
	private Label attend;

	@FXML
	private Label pofit;
	Stack<Integer> S = new Stack<Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l1.setCellFactory(cell -> {
			return new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);

						// decide to add a new styleClass
						// getStyleClass().add("costume style");
						// decide the new font size
						setFont(Font.font(18));
						setStyle("-fx-background-color: #1F1F38;-fx-text-fill: white");

					}	 else {
						setText("");
		                setStyle("-fx-background-color: #1F1F38");
		                setGraphic(null);
					}
				}
			};
		});
		l0.setCellFactory(cell -> {
			return new ListCell<String>() {

				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);

						// decide to add a new styleClass
						// getStyleClass().add("costume style");
						// decide the new font size
						setFont(Font.font(18));
						setStyle("-fx-background-color: #1F1F38;-fx-text-fill: white");

					}	 else {
						setText("");
		                setStyle("-fx-background-color: #1F1F38");
		                setGraphic(null);
					}
				}
			};
		});

		l2.setCellFactory(cell -> {
			return new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					

					if (item != null) {
						setText(item);

						// decide to add a new styleClass
						// getStyleClass().add("costume style");
						// decide the new font size
						setFont(Font.font(18));
						setStyle("-fx-background-color: #1F1F38;-fx-text-fill: white");
					}	 else {
						setText("");
		                setStyle("-fx-background-color: #1F1F38");
		                setGraphic(null);
					
					}
				}
			};
		});
		l3.setCellFactory(cell ->

		{
			return new ListCell<String>() {
				@Override
				protected  void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);

						// decide to add a new styleClass
						// getStyleClass().add("costume style");
						// decide the new font size
						setStyle("-fx-background-color: #1F1F38;-fx-text-fill: white");
						setFont(Font.font(18));
					}	 else {
						setText("");
		                setStyle("-fx-background-color: #1F1F38");
		                setGraphic(null);
					}
				}
			};
		});

	}

	/**
	 * @param event
	 */
	@FXML
	void read(ActionEvent event) {
		
		
		
		
		
		
		Alert a = new Alert(AlertType.WARNING);
		Project projects[] = null;
		l0.getItems().removeAll();
		l2.getItems().removeAll();
		l3.getItems().removeAll();
		l1.getItems().removeAll();
		l0.getItems().clear();
		l2.getItems().clear();
		l3.getItems().clear();
		l1.getItems().clear();
		
		
		
		
		

		try {
			Stage s = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open inbut File");
			File sfile = fileChooser.showOpenDialog(s);
			Dir1.setText(sfile.getAbsolutePath());
			Scanner in = new Scanner(sfile);
			int n = Integer.parseInt(in.nextLine());
			if (n < 200000) {
				System.out.println(n);
				projects = new Project[n];
				l0.getItems().clear();
				l2.getItems().clear();
				l3.getItems().clear();
				l1.getItems().clear();


	
				for (int i = 0; i < n; i++) {
					if (in.hasNext()) {
						String str = in.nextLine();
						System.out.println("d" + str);
						String[] spStr = str.trim().split("[ ]");
						int ai = Integer.parseInt(spStr[0]);
						int bi = Integer.parseInt(spStr[1]);
						int pi = Integer.parseInt(spStr[2]);
						if (ai > bi || 1000000000 < bi || bi < 1 || 1000000000 < pi || pi < 1) {
							a.setAlertType(AlertType.ERROR);
							int j = i + 2;
							a.setContentText("Error occurred at line" + j + " in File " + sfile.getPath());
							a.setTitle("ERROR");
							l0.getItems().clear();
							l2.getItems().clear();
							l3.getItems().clear();
							l1.getItems().clear();
							a.show();
							return;
						}

						l1.getItems().add(spStr[0]);
						l2.getItems().add(spStr[1]);
						l3.getItems().add(spStr[2]);
						l0.getItems().add("" + i);

						projects[i] = (new Project(ai, bi, pi, i));

					} else {
						a.setAlertType(AlertType.WARNING);
						a.setContentText("file is inconstant");
						a.setTitle("WARNING");
						a.show();
					}
				}
				if (n < 9) {
					attend.setVisible(true);
					scroll.setVisible(true);
					grid.setVisible(true);
				}
				WeightedIntervalScheduling w = new WeightedIntervalScheduling();

				S = WeightedIntervalScheduling.schedule(projects);
				
				pofit.setText("The Maximum Profit is: ");

				
				
				
				S = w.findMaxProfit(projects,n);

				// Collections.reverse(S);
				// WeightedIntervalScheduling.schedule(projects);
				profit.setText(String.valueOf(w.maxProfit));

				l0.setCellFactory(cell -> {

					return new ListCell<String>() {

						@SuppressWarnings("unlikely-arg-type")
						@Override

						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);

							if (item != null) {
								System.out.println(item);
								System.out.println(S.peek());

								setFont(Font.font(18));
								if (S.contains(Integer.parseInt(item))) {

									System.out.println(item + " *");
									setText(item + " **");
									setStyle("-fx-background-color: GREEN;-fx-text-fill: black");
						               //setGraphic(item);
			
								} else {

									setStyle("-fx-background-color: #1F1F38;-fx-text-fill: RED");

									setText(item + "");
								}
							}		 else {
								setText("");
				                setStyle("-fx-background-color: #1F1F38");
				                setGraphic(null);
							}
						}
					};

				});
				
				
				if (S.toArray().length < 8) {
					Stack<Integer> R = new Stack<Integer>();
					R.addAll(S);
					System.out.println(R.peek() + "dd");
					path.setText("");
					while (!R.isEmpty()) {

						path.setStyle("-fx-text-fill: GREEN");
						path.setText(path.getText() + " proj" + R.pop() + " >");

					}
				}
				in.close();

			} else {

				a.setAlertType(AlertType.WARNING);
				a.setContentText("Number of projects is more than 2*(10^5)");
				a.setTitle("WARNING");
				a.show();

			}
			in.close();

		} catch (Exception e) {
			l0.getItems().clear();
			l2.getItems().clear();
			l3.getItems().clear();
			l1.getItems().clear();
			grid.setVisible(false);
			pofit.setText("Read file to get maximum profit ");
			profit.setText("");
			scroll.setVisible(false);
			attend.setVisible(false);
			path.setText("");
			a.setAlertType(AlertType.ERROR);
			a.setContentText("ERROR: Read file again");
			a.setTitle("ERROR");
			a.show();
		} finally {

		}

	}

	@FXML
	void MC(MouseEvent event) {
		read.setStyle(
				"-fx-background-color: #994f1a; -fx-background-radius:13;-fx-border-width:.5; -fx-border-radius:12;-fx-border-color:#00ADB5");
		read.setStyle(
				"-fx-background-color: #FA7616; -fx-background-radius:13;-fx-border-width:.5; -fx-border-radius:12;-fx-border-color:#00ADB5");

		// read.setStyle("-fx-background-color: #994f1a; ");

	}

	@FXML
	void ME(MouseEvent event) {
		// read.setStyle("-fx-background-color: #994f1a;
		// -fx-background-radius:13;-fx-border-width:.5;
		// -fx-border-radius:12;-fx-border-color:#00ADB5");

	}

	@FXML
	void MEX(MouseEvent event) {
		// read.setStyle("-fx-background-color: #FA7616;
		// -fx-background-radius:13;-fx-border-width:.5;
		// -fx-border-radius:12;-fx-border-color:#00ADB5");

	}

	@FXML
	void MR(MouseEvent event) {
		read.setStyle(
				"-fx-background-color: #FA7616;-fx-background-radius:13;-fx-border-width:.5; -fx-border-radius:12");

	}

}
